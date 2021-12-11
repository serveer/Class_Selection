import java.util.*;

public class Professor extends User {
	private static Scanner input = new Scanner(System.in);
	public Map <Course,Student[]> profCourses= new HashMap<Course,Student[]>();
	public Professor(String name, int id, String username, String password) {
		super(name, id, username, password);
	}
	public void optionPage() {
		System.out.println("1.view given courses");
		System.out.println("2.view student list of given courses");
		System.out.println("3.Exit");
		int admin_c=Integer.parseInt(input.next());
		switch(admin_c) {
		case 1://view all course the Prof is has
	    	this.viewProfCourses();
	    	exit();
			break;
		case 2:
			this.viewStudents();
			exit();
			break;
		case 3://back to login page
			Controller.loginpage();
			break;
		}			
	}
	public void viewProfCourses() {
		for (Course c:profCourses.keySet()) {
    		c.getAllInfo();
    	}
	}
	
	public String pickClass() {
		System.out.println("The courses in your list: ");
		viewProfCourses();
		System.out.println();
		System.out.println("Please select course id or enter 'q' to quit");
		String id=input.next();
		return id;
	}
	public void viewStudents() {
		String id=pickClass();
		while (!ifExit(id)) {
			//course of interest
			Course c=Course.findCourse(id);
			//withdraw course to stuCourses
			profCourses.remove(c);
			//get next input
			System.out.println("Please select course id to wtihdraw or enter 'q' to quit");
			id=input.next();
		}
	}
}
