package problems;

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

public class ParalelWebCrawler {
	public List<String> scannedPages = new ArrayList<String>();
	public String content;
	public Queue<URL> q = new LinkedList<URL>();
	public URL homepage;
	public boolean flag = true;
	public int count = 1;

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void increment() {
		count++;
	}

	public int getCount() {
		return count;
	}

	public ParalelWebCrawler(URL url) {
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

	public synchronized void crawlPage(URL url) throws IOException {
		content = getContent(url);
		{
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
	}

	public Queue<URL> getQueue() {
		return q;
	}

	public String getDomainName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}

	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		URL url;
		url = new URL("http://sportal.bg");
		ParalelWebCrawler c = new ParalelWebCrawler(url);
		c.crawlPage(url);
		Queue<URL> q = c.getQueue();
		String name1 = "T1";
		String name2 = "T2";
		Thread t1 = new Thread(new MyThread1(q, c, name1));
		Thread t2 = new Thread(new MyThread2(q, c, name2));
		t1.start();
		t2.start();
	}

}

class MyThread1 implements Runnable {
	Queue<URL> q;
	ParalelWebCrawler c;
	String name;

	public MyThread1(Queue<URL> q, ParalelWebCrawler c, String name) {
		this.q = q;
		this.c = c;
		this.name = name;
	}

	public Queue<URL> FirstHalfQueue() {
		Queue<URL> result = new LinkedList<URL>();
		int HalfSize = q.size() / 2;
		while (q.size() != HalfSize)
			result.add(q.poll());
		return result;
	}

	public void setHalfQueue() {
		q = FirstHalfQueue();
	}

	public void run() {
		setHalfQueue();
		while (!q.isEmpty()) {
			System.out.println(this.name + ": " + c.getCount() + " "
					+ q.remove());
			if (!q.peek().toString().contains("www.legendarysurvey.com")) {
				try {
					c.crawlPage(q.remove());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			c.increment();
		}

	}
}

class MyThread2 implements Runnable {
	Queue<URL> q;
	ParalelWebCrawler c;
	String name;

	public MyThread2(Queue<URL> q, ParalelWebCrawler c, String name) {
		this.q = q;
		this.c = c;
		this.name = name;
	}

	public Queue<URL> SecondHalfQueue() {
		int HalfSize = q.size() / 2;
		while (q.size() != HalfSize)
			q.remove();
		return q;
	}

	public void setHalfQueue() {
		q = SecondHalfQueue();
	}

	public void run() {
		setHalfQueue();
		while (!q.isEmpty()) {
			System.out.println(this.name + ": " + c.getCount() + " "
					+ q.remove());
			if (!q.peek().toString().contains("www.legendarysurvey.com")) {
				try {
					c.crawlPage(q.remove());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			c.increment();
		}

	}
}
