package employeePortal.login;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String Em_Name = req.getParameter("uname");
        String Email = req.getParameter("email");
        String Password = req.getParameter("pass");
        String DateOfBirth = req.getParameter("dob");
        String Gender = req.getParameter("gender");
        
        Employee emp = new Employee(Em_Name, Email, Password, DateOfBirth, Gender);
        
        PrintWriter out = res.getWriter();
        
        boolean isRegistered = false;
		try {
			isRegistered = registerEmployee(emp, req, res);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (isRegistered) {
            res.sendRedirect("login.html");
        } else {
            out.write("Error");
        }	
	}
	private boolean registerEmployee(Employee employee, HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
		String url, uname, pass;
		
		ServletContext ctx = req.getServletContext();
		
		url = ctx.getInitParameter("url");
		uname = ctx.getInitParameter("uname");
		pass = ctx.getInitParameter("pass");
		
		Connection con = DriverManager.getConnection(url, uname, pass);
		
		try {
			
			
			String query = "insert into employee values(?, ?, ?, ?, ?)";
			
			PreparedStatement st = con.prepareStatement(query);
			
			st.setString(1, req.getParameter("uname"));
			st.setString(2, req.getParameter("pass"));
			st.setString(3, req.getParameter("email"));
			st.setString(4, req.getParameter("dob"));
			st.setString(5, req.getParameter("gender")); 
			
			int row = st.executeUpdate();
			
			return row > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	
}
//wait 2 min ok!

