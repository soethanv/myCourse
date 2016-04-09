package model;

import java.util.List;

public class Course {
	public static final String COURSE_CS = "CS";
	public static final String COURSE_BIO = "BIO";
	public static final String COURSE_MAT = "MAT";
	public static final String COURSE_ACC = "ACC";
	public static final String COURSE_ANT = "ANT";
	public static final String COURSE_ARC = "ARC";
	public static final String COURSE_ART = "ART";
	
	private String dicipline;
	private String number;
	private String section;
	private String crn;
	private String description;
	private int hours;
	private String meetingDays;
	private String meetingTimes;
	private List<Course> prerequisites;
	
	public Course() {
		
	}
	
	public static boolean isDicipline(String str) {
		return str.equals(COURSE_ACC) || str.equals(COURSE_ANT) || 
				str.equals(COURSE_ARC) || str.equals(COURSE_ART) || 
				str.equals(COURSE_BIO) || str.equals(COURSE_CS) || 
				str.equals(COURSE_MAT);
	}

	/**
	 * @return the dicipline
	 */
	public String getDicipline() {
		return dicipline;
	}

	/**
	 * @param dicipline the dicipline to set
	 */
	public void setDicipline(String dicipline) {
		this.dicipline = dicipline;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the crn
	 */
	public String getCrn() {
		return crn;
	}

	/**
	 * @param crn the crn to set
	 */
	public void setCrn(String crn) {
		this.crn = crn;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * @param meetingDays the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * @return the meetingTimes
	 */
	public String getMeetingTimes() {
		return meetingTimes;
	}

	/**
	 * @param meetingTimes the meetingTimes to set
	 */
	public void setMeetingTimes(String meetingTimes) {
		this.meetingTimes = meetingTimes;
	}

	/**
	 * @return the courseCs
	 */
	public static String getCourseCs() {
		return COURSE_CS;
	}

	/**
	 * @return the courseBio
	 */
	public static String getCourseBio() {
		return COURSE_BIO;
	}

	/**
	 * @return the courseMat
	 */
	public static String getCourseMat() {
		return COURSE_MAT;
	}

	/**
	 * @return the prerequisites
	 */
	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	/**
	 * @param prerequisites the prerequisites to set
	 */
	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
}
