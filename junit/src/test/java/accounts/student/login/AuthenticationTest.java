package accounts.student.login;

import org.junit.*;

public class AuthenticationTest {
	@Test
	public void testcase_1() {
		
		Authentication app = new Authentication();
		
		Assert.assertEquals(true, app.logIn("ddd", "ddd@676"));
	}
	
	@Test
	public void testcase_2() {
		
		Authentication app = new Authentication();
		
		Assert.assertEquals(false, app.logIn("ddd", "ddd@667"));
	}
}