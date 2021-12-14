import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfessorTest {
	Professor prof;
	@BeforeEach
	void setUp() throws Exception {
		public static ArrayList<Professor> allList=new ArrayList<Professor>();
		public Map <Course,ArrayList <Student>> profCourses= new HashMap<Course,ArrayList <Student>>();
		String name = "Brandon";
		int id = 101;
		String username = "brandon";
		String password = "password";
		prof = new Professor(name, id, username,password);

	}

	@Test
	void testViewProfCourses() {
		fail("Not yet implemented");
	}
	void testFindProf() {
		//how to refer to the first one in professor info file.
		assertTrue(xxx,Professor.findProf("Clayton Greenberg"));
	}
	void testCheckExists() {
		Professor lecturer = new Professor("Brandon",001,"brandon","123");
		Course course = new Course("CIS545","Big Data",lecturer,"MW","12:30","13:30",50);
		assertFalse(prof.checkExists(course));
	}
	void testCheckTime() {
		Professor lecturer = new Professor("Brandon",001,"brandon","123");
		Course course = new Course("CIS545","Big Data",lecturer,"MW","12:30","13:30",50);
		assertFalse(prof.checkTime(course));
	}
		
	void testConflictCourse() {
		
	}
	void testCheckUsernameExist() {
		assertTrue(Professor.checkUsernameExist("Clayton Greenberg"));
		assertFalse(Professor.checkUsernameExist("Zihu"));
	}
	void testCheckPasswordExist() {
		assertTrue(Professor.checkPasswordExist("password590"));
		assertFalse(Professor.checkPasswordExist("ashdjksf"));
	}
	void testCheckIDExist() {
		assertTrue(Professor.checkIDExist(001));
		assertFalse(Professor.checkIDExist(456));
	}
}
