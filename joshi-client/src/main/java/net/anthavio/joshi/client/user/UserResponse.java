package net.anthavio.joshi.client.user;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * 
 * @author martin.vanek
 *
 */
public class UserResponse extends JoshiResponse<UserData> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
