package net.anthavio.joshi.client;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.rest.MethodConfig;

/**
 * @author martin.vanek
 * 
 * @param <B> - generic self
 * @param <R> - response type
 */
public abstract class JoshiRequest<B extends JoshiRequest<?, R>, R extends JoshiResponse<?>> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final MethodConfig<R> config;

	private final JoshiClient client;

	private Long cacheSeconds;

	public JoshiRequest(MethodConfig<R> config, JoshiClient client) {
		if (config == null) {
			throw new IllegalArgumentException("Null config");
		}
		this.config = config;

		if (client == null) {
			throw new IllegalArgumentException("Null client");
		}
		this.client = client;
	}

	public R execute() {
		return client.execute(this);
	}

	public MethodConfig<R> getConfig() {
		return config;
	}

	protected abstract B getSelf(); //Generic self

	public B setCache(int value, TimeUnit unit) {
		this.cacheSeconds = unit.toSeconds(value);
		return getSelf();
	}

	public Long getCacheSeconds() {
		return this.cacheSeconds;
	}

	public SenderRequest buildRequest() {
		SenderRequest request = config.buildRequest();
		addParameters(request);
		return request;
	}

	protected abstract void addParameters(SenderRequest request);

}
