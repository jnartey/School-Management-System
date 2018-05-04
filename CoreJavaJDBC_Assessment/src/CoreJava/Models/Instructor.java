package CoreJava.Models;

public class Instructor {
	private int instructor_id;
	private String full_name;
	private String email;
	private String specialty;
	private int instructor_role;
	private String pass;
	
	/**
	 * @param instructor_id
	 * @param full_name
	 * @param email
	 * @param specialty
	 * @param instructor_role
	 * @param pass
	 */
	public Instructor(int instructor_id, String full_name, String email, String specialty, int instructor_role,
			String pass) {
		this.instructor_id = instructor_id;
		this.full_name = full_name;
		this.email = email;
		this.specialty = specialty;
		this.instructor_role = instructor_role;
		this.pass = pass;
	}
	
	public Instructor() {
		this.instructor_id = 0;
		this.full_name = "";
		this.email = "";
		this.specialty = "";
		this.instructor_role = 0;
		this.pass = "";
	}

	public int getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getInstructor_role() {
		return instructor_role;
	}

	public void setInstructor_role(int instructor_role) {
		this.instructor_role = instructor_role;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
