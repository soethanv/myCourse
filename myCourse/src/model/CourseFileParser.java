/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Zarmbinski
 *
 */
public class CourseFileParser {
	private String filename;
	public CourseFileParser(String filename) {
		this.filename = filename;
	}
	public List<Course> parse() {
		List<Course> courses = new ArrayList<Course>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null) {
				Course course = new Course();
				String[] fields = line.split("\\|");
				course.setDicipline(fields[0]);
				course.setNumber(fields[1]);
				course.setSection(fields[2]);
				course.setCrn(fields[3]);
				course.setDescription(fields[4]);
				course.setHours(Integer.parseInt(fields[5]));
				course.setMeetingDays(fields[6]);
				course.setMeetingTimes(fields[7]);
				courses.add(course);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return courses;
	}
}
