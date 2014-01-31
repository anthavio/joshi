package net.anthavio.joshi.client.user;

import net.anthavio.joshi.client.AbstractOperations;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiResponse;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-User_Information.html
 * 
 * @author martin.vanek
 *
 */
public class UserOperations extends AbstractOperations {

	public UserOperations(JoshiClient client) {
		super(client);
	}

	/**
	 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-User_Information.html#sect-API_Guide-User_Information-View_User_Information
	 */
	public JoshiResponse<UserData> view() {
		return client.execute(new UserRequest(client));
	}

}
