package net.anthavio.joshi.client.applications;

import net.anthavio.joshi.client.AbstractGroup;
import net.anthavio.joshi.client.JoshiClient;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Applications.html
 * 
 * @author martin.vanek
 *
 */
public class ApplicationsGroup extends AbstractGroup {

	public ApplicationsGroup(JoshiClient client) {
		super(client);
	}

}
