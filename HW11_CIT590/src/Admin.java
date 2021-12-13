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
		System.out.println("1.view all course");
		System.out.println("2.add new course");
		System.out.println("3.delete course");
		System.out.println("4.add new professor");
		System.out.println("5.delete professor");
		System.out.println("6.register new student");
		System.out.println("7.delete student");
		System.out.println("8.Exit");
		int admin_c=Integer.parseInt(input.next());//take in admin choice
		input.nextLine();
		switch(admin_c) {
		case 1:	//view all course in courselist		
			viewAllCourses();
			exit();
			break;
		case 2://adding course, input all required number other than student list, automatic null list
			System.out.println("course name: ");	
	    	String courseName = input.nextLine();
	    	this.checkExit(courseName);
	    	System.out.println("course id: ");
	    	String ID = input.nextLine();
	    	this.checkExit(ID);
	    	System.out.println("capacity: ");
	    	String capacityStr = input.nextLine();
	    	this.checkExit(capacityStr);
	    	int capacity=Integer.parseInt(capacityStr);
	    	System.out.println("instructor: ");
	    	String lecturer = input.nextLine();
	    	this.checkExit(lecturer);
	    	System.out.println("course date(eg 'MW'): ");
	    	String days=input.nextLine();	    	  
	    	this.checkExit(days);
	    	System.out.println("start time(eg '19:00'): ");
	    	String startTime = input.nextLine();
	    	this.checkExit(startTime);
	    	System.out.println("end time(eg '19:00'): ");
	    	String endTime = input.nextLine();
	    	this.checkExit(endTime);
	    	if (Professor.findProf(lecturer)==null) {
	    		addProf();
			}	    		
	    	Professor p=Professor.findProf(lecturer);
	    	Course newC=new Course(ID,courseName, p, days, startTime, endTime, capacity);
	    	if (p.checkTime(newC)) {
	    		System.out.println("New course is in conflict with other courses"+p.conflictCourse(newC).getAllInfo());
	    	}else {
	    		Controller.allCourseList.add(newC);
	    		System.out.println("Successfully added course"+newC.getAllInfo());
	    	}
			exit();
			break;
		case 3://delete course from course list
			System.out.println("id of course deleting:");
			String id=input.next();			
			Controller.allCourseList.remove(Course.findCourse(id));
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
			exit();
			break;
		}		
	}
	public void addProf() {
		System.out.println("Prof name: ");	
    	String name = input.nextLine();
    	this.checkExit(name);
    	System.out.println("Prof id: ");
    	String IDStr = input.nextLine();
    	this.checkExit(IDStr);
    	int ID=Integer.parseInt(IDStr);
    	while (Professor.checkIDExist(ID)) {
    		System.out.println("Prof id: ");
        	IDStr = input.nextLine();
        	this.checkExit(IDStr);
        	ID=Integer.parseInt(IDStr);;
    	}
    	System.out.println("username: ");
    	String username = input.nextLine();
    	this.checkExit(username);
    	while (Professor.checkUsernameExist(username)) {
    		System.out.println("username: ");
        	username = input.nextLine();
        	this.checkExit(username);
    	}
    	System.out.println("password: ");
    	String password = input.nextLine();
    	this.checkExit(password);
    	while (Professor.checkPasswordExist(password)) {
    		System.out.println("password: ");
        	password = input.nextLine();
        	this.checkExit(password);
    	}
    	Professor p=new Professor(name, ID, username,password);
    	Professor.allList.add(p);
    	System.out.println("Successfully added");
	}
	public void delProf(String name) {
		Professor.allList.remove(Professor.findProf(name));
	}
	public void addStu() {
		System.out.println("Student name: ");	
    	String name = input.nextLine();
    	this.checkExit(name);
    	System.out.println("Student id: ");
    	String IDStr = input.nextLine();
    	this.checkExit(IDStr);
    	int ID=Integer.parseInt(IDStr);
    	while (Student.checkIDExist(ID)) {
    		System.out.println("Student id: ");
        	IDStr = input.nextLine();
        	this.checkExit(IDStr);
        	ID=Integer.parseInt(IDStr);;
    	}
    	System.out.println("username: ");
    	String username = input.nextLine();
    	this.checkExit(username);
    	while (Student.checkUsernameExist(username)) {
    		System.out.println("username: ");
        	username = input.nextLine();
        	this.checkExit(username);
    	}
    	System.out.println("password: ");
    	String password = input.nextLine();
    	this.checkExit(password);
    	while (Student.checkPasswordExist(password)) {
    		System.out.println("password: ");
        	password = input.nextLine();
        	this.checkExit(password);
    	}
    	Map <Course,String> stuCourseList= new HashMap<Course,String>(); 
    	String courseID="";
    	while (!checkN(courseID)) {
    		System.out.println("Course ID, or input 'n' to stop: ");
        	courseID = input.nextLine();
        	this.checkExit(courseID);
        	System.out.println("Course grade: ");
        	String courseGrade = input.nextLine();
        	stuCourseList.put(Course.findCourse(courseID), courseGrade);
    	}
    	Student s=new Student(name, ID, username,password,stuCourseList);
    	Student.allList.add(s);
    	System.out.println("Successfully added");
	}
	public boolean checkN(String input) {
		if (input.equals("n")){
			return true;
		}
		return false;
	}
	public void delStu(String name) {
		Student.allList.remove(Student.findStu(name));
	}
}
