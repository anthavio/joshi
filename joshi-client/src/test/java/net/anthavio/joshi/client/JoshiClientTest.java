package net.anthavio.joshi.client;

import java.util.List;
import java.util.Properties;

import net.anthavio.httl.HttpClient4Config;
import net.anthavio.httl.HttpClient4Sender;
import net.anthavio.httl.HttpSender;
import net.anthavio.joshi.client.plan.PlanData;

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

		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/joshi-test.properties"));
		JoshiSettings settings = new JoshiSettings(props.getProperty("username"), props.getProperty("password"));
		//settings.setUseAuthToken(true);
		HttpClient4Config config = new HttpClient4Config(settings.getServerUrl());
		HttpSender sender = new HttpClient4Sender(config);
		JoshiClient client = new JoshiClient(settings, sender);

		//System.out.println(client.api().getData());

		//System.out.println(client.cartridges().list().getData());

		//System.out.println(client.cartridges().listEmbedded("anthavio", "wotan"));

		//System.out.println(client.users().view());

		JoshiResponse<List<PlanData>> list = client.plans().list();
		System.out.println(list);

		JoshiResponse<PlanData> respo = client.plans().info("silver");
		System.out.println(respo);

	}
}
