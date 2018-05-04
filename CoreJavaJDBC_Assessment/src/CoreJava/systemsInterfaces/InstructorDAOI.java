package CoreJava.systemsInterfaces;

import java.sql.SQLException;
import java.util.List;

import CoreJava.Models.Instructor;

public interface InstructorDAOI {
	public List<Instructor> getAllInstructors() throws SQLException;
	public Instructor getInstructoByGmail(String email) throws SQLException;
}
