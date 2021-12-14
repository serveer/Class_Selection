import java.util.ArrayList;
import java.util.Scanner;
/**
 * main class
 * @author Jonathan Shaw and Zihu Xu
 *
 */
public class Controller {
	//create scanner object
	private static Scanner input = new Scanner(System.in);
	/**
	 * list of all courses
	 */
	public static ArrayList<Course> allCourseList=new ArrayList<Course>();
	public static void main(String[] args) {
		//read file
		FileInfoReader.setUp("courseInfo.txt", "profInfo.txt", "studentInfo.txt", "adminInfo.txt");
		//login
		loginpage();
	}
	/**
	 * print login options for user
	 */
	public static void loginpage() {
		//print login options
		System.out.println("Choose number?");
		System.out.println("1.---Login as a student");
		System.out.println("2.---Login as a professor");
		System.out.println("3.---Login as an admin");
		System.out.println("4.---Quit system");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
		//get user option as int
		int userType= Integer.parseInt(input.next());
		switch(userType){
			//call login for student
			case 1:
				studentLogin();
			//call login for prof
			case 2:
				profLogin();
			//call login for admin
			case 3:
				adminLogin();
			//exit system
			case 4:
				System.exit(0);
		}
	}
	/**
	 * login to student profile
	 */
	public static void studentLogin() {
		//get username and password
		System.out.println("Log in");
    	System.out.println("username: ");
    	String username = input.next();
    	System.out.println("password: ");
    	String password = input.next();
    	//check if login successful
    	if (Student.login(username, password)!=null) {
    		//if login successful return student object and go to option page
    		Student s=Student.login(username, password);//find specific student obj
    		s.optionPage();
    	}else {
    		//if wrong username or password go back to first page
    		loginpage();
    	}
		
	}
	/**
	 * login to professor profile
	 */
	public static void profLogin() {
		//get username and password
		System.out.println("Log in");
    	System.out.println("username: ");
    	String username = input.next();
    	System.out.println("password: ");
    	String password = input.next();
    	//check if login successful
    	if (Professor.login(username, password)!=null) {
    		//if login successful return prof object and go to option page
    		Professor p=Professor.login(username, password);//find specific student obj
    		p.optionPage();
    	}else {
    		//if wrong username or password go back to first page
    		loginpage();
    	}
	}
	/**
	 * login to admin profile
	 */
	public static void adminLogin() {
		//get username and password
		System.out.println("Log in");
    	System.out.println("username: ");
    	String username = input.next();
    	System.out.println("password: ");
    	String password = input.next();
    	//check if login successful
    	if (Admin.login(username, password)!=null) {
    		//if login successful return admin object and go to option page
    		Admin a=Admin.login(username, password);//find specific student obj
    		a.optionPage();
    	}else {
    		//if wrong username or password go back to first page
    		loginpage();
    	}
	}
	
}
