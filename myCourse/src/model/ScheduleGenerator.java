package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleGenerator {
	
	private List<Course> available;
	
	public ScheduleGenerator(List<Course> availableCourses) {
		this.available = availableCourses;
	}
	
	public List<List<Course>> generateSchedules(List<Course> selectedCourses) {
		List<List<Course>> output = new ArrayList<List<Course>>();
		Map<Course, Course[]> category = new HashMap<Course, Course[]>();
		List<Course> tmp = new ArrayList<Course>(available);
		for(Course filter : selectedCourses) {
			tmp.retainAll(Arrays.asList(filter));
			category.put(filter, tmp.toArray(new Course[0]));
			tmp = new ArrayList<Course>(available);
		}
		int[] counters = new int[selectedCourses.size()];
		while((counters = inc(counters, category)) != null){
			boolean goodGeneration = true;
			List<Course> generation = new ArrayList<Course>();
			for(int x = 0; x < selectedCourses.size(); x++) {
				generation.add(category.get(selectedCourses.get(x))[counters[x]]);
			}
			for(int x = 0; x < generation.size() -1; x++) {
				if(overlap(generation.get(x), generation.get(x+1))) {
					goodGeneration = false;
					break;
				}
			}
			if(goodGeneration) output.add(generation);
		}
		
		return output;
	}
	
	private int[] inc(int[] counters, Map<Course, Course[]> map) {
		Course[] keySet = map.keySet().toArray(new Course[0]);
		int i = 0;
		boolean carry;
		int[] output = counters;
		do{
			carry = false;
			output[i]++;
			if(output[i] >= map.get(keySet[i]).length) {
				output[i] = 0;
				carry = true;
				if(i + 1 >= output.length)
					return null;
			}
		}while(carry);
		return output;
	}
	
	private boolean overlap(Course course1, Course course2) {
		//check for overlapping dates
		for(char c : course1.getMeetingDays().toCharArray()) {
			if(course2.getMeetingDays().indexOf(c) >= 0) {
				return false;
			}
		}
		//check for overlapping times
		TimeRange t1 = new TimeRange(course1.getMeetingTimes());
		TimeRange t2 = new TimeRange(course2.getMeetingTimes());
		if(t1.getStartMinutes() < t2.getStartMinutes() && t1.getEndMinutes() > t2.getStartMinutes() ||
				t2.getStartMinutes() < t1.getStartMinutes() && t2.getEndMinutes() > t1.getStartMinutes()) return false;
		return true;
	}
}
class TimeRange {
	private int startMinutes;
	private int endMinutes;
	public TimeRange(String raw) {
		translate(raw);
	}
	//TODO
	private void translate(String raw) {
		String[] fields = raw.split("-");
		String[] firstTime = fields[0].split(":");
		String[] secondTime = fields[1].split(":");
		startMinutes = Integer.parseInt(firstTime[0]) * 60;
		endMinutes = Integer.parseInt(secondTime[0]) * 60;
		startMinutes += Integer.parseInt(firstTime[1]);
		endMinutes += Integer.parseInt(secondTime[1].substring(0, secondTime[1].length()-2));
		if(fields[1].contains("pm")) {
			if(startMinutes > endMinutes) {
				endMinutes += 12*60;
			} else {
				startMinutes += 12*60;
				endMinutes += 12*60;
			}
		}
	}
	public int getStartMinutes() {
		return startMinutes;
	}
	public int getEndMinutes() {
		return endMinutes;
	}
}
