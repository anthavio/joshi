package net.anthavio.joshi.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.anthavio.cache.CacheBase;
import net.anthavio.httl.HttpSender;
import net.anthavio.httl.HttpURLSender;
import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.SenderResponse;
import net.anthavio.httl.cache.CachedResponse;
import net.anthavio.httl.cache.CachingSender;
import net.anthavio.joshi.client.applications.ApplicationsGroup;
import net.anthavio.joshi.client.cartridges.CartridgesGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
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

		this.mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null)).addDeserializer(MyType.class,
		//		new MyTypeDeserializer());
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

	public ApplicationsGroup applications() {
		return new ApplicationsGroup(this);
	}

	public CartridgesGroup cartridges() {
		return new CartridgesGroup(this);
	}

	public <T extends JoshiResponse<?>> T execute(JoshiRequest<?, T> request) {
		SenderRequest sr = request.buildRequest();
		//sr.addParameter("application_id", settings.getApplicationId());

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
			T value = mapper.readValue(response.getReader(), request.getConfig().getResponseClass());
			//if (value.getStatus() == Status.error) {
			//	throw new JoshiException(value.getError());
			//}
			return value;
		} catch (IOException iox) {
			throw new JoshiException(iox);
		}
	}

}