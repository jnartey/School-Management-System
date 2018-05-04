package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.StudentDAOI;
import CoreJava.DAO.OracleConnection;
import CoreJava.Utils.OracleQueries;

public class StudentDAO implements StudentDAOI {

	@Override
	public Student getStudentByGmail(String email) throws SQLException {
		Student student = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETSTUDENTBYGMAIL);
			stmt.setString(1, email);
			
			result = stmt.executeQuery();
			
			while(result!= null && result.next()) {
				student = new Student();
				student.setStudent_id(result.getInt(1));
				student.setFull_name(result.getString(2));
				student.setEmail(result.getString(3));
				student.setGpa(result.getDouble(4));
				student.setPass(result.getString(5));
				student.setStudent_role(result.getInt(6));
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
		
		return student;
	}
	
	public Boolean validateUser(String passToValidate, String comparablePas) {
		
		if(passToValidate.equals(comparablePas)) {
			return true;
		}else {
			return false;
		}
	}

}
