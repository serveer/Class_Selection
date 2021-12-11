import java.util.ArrayList;

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
		optionPage();
	};
	
	public static User login(ArrayList<User> userList,String username,String password) {
		for (User user:userList){
			if (user.username.contentEquals(username)&&user.password.contentEquals(password))
				return user;
		}
		System.out.println("Wrong username or password");
		return null;
	} 
	
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
	
	
	
}
