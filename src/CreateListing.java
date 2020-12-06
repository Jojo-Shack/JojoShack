import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.User;
import datamodel.User.UserType;
import util.UtilDB;

@WebServlet("/CreateListing")
public class CreateListing extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CreateListing() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("title");
      String description = request.getParameter("desc");
      //TODO: Make utility for optional parameters: https://stackoverflow.com/questions/6335759/servlet-handling-many-optional-parameters
      //String categoryName = request.getParameter("cname");
      //String tagName = request.getParameter("tname");
      
      String destPage = "searchTest.jsp";
      RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
      
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      
      // Don't let volunteers post listings
      if(user.getType() == UserType.ORGANIZATION)
      {
    	  UtilDB.createListing(name, description, user);
    	  dispatcher.forward(request, response);
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
      
      
      
      
      
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}