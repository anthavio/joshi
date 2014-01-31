package net.anthavio.joshi.client.user;

import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.rest.MethodConfig;
import net.anthavio.httl.util.GenericType;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiRequest;
import net.anthavio.joshi.client.JoshiResponse;

/**
 * 
 * @author martin.vanek
 *
 */
public class UserRequest extends JoshiRequest<UserRequest, JoshiResponse<UserData>> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<JoshiResponse<UserData>> config = //
	MethodConfig.GET("/broker/rest/user", new GenericType<JoshiResponse<UserData>>() {
	});

	public UserRequest(JoshiClient client) {
		super(config, client);
	}

	@Override
	protected UserRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {
		//no paramaters
	}

}
