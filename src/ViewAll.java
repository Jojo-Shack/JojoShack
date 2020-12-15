import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.Listing;
import datamodel.User;
import datamodel.User.UserType;
import util.UtilDB;

@WebServlet("/ViewAll")
public class ViewAll extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public ViewAll() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String destPage = "yourlistings.jsp";
	   RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
	   
	   HttpSession session = request.getSession();
	   User user = (User)session.getAttribute("user");
	   List<Listing> allListings = new ArrayList<Listing>();
	   
	   if(user != null && user.getType() == UserType.VOLUNTEER) {
		   allListings = UtilDB.getJoinedListings(user.getId());
	   }
	   else if(user != null && user.getType() == UserType.ORGANIZATION) {
		   allListings = UtilDB.getOwnedListings(user.getId());
	   }
	   
	   request.setAttribute("listListing", allListings);
	   
	   dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}