package net.anthavio.joshi.client.api;

import java.util.Date;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.api.AuthorizationResponse.TokenData;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author martin.vanek
 *
 */

public class AuthorizationResponse extends JoshiResponse<TokenData> {

	private static final long serialVersionUID = 1L;

	//@JsonIgnoreProperties(ignoreUnknown = true)
	public static class TokenData extends net.anthavio.joshi.client.JoshiResponse.JoshiResponseData {

		private static final long serialVersionUID = 1L;

		@JsonProperty("created_at")
		private Date created_at;

		@JsonProperty("expires_in")
		private Integer expires_in; // same as expires_in_seconds

		@JsonProperty("expires_in_seconds")
		private Integer expires_in_seconds;

		@JsonProperty("id")
		private String id;

		@JsonProperty(value = "identity")
		private String identity; // login (email)

		@JsonProperty("note")
		private String note;

		@JsonProperty("scopes")
		private String scope;

		@JsonProperty("token")
		private String token;

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public Integer getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(Integer expires_in) {
			this.expires_in = expires_in;
		}

		public Integer getExpires_in_seconds() {
			return expires_in_seconds;
		}

		public void setExpires_in_seconds(Integer expires_in_seconds) {
			this.expires_in_seconds = expires_in_seconds;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getIdentity() {
			return identity;
		}

		public void setIdentity(String identity) {
			this.identity = identity;
		}
	}

}
