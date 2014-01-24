package net.anthavio.joshi.client;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import net.anthavio.httl.HttpClient4Sender;
import net.anthavio.httl.HttpSender;
import net.anthavio.httl.HttpURLSender;
import net.anthavio.httl.util.CodeGeneratingHandler;
import net.anthavio.joshi.client.JoshiClient;
import net.anthavio.joshi.client.JoshiSettings;
import net.anthavio.joshi.client.api.ApiFunction;
import net.anthavio.joshi.client.api.ApiResponse;
import net.anthavio.joshi.client.cartridges.CartidgesResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author martin.vanek
 *
 */
public class JoshiClientTest {

	public static void main(String[] args) {
		JoshiClientTest test = new JoshiClientTest();

		try {
			test.basic();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	//@Test
	public void basic() throws Exception {

		HttpURLSender sender2 = new HttpURLSender("https://openshift.redhat.com");

		sender2.GET("/broker/rest/api").accept("application/json").execute(new CodeGeneratingHandler());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ApiResponse> entity = restTemplate.getForEntity("https://openshift.redhat.com/broker/rest/api",
				ApiResponse.class);
		Map<String, ApiFunction> data2 = entity.getBody().getData();
		for (ApiFunction af : data2.values()) {
			System.out.println(af.getRel() + " - " + af.getHref());
		}
		//System.out.println(entity);

		ResponseEntity<CartidgesResponse> entity2 = restTemplate.getForEntity(
				"https://openshift.redhat.com/broker/rest/cartridges", CartidgesResponse.class);
		System.out.println(entity2);

		ResponseEntity<Resource<Map>> responseEntity = restTemplate.exchange(
				"https://openshift.redhat.com/broker/rest/api", HttpMethod.GET, null,
				new ParameterizedTypeReference<Resource<Map>>() {
				}, Collections.emptyMap());
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Resource<Map> userResource = responseEntity.getBody();
			Map user = userResource.getContent();
		}

		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/wotan-test.properties"));
		JoshiSettings settings = new JoshiSettings(props.getProperty("username"), props.getProperty("password"));
		HttpSender sender = new HttpClient4Sender(settings.getServerUrl());
		JoshiClient client = new JoshiClient(settings, sender);
		//List<AccountStub> accounts = client.account().list("anthavio").execute().getData();
		//System.out.println(accounts);

		//Map<String, AccountInfo> info = client.account().info(504644777, 504644666);
		//System.out.println(info);
		/*
		Map<String, List<PlayersTank>> tanks = client.account().tanks(504644777);
		List<PlayersTank> tl = tanks.values().iterator().next();
		for (PlayersTank tank : tl) {
			System.out.println(tank);
		}
		*/

		//Ratings type = client.ratings().types().get(RatingType.ALL);
		//System.out.println(type);

		//Map<Long, PlayerRatings> player = client.ratings().player(504644777, RatingType.MONTH);
		//System.out.println(player);

		//List<Ratings> neighbors = client.ratings().neighbors(504644777, RatingType.ALL, "battles_count_rank");
		//System.out.println(neighbors);

	}
}
