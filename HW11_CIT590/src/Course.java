
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
	public static boolean checkCourseExist(String id) {
		for (Course i:Controller.allCourseList){
			if (i.getID().contentEquals(id))
				return true;
		}
		
		return false;
	}
	/**
	 * compare course by comparing start time and end time
	 */
	public int compareTo(Course course) {
		//get start hour
		Integer startHr=Integer.parseInt(startTime.split(":")[0]);
		//get end hour
		Integer endHr=Integer.parseInt(endTime.split(":")[0]);
		//get start hour of course to compare to
		int otherStartHr=Integer.parseInt(course.getStartTime().split(":")[0]);
		//get end hour of course to compare to
		int otherEndHr=Integer.parseInt(course.getEndTime().split(":")[0]);
		//if days match
		if (days.substring(0, 2).equals(course.getDays().substring(0, 2))) {
			//and if starthr/endhr conflict
			if (!(startHr>=otherEndHr || endHr<=otherStartHr)) {
				//return 0
				return 0;
			}
		}
		//else return any number
		return startHr.compareTo(otherStartHr);
	}
	/**
	 * if a student picks the course
	 */
	public void addStudent(Student s) {
		//check if course if full before adding
		if (!checkFull()) {
			//add student count by 1
			stuCount+=1;
			//add to professor courselist
			lecturer.profCourses.get(this).add(s);
		}else {
			System.out.println("Course already full, cannot add");
		}
	}
	
	/**
	 * if a student picks the course
	 */
	public void removeStudent(Student s) {
		//delete student count by one
		stuCount-=1;
		//delete from professor course student list
		lecturer.profCourses.get(this).remove(s);
	}
	/**
	 * check if course if full
	 * @return
	 */
	public boolean checkFull() {
		//if student count is at capacity
		if (stuCount==capacity) {
			System.out.println(this.getID()+"course is full");
			return true;
		}
		return false;
	}
}
