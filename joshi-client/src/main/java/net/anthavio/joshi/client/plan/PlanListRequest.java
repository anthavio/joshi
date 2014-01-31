package net.anthavio.joshi.client.plan;

import java.util.List;

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
public class PlanListRequest extends JoshiRequest<PlanListRequest, JoshiResponse<List<PlanData>>> {

	private static final long serialVersionUID = 1L;

	private static MethodConfig<JoshiResponse<List<PlanData>>> config = //
	MethodConfig.GET("/broker/rest/plans", new GenericType<JoshiResponse<List<PlanData>>>() {
	});

	public PlanListRequest(JoshiClient client) {
		super(config, client);
	}

	@Override
	protected PlanListRequest getSelf() {
		return this;
	}

	@Override
	protected void addParameters(SenderRequest request) {
		//no paramaters
	}

}
