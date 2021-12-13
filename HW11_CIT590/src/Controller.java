import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	//create scanner object
	private static Scanner input = new Scanner(System.in);
	public static ArrayList<Course> allCourseList=new ArrayList<Course>();
	public static void main(String[] args) {
		FileInfoReader.setUp("courseInfo.txt", "profInfo.txt", "studentInfo.txt", "adminInfo.txt");
		loginpage();
	}
	/**
	 * print login options for user
	 * @return user option
	 */
	public static void loginpage() {
		System.out.println("Choose number?");
		System.out.println("1.---Login as a student");
		System.out.println("2.---Login as a professor");
		System.out.println("3.---Login as an admin");
		System.out.println("4.---Quit system");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
		int userType= Integer.parseInt(input.next());
		switch(userType){
			case 1:
				studentLogin();
			case 2:
				profLogin();
			case 3:
				adminLogin();
			case 4:
				System.exit(0);
		}
	}
	/**
	 * login to student profile
	 */
	public static void studentLogin() {
		System.out.println("Log in");
    	System.out.println("username: ");
    	String username = input.next();
    	System.out.println("password: ");
    	String password = input.next();
		Student s=Student.login(username, password);//find specific student obj
		s.optionPage();
	}
	/**
	 * login to professor profile
	 */
	public static void profLogin() {
		System.out.println("Log in");
    	System.out.println("username: ");
    	String username = input.next();
    	System.out.println("password: ");
    	String password = input.next();
		Professor p=Professor.login(username, password);//find specific student obj
		p.optionPage();
	}
	/**
	 * login to admin profile
	 */
	public static void adminLogin() {
		System.out.println("Log in");
    	System.out.println("username: ");
    	String username = input.next();
    	System.out.println("password: ");
    	String password = input.next();
		Admin a=Admin.login(username, password);//find specific student obj
		a.optionPage();
	}
	
	
}
