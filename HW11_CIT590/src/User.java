import java.util.ArrayList;

public abstract class User {
	private String name,username,password;
	public static ArrayList<User> allList=new ArrayList<User>();
	private int id;
	public User(String name, int id,String username,String password) {
		this.name=name;
		this.id=id;
		this.username=username;
		this.password=password;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public abstract void optionPage();
	
	public void exit() {
		System.out.println();
		optionPage();
	};
	
	public void viewAllCourses() {
		for (Course cour:Controller.allCourseList) {
			System.out.println(cour.getAllInfo());
		}
	}
	
	public boolean ifExit(String input) {
		if (input.equals("q")){
			return true;
		}
		return false;
	}
	public void checkExit(String input) {
		if (input.equals("q")){
			exit();
		}
	}
	public boolean checkExists(Course course) {
		for (Course c:Controller.allCourseList) {
			if (course.equals(c)) {
				return true;
			}
		}
		return false;
	}
	public static User login(String username,String password) {
		for (User u:allList){
			if (u.getUsername().contentEquals(username)&&u.getPassword().contentEquals(password))
				return u;
		}
		System.out.println("Wrong username or password");
		return null;
	}
	public static boolean checkUsernameExist(String username) {
		for (User u:allList) {
			if (u.getUsername().equals(username)){
				System.out.println("Username exists");
				return true;
			}
		}
		return false;
	}
	public static boolean checkPasswordExist(String password) {
		for (User u:allList) {
			if (u.getPassword().equals(password)){
				System.out.println("Password exists");
				return true;
			}
		}
		return false;
	}
	public static boolean checkIDExist(int ID) {
		for (User u:allList) {
			if (u.getID()==ID){
				System.out.println("ID exists");
				return true;
			}
		}
		return false;
	}
}
