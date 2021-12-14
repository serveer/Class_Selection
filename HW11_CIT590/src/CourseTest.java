import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {
	Course course;
	@BeforeEach
	void setUp() throws Exception {
		//setup
		FileInfoReader.setUp("courseInfo.txt", "profInfo.txt", "studentInfo.txt", "adminInfo.txt");
		//initialize student object
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
	void testFindCourse() {
		assertEquals("CIT590",Course.findCourse("CIT590").getID());
	}
	void testCompareTo() {
		Professor lecturer = new Professor("Brandon",001,"brandon","123");
		Course course1=new Course("CIS545","Big Data",lecturer,"MW","12:30","13:30",50);
		assertEquals(0,course.compareTo(course1));
	}
	void testAddStudent() {
		Map <Course,String> stuCourses= new HashMap<Course,String>();
		Course course=Course.findCourse("CIS320");
		stuCourses.put(Course.findCourse("CIS191"), "A");
		stuCourses.put(course, "A");
		Student s = new Student("Bob",003, "bbbb", "password590", stuCourses);
		course.addStudent(s);
		assertEquals(1,course.getStuCount());
	}
	void testCheckCourseExist() {
		assertFalse(Course.checkCourseExist("CIT111"));
	}
		
	
}
