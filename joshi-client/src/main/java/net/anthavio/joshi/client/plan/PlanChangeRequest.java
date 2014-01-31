package net.anthavio.joshi.client.plan;

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
public class PlanChangeRequest extends JoshiRequest<PlanChangeRequest, JoshiResponse<PlanData>> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<JoshiResponse<PlanData>> config = //
	MethodConfig.PUT("/broker/rest/user", new GenericType<JoshiResponse<PlanData>>() {
	});

	private String planId;

	public PlanChangeRequest(JoshiClient client, String planId) {
		super(config, client);
		this.planId = planId;
	}

	@Override
	protected PlanChangeRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {
		request.setParameter("plan_id", planId);
	}

}
