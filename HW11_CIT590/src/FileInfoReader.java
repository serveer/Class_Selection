import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FileInfoReader {
	public static void setUp (String courseInfo, String profInfo, String studentInfo, String adminInfo) {
		//read profInfo file
		try {
			File f = new File (profInfo);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] info = line.trim().split(";");
				String name=info[0].trim(); 
				int ID=Integer.parseInt(info[1].trim());
				String username=info[2].trim(); 
				String password=info[3].trim();
				Professor prof=new Professor(name, ID, username, password);
				Professor.allList.add(prof);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//read adminInfo file
		try {
			File f = new File (adminInfo);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] info = line.trim().split(";");
				int ID=Integer.parseInt(info[0].trim());
				String name=info[1].trim(); 
				String username=info[2].trim(); 
				String password=info[3].trim();
				Admin ad=new Admin(name, ID, username, password);
				Admin.allList.add(ad);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//read courseInfo file
		try {
			File f = new File (courseInfo);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] info = line.trim().split(";");
				String ID=info[0].trim();
				String courseName=info[1].trim(); 
				Professor lecturer=Professor.findProf(info[2].trim());
				String days=info[3].trim();
				String startTime=info[4].trim();
				String endTime=info[5].trim();
				int capacity =Integer.parseInt(info[6].trim());
				Course course=new Course(ID, courseName, lecturer, 
						 days,  startTime,  endTime, capacity);
				Controller.allCourseList.add(course);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//read studentInfo file
		try {
			File f = new File (studentInfo);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] info = line.trim().split(";");
				int ID=Integer.parseInt(info[0].trim());
				String name=info[1].trim(); 
				String username=info[2].trim(); 
				String password=info[3].trim();
				Map <Course,String> stuCourses= new HashMap<Course,String>();
				if (info.length>4) {
					String [] courseList=info[4].trim().split(",");
					for (String courseGrade:courseList) {
						String [] CG=courseGrade.split(":");
						String courseID=CG[0];
						String grade=CG[1];
						stuCourses.put(Course.findCourse(courseID.trim()),grade.trim());
					}
				}
				Student stu=new Student(name, ID, username, password,stuCourses);
				Student.allList.add(stu);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//iterate through each course, if lecturer doesn't have this course add it
		for (Course c:Controller.allCourseList) {
			if (!c.getLecturer().profCourses.containsKey(c)) {
				ArrayList <Student> stuList=new ArrayList <Student>();
				c.getLecturer().profCourses.put(c,stuList);
			}
		}
		for (User s:Student.allList) {
			Student stu=(Student)s;
			for(Course c:stu.getStuCourses().keySet()){
				c.addStudent(stu);
			}
		}
	}
}
