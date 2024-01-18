package employeePortal.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Em_Name = request.getParameter("uname");
		String Password = request.getParameter("pass");

		String url, uname, pass;

		ServletContext ctx = request.getServletContext();
		PrintWriter out = response.getWriter();
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
		String query = "SELECT * FROM employee WHERE uname = ? AND pass = ?";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, Em_Name) ;
			stmt.setString(2, Password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				String emname = rs.getString(1);
				String password = rs.getString(2);
				if (emname.equals(Em_Name) && password.equals(Password)) {

					RequestDispatcher rd = request.getRequestDispatcher("dashboard");
					rd.forward(request, response);
				}
			} else {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				out.println("sike!,its the wrong password!");

				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}
