package net.anthavio.joshi.client.cartridges;

import java.util.List;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * 
 * @author martin.vanek
 *
 */
public class CartridgesListResponse extends JoshiResponse<List<CartridgeData>> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
