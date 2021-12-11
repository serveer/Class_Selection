import java.util.ArrayList;

public class Admin extends User {

	public Admin(String name, int id, String username, String password) {
		super(name, id, username, password);
		// TODO Auto-generated constructor stub
	}
	public static Admin login(ArrayList<Admin> userList,String username,String password) {
		for (Admin a:userList){
			if (a.getUsername().contentEquals(username)&&a.getPassword().contentEquals(password))
				return a;
		}
		System.out.println("Wrong username or password");
		return null;
	} 

}
