
public class Course implements Comparable<Course>{
	private String ID;
	private String courseName;
	private Professor lecturer;
	private String days;
	private String startTime;
	private String endTime;
	private int capacity;
	private int stuCount=0;
	public Course(String ID,String courseName, Professor lecturer, 
			String days, String startTime, String endTime, int capacity) {
		this.ID=ID;
		this.courseName=courseName;
		this.lecturer=lecturer;
		this.days=days;
		this.startTime=startTime;
		this.endTime=endTime;
		this.capacity=capacity;
	}
	/**
	 * getter
	 * @return Id
	 */
	public String getID() {
		return ID;
	}
	/**
	 * getter
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * getter
	 * @return professor name
	 */
	public Professor getLecturer() {
		return lecturer;
	}
	/**
	 * getter
	 * @return days
	 */
	public String getDays() {
		return days;
	}
	/**
	 * getter
	 * @return starttime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * getter
	 * @return endtime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * getter
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * getter
	 * @return student count
	 */
	public int getStuCount() {
		return stuCount;
	}
	/**
	 * setter
	 * @param i student count to set to
	 */
	public void setStuCount(int i) {
		this.stuCount=i;
	}
	/**
	 * print all course info
	 * @return String to print
	 */
	public String getAllInfo() {
		String allInfo=(ID+"|"+courseName+", "+startTime+"-"+endTime
				+" on "+days+", with course capacity"+Integer.toString(capacity)+", student: "
				+Integer.toString(stuCount)+", lecturer: "+getLecturer().getName());
		return allInfo;
	}
	/**
	 * method to find course object with id
	 * @param id to find course with
	 * @return course with id 
	 */
	public static Course findCourse(String id) {
		for (Course i:Controller.allCourseList){
			if (i.getID().contentEquals(id))
				return i;
		}
		System.out.println(id+" doesn't exist");
		return null;
	}
	/**
	 * compare course by comparing start time and end time
	 */
	public int compareTo(Course course) {
		Integer startHr=Integer.parseInt(startTime.split(":")[0]);
		Integer endHr=Integer.parseInt(endTime.split(":")[0]);
		int otherStartHr=Integer.parseInt(course.getStartTime().substring(0, 2));
		int otherEndHr=Integer.parseInt(course.getEndTime().substring(0, 2));
		if (days.substring(0, 2).equals(course.getDays().substring(0, 2))) {
			if (startHr==otherStartHr || endHr==otherEndHr) {
				return 0;
			}
		}
		return startHr.compareTo(otherStartHr);
	}
	/**
	 * if a student picks the course
	 */
	public void addStudent(Student s) {
		stuCount+=1;
		lecturer.profCourses.get(this).add(s);
	}
}
