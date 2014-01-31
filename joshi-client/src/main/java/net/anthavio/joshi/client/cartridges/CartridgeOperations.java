package net.anthavio.joshi.client.cartridges;

import net.anthavio.joshi.client.AbstractOperations;
import net.anthavio.joshi.client.JoshiClient;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Cartridges.html
 * 
 * @author martin.vanek
 *
 */
public class CartridgeOperations extends AbstractOperations {

	public CartridgeOperations(JoshiClient client) {
		super(client);
	}

	/**
	 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Cartridges.html#sect-API_Guide-Cartridges-Framework_Cartridges-List_Cartridges
	 */
	public CartridgesListResponse list() {
		return client.execute(new CartridgesListRequest(client));
	}

	/**
	 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/sect-API_Guide-Cartridges-Embedded_Cartridges.html#sect-API_Guide-Cartridges-Embedded_Cartridges-List_Embedded_Cartridges
	 */
	public CartridgesEmbeddedListResponse listEmbedded(String Domain_ID, String App_Name) {
		return client.execute(new CartridgesEmbeddedListRequest(client, Domain_ID, App_Name));
	}

}
