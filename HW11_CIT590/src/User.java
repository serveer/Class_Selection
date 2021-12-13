

public abstract class User {
	private String name,username,password;
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
}
