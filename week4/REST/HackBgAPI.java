import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class HackBgAPI {
	public static String getContent(String url) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException, JSONException {
		String url = "https://hackbulgaria.com/api/students/";
		String[] jObjects = getContent(url).split("\"}, ");
		int i = 0;
		String students = "";
		for (String student : jObjects) {
			students = student + "\"}";
			JSONObject j = new JSONObject(students.substring(students.indexOf('{')));
			JSONArray jar = j.getJSONArray("courses");

			if (jar.length() > 1) {
				System.out.println(++i + " " + j.getString("name")
						+ j.getString("courses") + "\n");
			}
		}

	}

}
