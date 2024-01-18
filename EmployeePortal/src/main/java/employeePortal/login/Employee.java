package employeePortal.login;

public class Employee {
	
	String Em_Name;
	String email;
	String password;
	String dob;
	String gender;

	public Employee(String Em_Name, String email, String password, String dob, String gender ) {
		super();
		this.Em_Name = Em_Name;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		
	}
}
