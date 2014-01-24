package net.anthavio.joshi.client.api;

import java.util.List;

/**
 * 
 * @author martin.vanek
 *
 */
public class ApiFunction {

	private String rel;

	private String href;

	private String method;

	private List<ApiParam> required_params;

	private List<ApiParam> optional_params;

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<ApiParam> getRequired_params() {
		return required_params;
	}

	public void setRequired_params(List<ApiParam> required_params) {
		this.required_params = required_params;
	}

	public List<ApiParam> getOptional_params() {
		return optional_params;
	}

	public void setOptional_params(List<ApiParam> optional_params) {
		this.optional_params = optional_params;
	}

}