package net.anthavio.joshi.client.plan;

import java.util.List;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * 
 * @author martin.vanek
 *
 */
public class PlanListResponse extends JoshiResponse<List<PlanData>> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
