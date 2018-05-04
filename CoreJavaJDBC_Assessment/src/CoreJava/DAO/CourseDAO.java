package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Course;
import CoreJava.Utils.OracleQueries;
import CoreJava.systemsInterfaces.CourseDAOI;

public class CourseDAO implements CourseDAOI {

	@Override
	public List<Course> getAllCourses() throws SQLException {
		List<Course> list = new ArrayList<Course>();
		
		Course course = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETALLCOURSES);
			
			// execute query is only for reading data from the database 
			result = stmt.executeQuery();
			
				while(result!= null && result.next()) {
					course = new Course();
					course.setCourse_id(result.getInt(1));
					course.setCourse_name(result.getString(2));
					course.setMinimum_gpa(result.getDouble(3));
					list.add(course);
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

	@Override
	public List<Course> getCourseByInstructor(int instructor_id) throws SQLException {
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETCOURSEBYINSTRUCTOR);
			stmt.setInt(1, instructor_id);
			
			result = stmt.executeQuery();
			
			while(result!= null && result.next()) {
				course = new Course();
				course.setCourse_id(result.getInt(1));
				course.setCourse_name(result.getString(2));
				course.setMinimum_gpa(result.getDouble(3));
				
				list.add(course);
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
