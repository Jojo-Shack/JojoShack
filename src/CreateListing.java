import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
      String category = request.getParameter("category");
      int categoryID = 1;
      String tags = request.getParameter("tags");
      List<String> tagList = new ArrayList<String>();
      
      // Are there any tags at all?
      if (tags == "") {
    	  System.out.println("Tags are null");
      }
      // Are there multiple tags?
      else if (tags.indexOf(',') == -1) {
    	  tagList.add(tags);
      }
      // Did they split with only a comma and not a space?
      else if (tags.indexOf(", ") == -1) {
    	  String[] tagArray = tags.split(",");
    	  for(String tag : tagArray) {
    		  tagList.add(tag);
    	  }
      }
      // There are multiple tags and they are split with a comma and a space
      else {
    	  String[] tagArray = tags.split(", ");
    	  for(String tag : tagArray) {
    		  tagList.add(tag);
    	  }
      }
      
      
      if(category.indexOf("indoor") != -1) {
		  categoryID = 3;
	  }
	  else if(category.indexOf("outdoor") != -1) {
		  categoryID = 4;
	  }
      
      //TODO: Make utility for optional parameters: https://stackoverflow.com/questions/6335759/servlet-handling-many-optional-parameters
      //String categoryName = request.getParameter("cname");
      //String tagName = request.getParameter("tname");
      
      String destPage = "searchTest.jsp";
      RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
      
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      
      // First check for a user so we don't get a null pointer exception
      if(user == null) {
    	  PrintWriter out = response.getWriter();
    	  out.println("<script type=\"text/javascript\">");
    	  out.println("alert('User Not Logged In');");
    	  out.println("</script>");
    	  dispatcher.include(request, response);
    	  out.close();
      }
      // Don't let volunteers post listings
      else if(user.getType() == UserType.ORGANIZATION)
      {
    	  
    	  UtilDB.createListing(name, description, categoryID, tagList, user);
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