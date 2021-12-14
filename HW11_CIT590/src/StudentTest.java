import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {
	Student student;
	@BeforeEach
	void setUp() throws Exception {
		//setup
		FileInfoReader.setUp("courseInfo.txt", "profInfo.txt", "studentInfo.txt", "adminInfo.txt");
		Map <Course,String> stuCourses= new HashMap<Course,String>();
		String name = "Bob";
		int id = 003;
		String username = "bbbb";
		String password = "password";
		student = new Student(name, id, username, password, stuCourses);
	}

	@Test
	void testCheckExists() {
		Professor lecturer = new Professor("Brandon",001,"brandon","123");
		Course course = new Course("CIS545","Big Data",lecturer,"MW","12:30","13:30",50);
		assertFalse(student.checkExists(course));
	}
	void testFindStu() {
		assertEquals(student,Student.findStu("StudentName1"));
	}
	void testCheckUsernameExist() {
		assertTrue(Student.checkUsernameExist("StudentName1"));
		assertFalse(Student.checkUsernameExist("Tom"));
	}
	void testLogin() {
		assertEquals(Student.findStu("StudentName1"),Student.login("testStudent01", "password590"));
	}
	void  testCheckIDExist() {
		assertTrue(Student.checkIDExist(001));
		assertFalse(Student.checkIDExist(100));
	}
}
