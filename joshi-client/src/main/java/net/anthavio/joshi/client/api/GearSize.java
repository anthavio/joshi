package net.anthavio.joshi.client.api;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author martin.vanek
 *
 */
public enum GearSize {

	small("small"), medium("medium"), large("large"), gigabyte("gigabyte"), jbosseap6("jbosseap-6");

	private final String value;

	private GearSize(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}
}
