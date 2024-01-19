package accounts.student.login;

import java.util.ResourceBundle;

public class Authentication {

	public boolean logIn(String uname, String pass) {
		
		ResourceBundle resource = ResourceBundle.getBundle("credentials");
		
		String username, password;
		
		username = resource.getString("username");
		password = resource.getString("password");
		
		return uname.equals(username) && pass.equals(password);
	}
}