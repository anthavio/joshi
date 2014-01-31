package net.anthavio.joshi.client.api;

import java.io.Serializable;
import java.util.List;

import net.anthavio.joshi.client.JsonStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author martin.vanek
 *
 */
public class Capabilities implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private Boolean subaccounts;

	@JsonProperty
	private List<GearSize> gear_sizes;

	@JsonProperty
	private Boolean plan_upgrade_enabled;

	@JsonProperty
	private Integer max_storage_per_gear;

	@JsonProperty
	private Boolean private_ssl_certificates;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
