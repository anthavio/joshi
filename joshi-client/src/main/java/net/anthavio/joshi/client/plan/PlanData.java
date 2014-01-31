package net.anthavio.joshi.client.plan;

import java.io.Serializable;
import java.util.Map;

import net.anthavio.joshi.client.JsonStringBuilder;
import net.anthavio.joshi.client.api.Capabilities;
import net.anthavio.joshi.client.api.GearSize;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author martin.vanek
 *
 */
public class PlanData implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String id;

	@JsonProperty
	private String name;

	@JsonProperty
	private Integer plan_no;

	@JsonProperty
	private PlanCapabilities capabilities;

	@JsonProperty
	private Map<String, Map<GearSize, UsageRate>> usage_rates;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

	public static class PlanCapabilities extends Capabilities {

		private static final long serialVersionUID = 1L;

		@JsonProperty
		private Integer max_gears;

		@JsonProperty
		private Integer max_untracked_addtl_storage_per_gear;

		@JsonProperty
		private Integer max_tracked_addtl_storage_per_gear;

	}

	public static class UsageRate implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty
		private Float usd;

		@JsonProperty
		private Float cad;

		@JsonProperty
		private Float eur;

		@JsonProperty
		private String duration;

		public Float getUsd() {
			return usd;
		}

		public void setUsd(Float usd) {
			this.usd = usd;
		}

		public Float getCad() {
			return cad;
		}

		public void setCad(Float cad) {
			this.cad = cad;
		}

		public Float getEur() {
			return eur;
		}

		public void setEur(Float eur) {
			this.eur = eur;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

	}

}
