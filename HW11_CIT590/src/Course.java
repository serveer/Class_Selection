
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
	public String getID() {
		return ID;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getLecturer() {
		return lecturer.getName();
	}
	public String getDays() {
		return days;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getStuCount() {
		return stuCount;
	}
	public void setStuCount(int i) {
		this.stuCount=i;
	}
	public String getAllInfo() {
		String allInfo=(ID+"|"+courseName+", "+startTime+"-"+endTime
				+" on "+days+", with course capacity"+Integer.toString(capacity)+", student: "
				+Integer.toString(stuCount)+", lecturer: "+getLecturer());
		return allInfo;
	}
	public static Course findCourse(String id) {
		for (Course i:Controller.allCourseList){
			if (i.getID().contentEquals(id))
				return i;
		}
		return null;
	}
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
	public void addStudent() {
		stuCount+=1;
	}
}
