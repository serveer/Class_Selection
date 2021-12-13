import java.util.*;

public class Professor extends User {
	private static Scanner input = new Scanner(System.in);
	public static ArrayList<Professor> allList=new ArrayList<Professor>();
	public Map <Course,ArrayList <Student>> profCourses= new HashMap<Course,ArrayList <Student>>();
	public Professor(String name, int id, String username, String password) {
		super(name, id, username, password);
	}
	public static Professor login(String username,String password) {
		for (Professor a:allList){
			if (a.getUsername().contentEquals(username)&&a.getPassword().contentEquals(password))
				return a;
		}
		System.out.println("Wrong username or password");
		return null;
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
    		System.out.println(c.getAllInfo());
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
			if (checkExists(c)) {
				System.out.println("Students in this course are: ");
				for (Student s: profCourses.get(c)) {
					System.out.println(s.getName()+" "+s.getID());
				}
			}else {
				System.out.println("Course not in list. ");
			}
			//get next input
			System.out.println("Please select course id to check student list or enter 'q' to quit");
			id=input.next();
		}
	}
	
	/**
	 * 
	 * @param name of prof
	 * @return prof object
	 */
	public static Professor findProf(String name) {
		for (User p:Professor.allList){
			Professor prof=(Professor)p;
			if (prof.getName().contentEquals(name))
				return prof;
		}
		System.out.println(name+" not found");
		return null;
	}
	/**
	 * check if course is in courselist prof teaches
	 */
	public boolean checkExists(Course course) {
		for (Course c:profCourses.keySet()) {
			if (course.equals(c)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check if course is in conflict with courses prof teaches
	 */
	public boolean checkTime(Course course) {
		for (Course c:profCourses.keySet()) {
			if (c.compareTo(course)==0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check which course is in conflict
	 */
	public Course conflictCourse(Course course) {
		for (Course c:profCourses.keySet()) {
			if (c.compareTo(course)==0) {
				return c;
			}
		}
		return null;
	}
}
