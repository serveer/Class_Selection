import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {
	Course course;
	@BeforeEach
	void setUp() throws Exception {
		//initialize movie trivia object
		String ID = "CIT590";
		String courseName = "Programming Languages and Techniques";
		Professor lecturer = new Professor("Brandon",001,"brandon","123");
		String days = "MW";
		String startTime = "12:00";
		String endTime = "13:00";
		int capacity = 10;
		int stuCount=0;
		course = new Course (ID,courseName,lecturer, days,startTime,endTime,capacity);
	}
	@Test

	void testGetters() {
		assertEquals("CIT590",course.getID());
		assertEquals("Programming Languages and Techniques",course.getCourseName());
		assertEquals("Brandon",course.getLecturer());
		assertEquals("MW",course.getDays());
		assertEquals("12:00",course.getStartTime());
		assertEquals("13:00",course.getEndTime());
		assertEquals(10,course.getCapacity());
		assertEquals(0,course.getStuCount());
		
	}

	void testSetters() {
		course.setStuCount(8);
		assertEquals(8,course.getStuCount());
	}
	void testFindCourse() {
		assertEquals("CIT590",course.findCourse(this.ID).getID());
	}
	void testCompareTo() {
		Course course1("CIS545","Big Data",lecturer,"MW","12:30","13:30",50);
		assertEquals(0,course.compareTo(course1));
	}
	void testAddStudent() {
		Student s = new Student(003; "Bob"; "bbbb"; "password"; "CIS191: A", "CIS320: A"); 
		course.addStudent(s);
		assertEquals(1,course.getStuCount());
	}
		
	
}
