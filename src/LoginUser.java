

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.BCrypt;

import datamodel.User;
import util.UtilDB;

@WebServlet("/login")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<User> list = UtilDB.queryUsers(username);
		
		String destPage = "login.jsp";
		
		if (list.size() > 0) {
			if (BCrypt.checkpw(password, list.get(0).getPassword())) {
				User user = new User();
				user.setEmail(list.get(0).getEmail());
				user.setType(list.get(0).getType());
				user.setUsername(list.get(0).getUsername());
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				destPage = "searchTest.jsp";
			}
			else {
				String message = "Invalid username or password";
				request.setAttribute("message", message);
			}
		}
		else {
			String message = "Invalid username or password";
			request.setAttribute("message", message);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

}
