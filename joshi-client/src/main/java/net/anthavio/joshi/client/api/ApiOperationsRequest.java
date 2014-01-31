package net.anthavio.joshi.client.api;

import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.rest.MethodConfig;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiRequest;

/**
 * 
 * @author martin.vanek
 *
 */
public class ApiOperationsRequest extends JoshiRequest<ApiOperationsRequest, ApiOperationsResponse> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<ApiOperationsResponse> config = //
	MethodConfig.GET("/broker/rest/api", ApiOperationsResponse.class);

	public ApiOperationsRequest(JoshiClient client) {
		super(config, client);
	}

	@Override
	protected ApiOperationsRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {

	}

}
