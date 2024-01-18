package employeePortal.login;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.*;


@WebServlet("/dashboard")
public class DashboardView extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emname = request.getParameter("uname");
//		String Password1 = request.getParameter("pass");
		
//		database connection 
		String url, uname, pass;
		ServletContext ctx = request.getServletContext();
		url = ctx.getInitParameter("url");
		uname = ctx.getInitParameter("uname");
		pass = ctx.getInitParameter("pass");
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, uname, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "SELECT * FROM employee WHERE uname = ?";
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.setString(1, emname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        ResultSet rs = null;
		try {
			rs = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        try {
			if (rs.next()) {
			    String Em_Name = rs.getString(1);
			    String Password = rs.getString(2);
			    String Email = rs.getString(3);
				String DOB = rs.getString(4);
				String Gender = rs.getString(5);
			    
				PrintWriter out = response.getWriter();
				
				out.println("<h1>" + Em_Name + "</h1>");
				out.println("<h1>" + Password + "</h1>");
				out.println("<h1>" + Email + "</h1>");
				out.println("<h1>" + DOB + "</h1>");
				out.println("<h1>" + Gender + "</h1>");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}

}
