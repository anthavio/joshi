package net.anthavio.joshi.client.plan;

import net.anthavio.httl.SenderRequest;
import net.anthavio.httl.rest.MethodConfig;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiRequest;

/**
 * 
 * @author martin.vanek
 *
 */
public class PlanInfoRequest extends JoshiRequest<PlanInfoRequest, PlanInfoResponse> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<PlanInfoResponse> config = //
	MethodConfig.GET("/broker/rest/plans/{Plan_ID}", PlanInfoResponse.class);

	private String planId;

	public PlanInfoRequest(JoshiClient client, String planId) {
		super(config, client);
		this.planId = planId;
	}

	@Override
	protected PlanInfoRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {
		request.setUrlParam("Plan_ID", planId);
	}

}
