package net.anthavio.joshi.client.user;

import java.io.Serializable;
import java.util.Date;

import net.anthavio.joshi.client.JsonStringBuilder;
import net.anthavio.joshi.client.api.Capabilities;

/**
 * 
 * @author martin.vanek
 *
 */
public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer consumed_gears;
	private Date created_at;

	private String login;
	private Integer max_gears;

	private String plan_id; //enum - free
	private String plan_state; //enum - ACTIVE

	private String usage_account_id;

	private Capabilities capabilities;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
