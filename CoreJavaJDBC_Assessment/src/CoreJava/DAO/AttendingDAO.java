package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.CustomExceptions.StudentRegistrationException;
import CoreJava.Models.Attending;
import CoreJava.Models.Course;
import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.AttendingDAOI;
import CoreJava.DAO.OracleConnection;
import CoreJava.Utils.OracleQueries;

public class AttendingDAO implements AttendingDAOI {

	@Override
	public int registerStudentToCourse(Student student, Course course) throws SQLException {
		Integer ID = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		OracleConnection connection = new OracleConnection();
		String [] COL = {"ATTENDING_ID"};
		
		if(student.getGpa() >= course.getMinimum_gpa()) {
			
			try {
				conn = connection.getConnection();
				stmt = conn.prepareStatement(OracleQueries.SAVESTUDENTTOCOURSE, COL);
				stmt.setInt(1, course.getCourse_id());
				stmt.setInt(2, student.getStudent_id());
				
				stmt.executeUpdate();
				
				result = stmt.getGeneratedKeys();
				
				if(result!=null && result.next()) {
					ID = result.getInt(1);
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				result.close();
				if(stmt!=null ) {
					stmt.close();
					
				}
				if(conn !=null) {
					conn.close();
				}
			}
		}else {
			try {
				throw new StudentRegistrationException("\nDid not meet the minimum GPA requirement\nRegistration Denied");
			} catch (StudentRegistrationException e) {
				e.printStackTrace();
			}
		}
		
		return ID;
	}

	@Override
	public List<Attending> getStudentCourse(int student_id) throws SQLException {
		List<Attending> list = new ArrayList<Attending>();
		Attending attending = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETCOURSEBYSTUDENT);
			stmt.setInt(1, student_id);
			
			result = stmt.executeQuery();
			
//			if (!result.next() ) {
//			    System.out.println("no data");
//			}
			
			while(result!= null && result.next()) {
				attending = new Attending();
				attending.setCourse_name(result.getString(1));
				attending.setFull_name(result.getString(2));
				attending.setEmail(result.getString(3));
				list.add(attending);
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			result.close();
			if(stmt!=null ) {
				stmt.close();
				
			}
			if(conn !=null) {
				conn.close();
			}
		}
		
		return list;
	}

}
