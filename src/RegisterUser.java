

import java.io.IOException;

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
		
		//TODO: add check user name unique
		
		// add user to db
		UtilDB.registerUser(username, hashed, email, usertype);
		
		// save user object to session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//forward to search page
		RequestDispatcher dispatcher = request.getRequestDispatcher("searchTest.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
