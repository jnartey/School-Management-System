package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Instructor;
import CoreJava.systemsInterfaces.InstructorDAOI;
import CoreJava.DAO.OracleConnection;
import CoreJava.Utils.OracleQueries;

public class InstructorDAO implements InstructorDAOI {

	@Override
	public List<Instructor> getAllInstructors() throws SQLException {
		List<Instructor> list = new ArrayList<Instructor>();
		
		Instructor instructor = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETALLINSTRUCTORS);
			
			// execute query is only for reading data from the database 
			result = stmt.executeQuery();
			
				while(result!= null && result.next()) {
					instructor = new Instructor();
					instructor.setInstructor_id(result.getInt(1));
					instructor.setFull_name(result.getNString(2));
					instructor.setEmail(result.getNString(3));
					instructor.setSpecialty(result.getString(4));
					instructor.setInstructor_role(result.getInt(5));
					instructor.setPass(result.getString(6));
					list.add(instructor);
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
	public Instructor getInstructoByGmail(String email) throws SQLException {
		Instructor instructor = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		OracleConnection connection = new OracleConnection();
		
		try {
			conn = connection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETINSTRUCTORBYGMAIL);
			stmt.setString(1, email);
			
			result = stmt.executeQuery();
			
			while(result!= null && result.next()) {
				instructor = new Instructor();
				instructor.setInstructor_id(result.getInt(1));
				instructor.setFull_name(result.getNString(2));
				instructor.setEmail(result.getNString(3));
				instructor.setSpecialty(result.getString(4));
				instructor.setInstructor_role(result.getInt(5));
				instructor.setPass(result.getString(6));
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
		
		return instructor;
	}
	
	public String validateUser(Instructor ins, String comparablePas) {
		String response = "Wrong Credentials";
		
		if(ins.getPass().equals(comparablePas)) {
			if(ins.getInstructor_role() == 0) {
				response = "Instructor";
			}else if(ins.getInstructor_role() == 1) {
				response = "Admin";
			}
		}
		
		return response;
	}
	
}
