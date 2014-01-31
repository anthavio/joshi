package net.anthavio.joshi.client.plan;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * 
 * @author martin.vanek
 *
 */
public class PlanInfoResponse extends JoshiResponse<PlanData> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
