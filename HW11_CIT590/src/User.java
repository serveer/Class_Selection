

public abstract class User {
	private String name,username,password;
	private int id;
	public User(String name, int id,String username,String password) {
		this.name=name;
		this.id=id;
		this.username=username;
		this.password=password;
	}
	//getters
	public String getName() {
		return name;
	}
	public int getID() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	//each subclass has to have a option page
	public abstract void optionPage();
	
	public void exit() {
		//exit to optionpage
		System.out.println();
		optionPage();
	};
	/**
	 * view all the courses available in course list
	 */
	public void viewAllCourses() {
		//for each course in list
		for (Course cour:Controller.allCourseList) {
			//call get all info to print courses in desired format
			System.out.println(cour.getAllInfo());
		}
	}
	/**
	 * check if input is q
	 * @param input user input
	 * @return true if q
	 */
	public boolean ifExit(String input) {
		//if input q
		if (input.equals("q")){
			return true;
		}
		return false;
	}
	/**
	 * Exercise exit action if input q
	 * @param input user input
	 */
	public void checkExit(String input) {
		//if input q
		if (input.equals("q")){
			//call exist
			exit();
		}
	}
}
