import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCrawler {
	public List<String> scannedPages = new ArrayList<String>();
	public String content;
	public Queue<URL> q = new LinkedList<URL>();
	public URL homepage;

	public WebsiteCrawler(URL url) {
		homepage = url;
	}

	private static List<String> getAllLinks(String content) {
		ArrayList<String> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			resultList.add(matcher.group(1));
		}
		return resultList;
	}

	private String contentToStr(HttpURLConnection con) {
		StringBuilder sb = new StringBuilder();
		if (con != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String input;
				while ((input = br.readLine()) != null) {
					sb.append(input);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

	public String getContent(URL url) throws NullPointerException, IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		return contentToStr(con);

	}

	public URL prepareUrl(URL url, String link) throws MalformedURLException {
		URL result = null;
		if (!link.startsWith("http"))
			result = new URL(url + "/" + link);
		else
			result = new URL(link);
		return result;

	}

	public boolean crawlPage(URL url, String needle) throws IOException {
		content = getContent(url);
		if (content.contains(needle)) {
			System.out.println(url);
			return true;
		} else {
			List<String> links = getAllLinks(content);
			for (String link : links) {
				if (!link.contains("..")) {
					if (!scannedPages.contains(link)
							&& (url + "/" + link).startsWith(homepage
									.toString())) {
						q.add(prepareUrl(url, link));
						scannedPages.add(link);
					}
				}
			}

		}
		return false;
	}

	public String getDomainName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}

	public void crawlWebsite(String needle) throws IOException,
			URISyntaxException {
		crawlPage(homepage, needle);
		while (!q.isEmpty()) {
			if (crawlPage(q.remove(), needle))
				return;
		}
	}

	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://ebusiness.free.bg");
			String needle = "Револвираща";
			WebsiteCrawler c = new WebsiteCrawler(url);
			try {
				c.crawlWebsite(needle);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

	}

}
