package net.anthavio.joshi.client.cartridges;

import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.rest.MethodConfig;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiRequest;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Cartridges.html#sect-API_Guide-Cartridges-Framework_Cartridges-List_Cartridges
 * 
 * @author martin.vanek
 *
 */
public class CartridgesListRequest extends JoshiRequest<CartridgesListRequest, CartridgesListResponse> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<CartridgesListResponse> config = //
	MethodConfig.GET("/broker/rest/cartridges", CartridgesListResponse.class);

	public CartridgesListRequest(JoshiClient client) {
		super(config, client);
	}

	@Override
	protected CartridgesListRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {

	}

}
