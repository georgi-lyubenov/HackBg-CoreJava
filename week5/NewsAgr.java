import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Servlet implementation class NewsAgregator
 */
@WebServlet("/NewsAgregator")
public class NewsAgr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		URL url = new URL("http://www.sportal.bg/uploads/rss_category_2.xml");
		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed;
		try {
			feed = input.build(new XmlReader(httpcon));
			List entries = feed.getEntries();
			Iterator itEntries = entries.iterator();
			while (itEntries.hasNext()) {
				SyndEntry entry = (SyndEntry) itEntries.next();
				response.getWriter().println("<a href= entry.getTitle()><h1>" + entry.getTitle() + "</h1></a>");
				response.getWriter().println("<p>" + entry.getPublishedDate() + "</p>");
				response.getWriter().println("<p>" + entry.getDescription().getValue() + "</p>");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		}

	}


}
