package net.anthavio.joshi.client;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import net.anthavio.cache.CacheBase;
import net.anthavio.httl.HttpSender;
import net.anthavio.httl.HttpSenderConfig;
import net.anthavio.httl.HttpURLSender;
import net.anthavio.httl.PostRequest;
import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.SenderResponse;
import net.anthavio.httl.cache.CachedResponse;
import net.anthavio.httl.cache.CachingSender;
import net.anthavio.joshi.client.api.ApiOperationsRequest;
import net.anthavio.joshi.client.api.ApiOperationsResponse;
import net.anthavio.joshi.client.api.AuthorizationResponse;
import net.anthavio.joshi.client.applications.ApplicationOperations;
import net.anthavio.joshi.client.cartridges.CartridgeOperations;
import net.anthavio.joshi.client.plan.PlanOperations;
import net.anthavio.joshi.client.user.UserOperations;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author martin.vanek
 *
 */
public class JoshiClient implements Closeable {

	private static final Logger logger = LoggerFactory.getLogger(JoshiClient.class);

	private final JoshiSettings settings;

	private final HttpSender sender;

	private final CachingSender cachingSender;

	private String bearerAuthHeader;

	private String basicAuthHeader;

	private final ObjectMapper mapper;

	public JoshiClient(String username, String password) {
		this(new JoshiSettings(username, password));
	}

	public JoshiClient(JoshiSettings settings) {
		this(settings, new HttpURLSender(settings.getServerUrl()), null);
	}

	public JoshiClient(JoshiSettings settings, HttpSender sender) {
		this(settings, sender, null);
	}

	public JoshiClient(JoshiSettings settings, CacheBase<CachedResponse> cache) {
		this(settings, new HttpURLSender(settings.getServerUrl()), cache);
	}

	public JoshiClient(JoshiSettings settings, HttpSender sender, CacheBase<CachedResponse> cache) {
		if (settings == null) {
			throw new IllegalArgumentException("Null settings");
		}
		this.settings = settings;

		if (sender == null) {
			throw new IllegalArgumentException("Null sender");
		}
		this.sender = sender;

		if (cache != null) {
			cachingSender = new CachingSender(sender, cache);
		} else {
			cachingSender = null;
		}

		this.mapper = new ObjectMapper();
		this.mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		//SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null)).addDeserializer(MyType.class,
		//		new MyTypeDeserializer());

		HttpSenderConfig config = this.sender.getConfig();
		config.setDefaultAccept("application/json");

		byte[] bytes = (settings.getUsername() + ":" + settings.getPassword()).getBytes(Charset.forName(config
				.getEncoding()));
		String encoded = new String(Base64.encodeBase64(bytes, false));
		this.basicAuthHeader = "Basic " + encoded;

	}

	public CacheBase<CachedResponse> getCache() {
		if (cachingSender != null) {
			return cachingSender.getCache();
		} else {
			return null;
		}
	}

	public void close() {
		if (cachingSender != null) {
			cachingSender.close();
		} else {
			sender.close();
		}
	}

	public JoshiSettings getSettings() {
		return settings;
	}

	public ApiOperationsResponse api() {
		return execute(new ApiOperationsRequest(this));
	}

	public ApplicationOperations applications() {
		return new ApplicationOperations(this);
	}

	public CartridgeOperations cartridges() {
		return new CartridgeOperations(this);
	}

	public UserOperations users() {
		return new UserOperations(this);
	}

	public PlanOperations plans() {
		return new PlanOperations(this);
	}

	private String fetchAuthToken() {
		PostRequest request = sender.POST("/broker/rest/user/authorizations")
				.body("scope=session", "application/x-www-form-urlencoded").header("Authorization", basicAuthHeader).build();
		AuthorizationResponse response = sender.extract(request, AuthorizationResponse.class).getBody();
		return response.getData().getToken();
	}

	public <T extends JoshiResponse<?>> T execute(JoshiRequest<?, T> request) {

		SenderRequest sr = request.buildRequest();
		//sr.addParameter("application_id", settings.getApplicationId());

		if (settings.isUseAuthToken() && bearerAuthHeader == null) {
			String authToken = fetchAuthToken();
			bearerAuthHeader = "Bearer " + authToken;
		}

		if (bearerAuthHeader != null) {
			sr.setHeader("Authorization", bearerAuthHeader);
		} else {
			sr.setHeader("Authorization", basicAuthHeader);
		}

		SenderResponse response;
		if (cachingSender != null) {
			Long cacheSeconds = request.getCacheSeconds();
			if (cacheSeconds == null) {
				cacheSeconds = settings.getCacheSeconds();
			}
			if (cacheSeconds == null) {
				cacheSeconds = 10 * 60l;
			}
			response = this.cachingSender.from(sr).evictTtl(cacheSeconds, TimeUnit.SECONDS).execute();
		} else {
			if (request.getCacheSeconds() != null || settings.getCacheSeconds() != null) {
				logger.warn("Cache is not configured, setting cache time is futile.");
			}
			response = this.sender.execute(sr);
		}

		try {

			T value;
			if (response.getMediaType().indexOf("json") == -1) {
				throw new JoshiException("Not a Json response: " + response.getMediaType() + " Http Code: "
						+ response.getHttpStatusCode());
			} else {
				Class<T> responseClass = request.getConfig().getResponseClass();
				if (responseClass != null) {
					value = mapper.readValue(response.getReader(), request.getConfig().getResponseClass());
				} else {
					JavaType javaType = mapper.getTypeFactory().constructType(request.getConfig().getResponseType());
					value = mapper.readValue(response.getReader(), javaType);
				}
			}

			if (response.getHttpStatusCode() != 200) {
				throw new JoshiException(value.getStatus(), value.getMessages());
			}
			//if (value.getStatus() == Status.error) {
			//	throw new JoshiException(value.getError());
			//}
			return value;
		} catch (IOException iox) {
			throw new JoshiException(iox);
		} finally {
			response.close();
		}
	}
}
