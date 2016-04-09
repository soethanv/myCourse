package net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Course;
 
 
public class Crawler {
	private String major;
	public Crawler(String major) throws MalformedURLException{
		this.major = major;
	}
	//maps course to list of prerequisites in the format:
	//		"CS 2312"->[CS 1111, CS 2222]
	public Map<String, List<Course>> findPrerequisites(String URL) {
		Map<String, List<Course>> output = new TreeMap<String, List<Course>>();
		try {
			List<String> info = processPage(URL);
			String lastCourse = "";
			for(String line : info) {
				if(line.matches("^\\W*CS.*")) {
					lastCourse = line.substring(0, line.indexOf('.'));
					if(lastCourse.length() > 10) continue;
					lastCourse = lastCourse.replaceAll("\\W", "");
					output.put(lastCourse, null);
				} else if(line.matches("^Prerequisite.*")){
					line = line.substring(0, line.indexOf("."));
					List<Course> prereqs = new ArrayList<Course>();
					String[] fields = line.split(" ");
					for(int x = 0; x < fields.length; x++) {
						if(Course.isDicipline(fields[x])) {
							Course course = new Course();
							course.setDicipline(fields[x++]);
							course.setNumber(fields[x].replaceAll("\\D+", ""));
							prereqs.add(course);
						}
					}
					output.put(lastCourse, prereqs);
				}
			}
			String[] keySet = output.keySet().toArray(new String[0]);
			for(int i = 0; i < keySet.length; i++) {
				List<Course> prereqs = output.get(keySet[i]);
				if(prereqs == null) continue;
				for(int x = 0; x < prereqs.size(); x++) {
					String tmp = prereqs.get(x).getDicipline() + " " + prereqs.get(x).getNumber();
					if(output.containsKey(tmp)) {
						prereqs.get(x).setPrerequisites(output.get(prereqs.get(x)));
					}
				}
				output.put(keySet[i], prereqs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	@SuppressWarnings("finally")
	public List<String> processPage(String URL) throws IOException{
		Connection connection = Jsoup.connect(URL);
		List<String> results = new ArrayList<String>();
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