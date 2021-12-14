import java.util.*;
/**
 * Professor user class
 * @author Jonathan Shaw and Zihu Xu
 *
 */
public class Professor extends User {
	private static Scanner input = new Scanner(System.in);
	/**
	 * list of all professors
	 */
	public static ArrayList<Professor> allList=new ArrayList<Professor>();
	//professor's courses and student list
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
		System.out.println("You may also come back to this page at any time by entering 'q'");
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
	
	/**
	 * print info about each course
	 */
	public void viewProfCourses() {
		//iterate through about each course 
		for (Course c:profCourses.keySet()) {
			//print info about each course
    		System.out.println(c.getAllInfo());
    	}
	}
	/**
	 * get user input for class
	 * @return id of class to view
	 */
	public String pickClass() {
		System.out.println("The courses in your list: ");
		viewProfCourses();
		System.out.println();
		System.out.println("Please select course id or enter 'q' to quit");
		String id=input.next();
		return id;
	}
	/**
	 * view student in certain course
	 */
	public void viewStudents() {
		String id=pickClass();
		//while id is not q, loop
		while (!ifExit(id)) {
			//find course only if course is in allcourselist
			if (Course.checkCourseExist(id)) {
				//course of interest
				Course c=Course.findCourse(id);
				//check if course in prof courseliset
				if (checkExists(c)) {
					//print student info
					System.out.println("Students in this course are: ");
					for (Student s: profCourses.get(c)) {
						System.out.println(s.getName()+" "+s.getID());
					}
				//check if course in all course list
				}else if (!Course.checkCourseExist(id)){
					System.out.println("Course not found. ");
				}else {
					//print message if not in list
					System.out.println("Course not in list. ");
				}
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
		//itereate through allProf list
		for (Professor p:Professor.allList){
			//check which prof has the same name
			if (p.getName().contentEquals(name))
				//return professor object
				return p;
		}
		//return null if not found
		System.out.println(name+" not found");
		return null;
	}
	/**
	 * check if course is in courselist prof teaches
	 * @param course to add
	 * @return true if course in professor list
	 */
	public boolean checkExists(Course course) {
		//iterate through prof's courselist
		for (Course c:profCourses.keySet()) {
			//if course found
			if (course.equals(c)) {
				//return true
				return true;
			}
		}
		//if not found return false
		return false;
	}
	/**
	 * 
	 * @param course to compare
	 * @return if any course is in conflict
	 */
	public boolean checkTime(Course course) {
		//compare each course objects
		for (Course c:profCourses.keySet()) {
			//if overlap return true
			if (c.compareTo(course)==0) {
				return true;
			}
		}
		//return false
		return false;
	}
	
	/**
	 * 
	 * @param course to compare
	 * @return course that is in conflict
	 */
	public Course conflictCourse(Course course) {
		//similar to checkTime method but return which course is in conflict
		for (Course c:profCourses.keySet()) {
			if (c.compareTo(course)==0) {
				return c;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param username to check
	 * @return if the username already exists
	 */
	public static boolean checkUsernameExist(String username) {
		//check each prof
		for (Professor u:allList) {
			//if one username matches
			if (u.getUsername().equals(username)){
				System.out.println("Username exists");
				//return true
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param id to check
	 * @return if the ID already exists
	 */
	public static boolean checkIDExist(int ID) {
		//check each prof
		for (Professor u:allList) {
			//if one ID matches
			if (u.getID()==ID){
				System.out.println("ID exists");
				//return true
				return true;
			}
		}
		return false;
	}
	/**
	 * check if professor exists to be deleted
	 * @param name of professor
	 * @return true if p exists
	 */
	public static boolean checkProfExist(String name) {
		//check if prof is in allProf list by prof name
		for (Professor p: allList) {
			//if one prof has same name
			if (p.getName().equals(name)) {
				//return true
				return true;
			}
		}
		//return false otherwise
		return false;
	}
}
