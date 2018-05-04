package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Teaching;
import CoreJava.Utils.OracleQueries;
import CoreJava.systemsInterfaces.TeachingDAOI;

public class TeachingDAO implements TeachingDAOI {

	@Override
	public int registerInstructorToCourse(int course_id, int instructor_id) throws SQLException {
		Integer ID = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		OracleConnection connection = new OracleConnection();
		String [] COL = {"TEACHING_ID"};
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.SAVEINSTRUCTORTOCOURSE, COL);
			stmt.setInt(1, course_id);
			stmt.setInt(2, instructor_id);
			
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
		
		return ID;
	}

	@Override
	public List<Teaching> getIntructorsCourses() throws SQLException {
		List<Teaching> list = new ArrayList<Teaching>();
		
		Teaching teaching = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETINSTRUCTORSCOURSES);
			
			// execute query is only for reading data from the database 
			result = stmt.executeQuery();
			
			while(result!= null && result.next()) {
				teaching = new Teaching();
				teaching.setCourse_name(result.getString(1));
				teaching.setMinimum_gpa(result.getDouble(2));
				teaching.setFull_name(result.getString(3));
				teaching.setEmail(result.getString(4));
				list.add(teaching);
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
