package model;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import net.Crawler;

public class PrereqTester {
	public static void main(String[] args) throws MalformedURLException {
		//parse the file
		CourseFileParser parser = new CourseFileParser("V:\\git\\Uyphuli\\myCourse\\src\\model\\classes.txt");
		List<Course> courses = parser.parse();
		//resolve prereqs
		Crawler crawler = new Crawler(Course.COURSE_CS);
		Map<String, List<Course>> prereqs = crawler.findPrerequisites("http://catalog.utsa.edu/undergraduate/sciences/computerscience/");
		System.out.println(prereqs);
		for(int x = 0; x < courses.size(); x++) {
			String key = courses.get(x).getDicipline() + courses.get(x).getNumber();
			List<Course> preq = prereqs.get(key);
			courses.get(x).setPrerequisites(preq);
		}
		for(Course course : courses) {
			System.out.print(String.format("Course %s %s.%s\n\tCRN: %s\n\t%s\n\tHours: %d\n\tMeets %s from %s\n\tPrerequisits: ", 
					course.getDicipline(), course.getNumber(), course.getSection(), course.getCrn(), course.getDescription(), course.getHours(),
					course.getMeetingDays(), course.getMeetingTimes()));
			List<Course> preq = course.getPrerequisites();
			if(preq == null || preq.size() == 0) System.out.println("None");
			else {
				for(Course pq : preq)
					System.out.print(pq.getDicipline() + " " + pq.getNumber() + " ");
			}
			System.out.println();
		}
		//user selects courses
		//generate possible schedules
	}
}
