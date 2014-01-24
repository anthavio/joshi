package net.anthavio.joshi.client.cartridges;

import net.anthavio.joshi.client.AbstractGroup;
import net.anthavio.joshi.client.JoshiClient;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Cartridges.html
 * 
 * @author martin.vanek
 *
 */
public class CartridgesGroup extends AbstractGroup {

	public CartridgesGroup(JoshiClient client) {
		super(client);
	}

	/**
	 * TODO https://eu.wargaming.net/developers/api_reference/wot/encyclopedia/tanks/
	 */
	public void tanks() {

	}

	/**
	 * TODO https://eu.wargaming.net/developers/api_reference/wot/encyclopedia/tankinfo/
	 */
	public void tankinfo() {

	}

}
