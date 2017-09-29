package cx.myhome.ckoshien.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class PushbulletClient {
	private CloseableHttpClient client;
	private Gson gson;
	private String URL = "https://api.pushbullet.com/v2";
	public PushbulletClient(String api_key) {
		// Carries the api key that verifies with the API
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("api.pushbullet.com", 443), new UsernamePasswordCredentials(api_key, null));
		this.client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		this.gson = new Gson();
		//this.devices = this.listDevices();
		//this.currentUser = this.updateCurrentUser();
	}

	public Push sendPush(String type, String title, String body, String url,
			String file_name, String file_type, String file_url,
			String source_device_iden, String device_iden,
			String client_iden, String channel_tag, String email, String guid){
			List<NameValuePair> nameValuePairs = new ArrayList<>();
			if(type.equals("link")) {
				nameValuePairs.add(new BasicNameValuePair("title", title));
				nameValuePairs.add(new BasicNameValuePair("body", body));
				nameValuePairs.add(new BasicNameValuePair("url", url));
				return gson.fromJson(post(URL + "/pushes", nameValuePairs), Push.class);
			} else if(type.equals("note")) {
				nameValuePairs.add(new BasicNameValuePair("type", "note"));
				nameValuePairs.add(new BasicNameValuePair("title", title));
				nameValuePairs.add(new BasicNameValuePair("body", body));
				nameValuePairs.add(new BasicNameValuePair("channel_tag", channel_tag));
				return gson.fromJson(post(URL + "/pushes", nameValuePairs), Push.class);
			} else {
				return null;
				//throw new Exception("Invalid push type!");
			}
	}

	private String post(String path, List<NameValuePair> nameValuePairs) {
		HttpPost post = new HttpPost(path);
		String result = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = client.execute(post);
			//logger.info("Status: {}", response.getStatusLine());
			result = collectResponse(response);
		} catch (IOException e) {
			//logger.error("While posting", e);
		}
		return result;
	}

	private String collectResponse(HttpResponse response) {
		StringBuilder result = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
			for (String line; (line = br.readLine()) != null; ) {
				result.append(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
