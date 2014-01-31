package net.anthavio.joshi.client.cartridges;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * 
 * @author martin.vanek
 *
 */
public class CartridgeData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type; //enum standalone, embedded
	private String name;
	private String display_name;
	private String version;
	private String license;
	private String license_url;
	private String website;

	private Map<String, String> help_topics;

	private String description;

	private Integer current_scale;
	private Integer scales_from;
	private Integer scales_to;
	private String scales_with;
	private Integer supported_scales_from;
	private Integer supported_scales_to;
	private List<String> tags;
	private List<Map> usage_rates; //usage_rate_usd 

	private Boolean obsolete;
	private String url;

	//status_messages;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
