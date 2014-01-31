package net.anthavio.joshi.client.api;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import net.anthavio.joshi.client.JoshiResponse;
import net.anthavio.joshi.client.JsonStringBuilder;

/**
 * https://access.redhat.com/site/documentation/en-US/OpenShift/2.0/html/REST_API_Guide/chap-API_Guide-API_Entry_Point.html
 * 
 * @author martin.vanek
 *
 */
@XmlRootElement(name = "response")
public class ApiOperationsResponse extends JoshiResponse<Map<String, ApiOperation>> {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}
}
