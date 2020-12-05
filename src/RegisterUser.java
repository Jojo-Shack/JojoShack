

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
import datamodel.User.UserType;
import util.UtilDB;

@WebServlet("/register")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterUser() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserType usertype = UserType.valueOf(request.getParameter("usertype"));
		
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
		
		// save email and type to user object
		User user = new User();
		user.setEmail(email);
		user.setType(usertype);
		user.setUsername(username);
		
		//user name unique
		List<User> list = UtilDB.queryUsers(username);
		
		String destPage = "register.jsp";
		
		if (list.size() == 0) {
			// add user to db
			UtilDB.registerUser(username, hashed, email, usertype);
			
			// save user object to session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			destPage = "searchTest.jsp";
		}
		else {
			String message = "username already used";
			request.setAttribute("message", message);
		}
		
		
		
		//forward to search page
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
		
		
	}

}
