package CoreJava.systemsInterfaces;

import java.sql.SQLException;
import java.util.List;

import CoreJava.Models.Teaching;

public interface TeachingDAOI {
	public int registerInstructorToCourse(int course_id, int instructor_id) throws SQLException;
	public List<Teaching> getIntructorsCourses() throws SQLException;
}
