package net.anthavio.joshi.client.api;

import java.util.List;

import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * 
 * @author martin.vanek
 *
 */
public class ApiParam {

	private String name;

	private String type; //enum string, integer, boolean

	private String default_value;

	private String description;

	private List<String> valid_options;

	private List<String> invalid_options;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getValid_options() {
		return valid_options;
	}

	public void setValid_options(List<String> valid_options) {
		this.valid_options = valid_options;
	}

	public List<String> getInvalid_options() {
		return invalid_options;
	}

	public void setInvalid_options(List<String> invalid_options) {
		this.invalid_options = invalid_options;
	}

}
