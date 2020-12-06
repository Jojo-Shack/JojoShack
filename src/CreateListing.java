import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.User;
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
      
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      
      System.out.println("User ID: " + user.getId());
      
      //TODO: Make utility for optional parameters: https://stackoverflow.com/questions/6335759/servlet-handling-many-optional-parameters
      //String categoryName = request.getParameter("cname");
      //String tagName = request.getParameter("tname");

      UtilDB.createListing(name, description, user);
      
      String destPage = "searchTest.jsp";
      RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
      dispatcher.forward(request, response);
      
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}