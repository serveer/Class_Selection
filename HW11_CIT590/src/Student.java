import java.util.*;

public class Student extends User {
	private static Scanner input = new Scanner(System.in);
	public static ArrayList<Student> allList=new ArrayList<Student>();
	public Map <Course,String> stuCourses= new HashMap<Course,String>();
	public Student(String name, int id, String username, String password, Map <Course,String> stuCourses) {
		super(name, id, username, password);
		this.stuCourses=stuCourses;
	}
	public Map <Course,String> getStuCourses(){
		return stuCourses;
	}
	/**
	 * page for student to pick options
	 */
	public void optionPage() {
		System.out.println("1.view all courses");
		System.out.println("2.Add courses to your list");
		System.out.println("3.view enrolled courses");
		System.out.println("4.withdraw from course");
		System.out.println("5.view grades");
		System.out.println("6.Exit");
		System.out.println("You may also come back to this page at any time by entering 'q'");
		int admin_c=Integer.parseInt(input.next());
		switch(admin_c) {
		case 1:	//view all course in courselist		
			viewAllCourses();
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
		String id=pickClass();
		//end if enters q
		while (!ifExit(id)) {
			//check if course in allCourseList, avoid nullpointer
			if (Course.checkCourseExist(id)) {
				Course c=Course.findCourse(id);//course of interest
				//check if course is already enrolled
				if(checkExists(c)) {
					System.out.println("Course already enrolled");
				//check if time conflicts
				}else if(checkTime(c)){
					System.out.println("New course is in conflict with other enrolled courses"+conflictCourse(c).getAllInfo());
				//check if course is full
				}else if(!c.checkFull()){
					//add course to stuCourses
					stuCourses.put(c,null);
				}else {	
					//add 1 to student count
					c.addStudent(this);
					System.out.println("course enrolled");
				}
			}
			//get next input
			System.out.println("Please select course id to add or enter 'q' to quit");
			id=input.next();
		}
	}
	/**
	 * print all information about this student's enrolled courses
	 */
	public void viewStuCourses() {
		//print info about student courses
		for (Course c:stuCourses.keySet()) {
    		System.out.println(c.getAllInfo());
    	}
	}
	/**
	 * print all student's enrolled courses and get their course id
	 * @return
	 */
	public String pickClass() {
		//view all enrolled courses
		System.out.println("The courses in your list: ");
		viewStuCourses();
		System.out.println();
		//get course ID
		System.out.println("Please select course id to enroll or enter 'q' to quit");
		String id=input.next();
		return id;
	}
	/**
	 * iterative method for student to withdraw a course from their courselist
	 */
	public void withdraw() {
		//get id
		String id=pickClass();
		//while id not q
		while (!ifExit(id)) {
			//check if course in allCourseList, avoid nullpointer
			if (Course.checkCourseExist(id)) {
				Course c=Course.findCourse(id);//course of interest
				//if course not in student enrolled course list
				if(!checkExists(c)) {
					System.out.println("Course not enrolled");
				}else {
					//withdraw course to stuCourses
					stuCourses.remove(c);
					//add 1 to student count
					c.removeStudent(this);
				}
			}
			//get next input
			System.out.println("Please select course id to wtihdraw or enter 'q' to quit");
			id=input.next();
		}
	}
	/**
	 * view all student courses and their grades
	 */
	public void viewGrades() {
		System.out.println("Here are the courses you've taken, with your grades in letter format.");
		for (Course c:stuCourses.keySet()) {
			System.out.println("Grade of "+c.getID()+c.getCourseName()+": "+stuCourses.get(c));
    	}
	}
	/**
	 * student login check, method override
	 * @param userList
	 * @param username
	 * @param password
	 * @return student object
	 */
	public static Student login(String username,String password) {
		for (Student s:allList){
			if (s.getUsername().contentEquals(username)&&s.getPassword().contentEquals(password))
				return s;
		}
		System.out.println("Wrong username or password");
		return null;
	} 
	/**
	 * check if course is in student enrolled courses
	 */
	public boolean checkExists(Course course) {
		for (Course c:stuCourses.keySet()) {
			if (course.equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param name of student
	 * @return student object
	 */
	public static Student findStu(String name) {
		for (User p:allList){
			if (p.getName().contentEquals(name))
				return (Student)p;
		}
		System.out.println("Student not found");
		return null;
	}
	/**
	 * check if professor exists to be deleted
	 * @param name of professor
	 * @return true if p exists
	 */
	public static boolean checkStudentExist(String name) {
		for (Student s: allList) {
			if (s.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param username to check
	 * @return if the username already exists
	 */
	public static boolean checkUsernameExist(String username) {
		//check each student
		for (Student u:allList) {
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
		//check each student
		for (Student u:allList) {
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
	 * check if course is in conflict with courses student enrolled
	 */
	public boolean checkTime(Course course) {
		//iterate through each course
		for (Course c:stuCourses.keySet()) {
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
		//similar to checkTime method but return which course is in conflict
		for (Course c:stuCourses.keySet()) {
			if (c.compareTo(course)==0) {
				return c;
			}
		}
		return null;
	}
}
