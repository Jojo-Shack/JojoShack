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
import util.UtilDB;

@WebServlet("/DeleteListing")
public class DeleteListing extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DeleteListing() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String listingId = request.getParameter("deleteListing");
	   
	   String destPage = "searchTest.jsp";
	   RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
	   
	   HttpSession session = request.getSession();
	   User user = (User)session.getAttribute("user");
	   
	   Listing listing = UtilDB.GetListing(Integer.parseInt(listingId));
	   
	   if (user.getId() == listing.getOwner().getId()) {
		   UtilDB.DeleteListing(Integer.parseInt(listingId));
		   dispatcher.forward(request, response);
	   }
	   else {
		   PrintWriter out = response.getWriter();
		   out.println("<script type=\"text/javascript\">");
		   out.println("alert('You do not own this listing');");
		   out.println("</script>");
		   dispatcher.include(request, response);
		   out.close();
	   }
	   
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}