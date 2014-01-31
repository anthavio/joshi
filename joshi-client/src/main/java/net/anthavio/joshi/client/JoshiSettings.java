package net.anthavio.joshi.client;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author martin.vanek
 *
 */
public class JoshiSettings {

	private final String serverUrl;

	private final String username;

	private final String password;

	private boolean useAuthToken; //https://www.openshift.com/developers/rest-api-tutorial

	private Long cacheSeconds;

	public JoshiSettings(String username, String password) {
		this("https://openshift.redhat.com", username, password);
	}

	public JoshiSettings(String serverUrl, String username, String password) {
		if (serverUrl == null || serverUrl.length() == 0) {
			throw new IllegalArgumentException("Blank serverUrl");
		}
		if (!serverUrl.startsWith("http")) {
			serverUrl = "http://" + serverUrl;
		}
		try {
			new URL(serverUrl);
		} catch (MalformedURLException mux) {
			throw new IllegalArgumentException("Invalid server url " + serverUrl, mux);
		}
		this.serverUrl = serverUrl;

		if (username == null || username.length() == 0) {
			throw new IllegalArgumentException("Blank username");
		}
		this.username = username;

		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Blank password");
		}
		this.password = password;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Long getCacheSeconds() {
		return cacheSeconds;
	}

	public void setCacheSeconds(Long cacheSeconds) {
		this.cacheSeconds = cacheSeconds;
	}

	public boolean isUseAuthToken() {
		return useAuthToken;
	}

	public void setUseAuthToken(boolean useAuthToken) {
		this.useAuthToken = useAuthToken;
	}

}
