package net.anthavio.joshi.client.plan;

import java.util.List;

import net.anthavio.joshi.client.AbstractOperations;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiResponse;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-Subscription_Plans.html
 * 
 * @author martin.vanek
 *
 */
public class PlanOperations extends AbstractOperations {

	public PlanOperations(JoshiClient client) {
		super(client);
	}

	public JoshiResponse<List<PlanData>> list() {
		return client.execute(new PlanListRequest(client));
	}

	public JoshiResponse<PlanData> info(String plan_id) {
		return client.execute(new PlanInfoRequest(client, plan_id));
	}

	public JoshiResponse<PlanData> change(String plan_id) {
		return client.execute(new PlanChangeRequest(client, plan_id));
	}

}
