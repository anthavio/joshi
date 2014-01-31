package net.anthavio.joshi.client.cartridges;

import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.rest.MethodConfig;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiRequest;

import org.apache.commons.lang.StringUtils;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Cartridges.html#sect-API_Guide-Cartridges-Framework_Cartridges-List_Cartridges
 * 
 * @author martin.vanek
 *
 */
public class CartridgesEmbeddedListRequest extends
		JoshiRequest<CartridgesEmbeddedListRequest, CartridgesEmbeddedListResponse> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<CartridgesEmbeddedListResponse> config = //
	MethodConfig.GET("/broker/rest/domains/{Domain_ID}/applications/{App_Name}/cartridges",
			CartridgesEmbeddedListResponse.class);

	private String domain;

	private String application;

	public CartridgesEmbeddedListRequest(JoshiClient client, String domain, String application) {
		super(config, client);

		if (StringUtils.isEmpty(domain)) {
			throw new IllegalArgumentException("Empty domain");
		}
		this.domain = domain;

		if (StringUtils.isEmpty(application)) {
			throw new IllegalArgumentException("Empty application");
		}
		this.application = application;
	}

	@Override
	protected CartridgesEmbeddedListRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {
		request.setUrlParam("Domain_ID", domain);
		request.setUrlParam("App_Name", application);
	}

}
