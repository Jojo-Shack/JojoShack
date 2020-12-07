import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/JoinListing")
public class JoinListing extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public JoinListing() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String listingId = request.getParameter("joinListing");
	   
	   
	   
	   String destPage = "searchTest.jsp";
	   RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
	   
	   HttpSession session = request.getSession();
	   User user = (User)session.getAttribute("user");
	   
	   // Don't let volunteers post listings
	   if(user.getType() == UserType.VOLUNTEER)
	   {
		   Listing listing = UtilDB.GetListing(Integer.parseInt(listingId));
		   UtilDB.JoinListing(user, listing);
		   dispatcher.forward(request, response);
		   //TODO: Show join confirmation
	   }
	   else {
		   //response.setContentType("text/html");
		   PrintWriter out = response.getWriter();
		   out.println("<script type=\"text/javascript\">");
		   out.println("alert('Incorrect User Type');");
		   out.println("</script>");
		   dispatcher.include(request, response);
		   out.close();
	   }
	   
	   //TODO: Don't let user join a listing they've already joined.
	   
      
   	}

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}