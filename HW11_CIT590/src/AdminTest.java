import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdminTest {
	Admin admin;
	@BeforeEach
	void setUp() throws Exception {
		public static ArrayList<Admin> allList=new ArrayList<Admin>();
		String name = "Brandon";
		int id = 101;
		String username = "brandon";
		String password = "password";
		admin = new Admin(name, id, username,password);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	void testCheckN() {
		assertTrue(admin.checkN("n"));
	}
	void testCheckUsernameExist() {
		assertTrue(Admin.checkUsernameExist("admin01"));
		assertFalse(Admin.checkUsernameExist("Zihu"));
	}
	void testCheckPasswordExist() {
		assertTrue(Admin.checkPasswordExist("password590"));
		assertFalse(Admin.checkPasswordExist("askhdjka"));
	}
	void testCheckIDExist() {
		assertTrue(Admin.checkIDExist(001));
		assertFalse(Admin.checkIDExist(456));
	}
}
