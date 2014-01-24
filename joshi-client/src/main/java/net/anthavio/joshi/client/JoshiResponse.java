package net.anthavio.joshi.client;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author martin.vanek
 *
 */
public class JoshiResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("api_version")
	private String api_version;

	@JsonProperty("version")
	private String version;

	@JsonProperty("supported_api_versions")
	private List<String> supported_api_versions;

	@JsonProperty("data")
	private T data;

	@JsonProperty("messages")
	private List<Message> messages;

	@JsonProperty("status")
	private String status;//enum... "ok"

	@JsonProperty("type")
	private String type; //enum...

	@JsonProperty("errors")
	private Map errors;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getSupported_api_versions() {
		return supported_api_versions;
	}

	public void setSupported_api_versions(List<String> supported_api_versions) {
		this.supported_api_versions = supported_api_versions;
	}

	public String getApi_version() {
		return api_version;
	}

	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	public static class Message implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer exit_code;

		private String field;

		private String index;

		private String severity; //enum info

		private String text;

		public Integer getExit_code() {
			return exit_code;
		}

		public void setExit_code(Integer exit_code) {
			this.exit_code = exit_code;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		public String getSeverity() {
			return severity;
		}

		public void setSeverity(String severity) {
			this.severity = severity;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}
}
