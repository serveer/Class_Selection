import java.util.*;

public class Student extends User {
	private static Scanner input = new Scanner(System.in);
	public Map <Course,String> stuCourses= new HashMap<Course,String>();
	public Student(String name, int id, String username, String password) {
		super(name, id, username, password);
	}
	public Map <Course,String> getStuCourses(){
		return stuCourses;
	}
	public void optionPage() {
		System.out.println("1.view all courses");
		System.out.println("2.Add courses to your list");
		System.out.println("3.view enrolled courses");
		System.out.println("4.withdraw from course");
		System.out.println("5.view grades");
		System.out.println("6.Exit");
		int admin_c=Integer.parseInt(input.next());
		switch(admin_c) {
		case 1:	//view all course in courselist		
			viewAllCourses();
			System.out.println("Exit?");
			input.next();
			exit();
			break;
		case 2://register in a course
			this.register();
			exit();
			break;
		case 3://view all course the student is registered in
	    	this.viewStuCourses();
	    	exit();
			break;
		case 4://withdraw from course
			this.withdraw();
			exit();
			break;
		case 5:
			this.viewGrades();
			exit();
			break;
		case 6://back to login page
			Controller.loginpage();
			break;
		}			
	}
	
	/**
	 * loop to register for course
	 */
	public void register() {
		//get first course to register
		System.out.println("Please select course id to add or enter 'q' to quit");
		String id=input.next();
		//end if enters q
		while (!ifExit(id)) {
			Course c=Course.findCourse(id);//course of interest
			//add course to stuCourses
			stuCourses.put(c,null);
			//add 1 to student count
			c.addStudent();
			//get next input
			System.out.println("Please select course id to add or enter 'q' to quit");
			id=input.next();
		}
	}
	/**
	 * 
	 */
	public void viewStuCourses() {
		for (Course c:stuCourses.keySet()) {
    		c.getAllInfo();
    	}
	}
	public String pickClass() {
		System.out.println("The courses in your list: ");
		viewStuCourses();
		System.out.println();
		System.out.println("Please select course id to withdraw or enter 'q' to quit");
		String id=input.next();
		return id;
	}
	/**
	 * iterative method for student to withdraw a course from their courselist
	 */
	public void withdraw() {
		String id=pickClass();
		while (!ifExit(id)) {
			Course c=Course.findCourse(id);//course of interest
			//withdraw course to stuCourses
			stuCourses.remove(c);
			//get next input
			System.out.println("Please select course id to wtihdraw or enter 'q' to quit");
			id=input.next();
		}
	}
	public void viewGrades() {
		for (Course c:stuCourses.keySet()) {
			System.out.println("Here are the courses you've taken, with your grades in letter format.");
			System.out.print("Grade of "+c.getID()+c.getCourseName()+": "+stuCourses.get(c));
    	}
	}
	public static Student login(ArrayList<Student> userList,String username,String password) {
		for (Student s:userList){
			if (s.getUsername().contentEquals(username)&&s.getPassword().contentEquals(password))
				return s;
		}
		System.out.println("Wrong username or password");
		return null;
	} 
}
