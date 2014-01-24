package net.anthavio.joshi.web;

import java.io.Serializable;

import net.anthavio.cache.CacheBase;
import net.anthavio.cache.HeapMapCache;
import net.anthavio.httl.cache.CachedResponse;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiSettings;

/**
 * 
 * Session data abstraction
 * 
 * @author martin.vanek
 *
 */
public class SessionData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accessToken;

	private transient JoshiClient client;

	public JoshiClient initClient(String username, String password, long cacheSeconds) {
		JoshiClient wotanClient;
		JoshiSettings settings = new JoshiSettings(username, password);
		if (cacheSeconds > 0) {
			settings.setCacheSeconds(cacheSeconds);
			CacheBase<CachedResponse> cache = new HeapMapCache<CachedResponse>();
			wotanClient = new JoshiClient(settings, cache);
		} else {
			//do not use any caching then....
			wotanClient = new JoshiClient(settings);
		}

		//validate via executing rest call
		//XXX wotanClient.ratings().types().execute().getData();
		//if no exception is thrown....
		if (this.client != null) {
			//kill existing
			this.client.close();
		}

		this.client = wotanClient;
		return wotanClient;
	}

	public JoshiClient getClient() {
		return client;
	}

}
