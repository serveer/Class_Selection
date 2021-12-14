import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdminTest {
	Admin admin;
	@BeforeEach
	void setUp() throws Exception {
		FileInfoReader.setUp("courseInfo.txt", "profInfo.txt", "studentInfo.txt", "adminInfo.txt");
		String name = "Brandon";
		int id = 101;
		String username = "brandon";
		String password = "password";
		admin = new Admin(name, id, username,password);
	}

	@Test
	void testDelStu() {
		admin.delStu("StudentName1");
		assertEquals(1,Student.allList.size());
		
	}
	void testDelProf() {
		assertEquals(32,Student.allList.size());
		admin.delProf("Harry Smith");
		assertEquals(31,Student.allList.size());
		
	}
	void testCheckN() {
		assertTrue(admin.checkN("n"));
	}
	void testLogin() {
		assertEquals(1,Admin.login("admin01", "password590").getID());
	}
}

