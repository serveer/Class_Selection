import java.io.*;
import java.util.ArrayList;

import movies.Actor;


public class FileInfoReader {
	private ArrayList<Course> actorsInfo = new ArrayList<Course>();
	public void setUp (String courseInfo, String movieRatings) {
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
					String ID=info[0];
					String courseName=info[1]; 
					String lecturer=info[2];
					String days=info[3];
					String startTime=info[4];
					String endTime=info[5];
					int capacity =Integer.parseInt(info[6]);
					Course course=new Course(ID, courseName, lecturer, 
							 days,  startTime,  endTime, capacity);
					for (int i = 1; i < array.length; i++) {
						newActor.getMoviesCast().add(array[i].trim().toLowerCase());
					}
					actorsInfo.add(newActor);
				}
				fd.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
