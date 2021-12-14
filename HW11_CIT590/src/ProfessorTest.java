import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfessorTest {
	Professor prof;
	Professor prof1;
	Course course;
	@BeforeEach
	void setUp() throws Exception {
		//setup
		FileInfoReader.setUp("courseInfo.txt", "profInfo.txt", "studentInfo.txt", "adminInfo.txt");
		String name = "Brandon";
		int id = 101;
		String username = "brandon";
		String password = "password";
		prof = new Professor(name, id, username,password);
		prof1 = new Professor("Jonathan Shaw",102, "Shaw","password590");
	}

	@Test
	void testFindProf() {
		//how to refer to the first one in professor info file.
		assertEquals("Clayton Greenberg",Professor.findProf("Clayton Greenberg").getName());
		assertEquals(1,Professor.findProf("Clayton Greenberg").getID());
	}
	void testCheckExists() {
		Professor lecturer = new Professor("Brandon",001,"brandon","123");
		Course course = new Course("CIS545","Big Data",lecturer,"MW","12:30","13:30",50);
		assertFalse(prof.checkExists(course));
	}
	void testCheckTime() {
		course = new Course("CIS545","Big Data",prof,"MW","12:30","13:30",50);
		assertFalse(prof.checkTime(course));
	}
		
	void testConflictCourse() {
		Course course1= new Course("CIS546","Big Data",prof1,"MW","12:30","13:30",50);
		ArrayList <Student> stuList=new ArrayList <Student>();
		prof.profCourses.put(course1, stuList);
		assertEquals(course1,prof.conflictCourse(course));
	}
	void testCheckUsernameExist() {
		assertTrue(Professor.checkUsernameExist("Clayton Greenberg"));
		assertFalse(Professor.checkUsernameExist("Zihu"));
	}
	void testCheckIDExist() {
		assertTrue(Professor.checkIDExist(001));
		assertFalse(Professor.checkIDExist(456));
	}
	void testCheckProfExist() {
		assertFalse(Professor.checkProfExist("Zihu Xu"));
	}
	void testLogin() {
		assertEquals(Professor.findProf("Clayton Greenberg"),Professor.login("Greenberg", "password590"));
	}
}
