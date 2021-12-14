import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin extends User {
	private static Scanner input = new Scanner(System.in);
	public static ArrayList<Admin> allList=new ArrayList<Admin>();
	public Admin(String name, int id, String username, String password) {
		super(name, id, username, password);
		// TODO Auto-generated constructor stub
	}
	public static Admin login(String username,String password) {
		for (Admin a:allList){
			if (a.getUsername().contentEquals(username)&&a.getPassword().contentEquals(password))
				return a;
		}
		System.out.println("Wrong username or password");
		return null;
	} 
	public void optionPage() {
		//print all options
		System.out.println("1.view all course");
		System.out.println("2.add new course");
		System.out.println("3.delete course");
		System.out.println("4.add new professor");
		System.out.println("5.delete professor");
		System.out.println("6.register new student");
		System.out.println("7.delete student");
		System.out.println("8.Exit");
		System.out.println("You may also come back to this page at any time by entering 'q'");
		int admin_c=Integer.parseInt(input.next());//take in admin choice
		input.nextLine();
		switch(admin_c) {
		case 1:	//view all course in courselist		
			viewAllCourses();
			exit();
			break;
		case 2://adding course, input all required number other than student list, automatic null list
			//get each course info
			//get initial course id
			System.out.println("course id: ");
	    	String ID = input.nextLine();
	    	//check if it is q
	    	this.checkExit(ID);
			//while course is already in the course list
			while(Course.checkCourseExist(ID)){
				System.out.println("The course already exist, re-enter or press 'q' to quit");
	    		System.out.println("course id: ");
		    	ID = input.nextLine();
		    	this.checkExit(ID);
	    	}
			System.out.println("course name: ");	
	    	String courseName = input.nextLine();
	    	//check if q
	    	this.checkExit(courseName);
	    	System.out.println("capacity: ");
	    	String capacityStr = input.nextLine();
	    	this.checkExit(capacityStr);
	    	int capacity=Integer.parseInt(capacityStr);
	    	System.out.println("course date(eg 'MW'): ");
	    	String days=input.nextLine();	    	  
	    	this.checkExit(days);
	    	System.out.println("start time(eg '19:00'): ");
	    	String startTime = input.nextLine();
	    	this.checkExit(startTime);
	    	System.out.println("end time(eg '19:00'): ");
	    	String endTime = input.nextLine();
	    	this.checkExit(endTime);
	    	System.out.println("instructor: ");
	    	String lecturer = input.nextLine();
	    	this.checkExit(lecturer);
	    	//if prof is not in list
	    	if (Professor.findProf(lecturer)==null) {
	    		System.out.println("Professor not found, ");
	    		//add professor object and get info
	    		addProf();
			}
	    	//find professor object by lecturer when it exists
	    	Professor p=Professor.findProf(lecturer);
	    	//create new course
	    	Course newC=new Course(ID,courseName, p, days, startTime, endTime, capacity);
	    	//check if new course conflict with prof current course
	    	if (p.checkTime(newC)) {
	    		//if conflict don't add
	    		System.out.println("New course is in conflict with other courses"+p.conflictCourse(newC).getAllInfo());
	    	}else {
	    		//if doesn't conflict add
	    		Controller.allCourseList.add(newC);
	    		System.out.println("Successfully added course"+newC.getAllInfo());
	    	}
			exit();
			break;
		case 3://delete course from course list
			System.out.println("id of course deleting:");
			String id=input.next();
			if (Course.findCourse(id)!=null) {
				Controller.allCourseList.remove(Course.findCourse(id));
				System.out.println("Course deleted");
			}else {
				System.out.println("Course not found, please try again later");
			}
			exit();
			break;
		case 4://add prof
			addProf();
			exit();
			break;
		case 5://Delete prof
			System.out.println("Name of professor deleting:");
			String name=input.next();
			this.checkExit(name);
			delProf(name);	
			exit();
			break;
		case 6://add student object/register student
			addStu();
			exit();
			break;
		case 7://delete student
			System.out.println("Name of student deleting:");
			String stuName=input.next();
			this.checkExit(stuName);
			delStu(stuName);
			break;
		case 8://end program and store info
			Controller.loginpage();
			break;
		}		
	}
	/**
	 * method to get prof info and add prof object
	 */
	public void addProf() {
		//get prof info
		System.out.println("Prof name: ");	
    	String name = input.nextLine();
    	this.checkExit(name);
    	System.out.println("Prof id: ");
    	String IDStr = input.nextLine();
    	this.checkExit(IDStr);
    	int ID=Integer.parseInt(IDStr);
    	//while prof ID exits
    	while (Professor.checkIDExist(ID)) {
    		System.out.println("Prof id: ");
        	IDStr = input.nextLine();
        	this.checkExit(IDStr);
        	ID=Integer.parseInt(IDStr);;
    	}
    	System.out.println("username: ");
    	String username = input.nextLine();
    	this.checkExit(username);
    	//while prof username exits
    	while (Professor.checkUsernameExist(username)) {
    		System.out.println("username: ");
        	username = input.nextLine();
        	this.checkExit(username);
    	}
    	System.out.println("password: ");
    	String password = input.nextLine();
    	this.checkExit(password);
    	//create prof object
    	Professor p=new Professor(name, ID, username,password);
    	Professor.allList.add(p);
    	
	}
	/**
	 * delete professor
	 * @param name of professor
	 */
	public void delProf(String name) {
		//check if prof exists
		if(Professor.checkProfExist(name)) {
			//remove if exists
			Professor.allList.remove(Professor.findProf(name));
    		System.out.println("Successfully deleted");
    	}else {
    		//do nothing otherwise
    		System.out.println("Professor not found");
    	}
	}
	/**
	 * add student to student list
	 */
	public void addStu() {
		//get student info
		System.out.println("Student name: ");	
    	String name = input.nextLine();
    	//check if q
    	this.checkExit(name);
    	System.out.println("Student id: ");
    	String IDStr = input.nextLine();
    	this.checkExit(IDStr);
    	int ID=Integer.parseInt(IDStr);
    	//check if ID exists
    	while (Student.checkIDExist(ID)) {
    		System.out.println("Student id: ");
        	IDStr = input.nextLine();
        	this.checkExit(IDStr);
        	ID=Integer.parseInt(IDStr);;
    	}
    	System.out.println("username: ");
    	String username = input.nextLine();
    	this.checkExit(username);
    	//check if username exists
    	while (Student.checkUsernameExist(username)) {
    		System.out.println("username: ");
        	username = input.nextLine();
        	this.checkExit(username);
    	}
    	System.out.println("password: ");
    	String password = input.nextLine();
    	this.checkExit(password);
    	//create empty map as student course list
    	Map <Course,String> stuCourseList= new HashMap<Course,String>();
    	//initialize course ID to add
    	System.out.println("Course ID, or input 'n' to stop: ");
    	String courseID = input.nextLine();
    	this.checkExit(courseID);
    	while (!checkN(courseID)) {
    		//check if course exists
    		if (Course.checkCourseExist(courseID)) {
    			//check if course full
    			if(!Course.findCourse(courseID).checkFull()) {
    				System.out.println("Course grade: ");
                	String courseGrade = input.nextLine();
                	stuCourseList.put(Course.findCourse(courseID), courseGrade);
    			}
      		}else {
    			System.out.println("Course doesn't exist");
    		}
    		System.out.println("Course ID, or input 'n' to stop, or 'q' to quit: ");
        	courseID = input.nextLine();
        	this.checkExit(courseID);
    	}
    	//create student object
    	Student s=new Student(name, ID, username,password,stuCourseList);
    	//for each course in student's course list, add student
    	for (Course c:stuCourseList.keySet()) {
    		c.addStudent(s);
    	}
    	//add student object
    	Student.allList.add(s);
    	System.out.println("Successfully added");
	}
	/**
	 * check if input is n to stop
	 * @param input of user
	 * @return true if n
	 */
	public boolean checkN(String input) {
		if (input.equals("n")){
			return true;
		}
		return false;
	}
	/**
	 * delete student by name
	 * @param name of student
	 */
	public void delStu(String name) {
		//check if student exists
		if(Student.checkStudentExist(name)) {
			//delete student
			Student.allList.remove(Student.findStu(name));
    		System.out.println("Successfully deleted");
    	}else {
    		//print student not found
    		System.out.println("Student not found");
    	}
	}
	
}
