package net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Crawler {
	private String major;
	public Crawler(String major) throws MalformedURLException{
		this.major = major;
	}
	@SuppressWarnings("finally")
	public List<String> processPage(String URL) throws IOException{
		Connection connection = Jsoup.connect(URL);
		List<String> results = new ArrayList<String>();
		System.err.println("Scanning " + URL);
		try{
			Document doc = connection.get();
			Elements parElems = doc.select("p");
			results.addAll(scanElems(parElems));
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("catalog.utsa.edu")) {
					String next = link.attr("abs:href");
					if(filter(next))
						processPage(link.attr("abs:href"));
				}
			}
		}catch(HttpStatusException httpe) {
		}catch(UnsupportedMimeTypeException umte) {
		}catch(SocketTimeoutException timeoute) {
		}catch(UnknownHostException uhe) {
		}finally{
			return results;
		}
	}
	private Collection<String> scanElems(Elements elems) {
		Collection<String> results = new ArrayList<String>();
		for(Element el : elems) {
			String text = el.text();
			if(text.contains(major) || text.matches("^Prerequisite[s]?:.*\\.")) {
				results.add(text);
			}
		}
		return results;
	}
	private boolean filter(String URL) {
		return URL.matches("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$");
	}
}