package net.anthavio.joshi.client.cartridges;

import java.util.List;
import java.util.Map;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.JsonStringBuilder;
import net.anthavio.joshi.client.api.GearSize;
import net.anthavio.joshi.client.cartridges.CartridgesEmbeddedListResponse.EmbeddedCartidge;

/**
 * 
 * @author martin.vanek
 *
 */
public class CartridgesEmbeddedListResponse extends JoshiResponse<List<EmbeddedCartidge>> {

	private static final long serialVersionUID = 1L;

	public static class EmbeddedCartidge extends CartridgeData {

		private static final long serialVersionUID = 1L;
		private Integer additional_gear_storage;
		private Integer base_gear_storage;
		private List<String> collocated_with;
		private GearSize gear_profile;
		private List<Map> properties;

		@Override
		public String toString() {
			return JsonStringBuilder.toString(this, true);
		}

	}
}
