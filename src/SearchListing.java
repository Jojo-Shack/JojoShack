import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Listing;
import datamodel.Tag;
import util.UtilDB;

@WebServlet("/SearchListing")
public class SearchListing extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SearchListing() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      //search(keyword, response);
      List<Listing> DBlistings = UtilDB.searchListings(keyword);
      
      request.setAttribute("matchedListings", DBlistings);
	   
	  RequestDispatcher dispatcher = request.getRequestDispatcher("searchlistings.jsp");
	   
	  dispatcher.forward(request, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      
      List<Listing> listings = UtilDB.searchListings(keyword);
      
      for(Listing listing : listings) {
    	  out.println("<h2 style=\"text-align:center;\">" + listing.getName()+ "</h2><br>");
    	  out.println("<b>Listing Description: </b>" + listing.getDesc()+ "<br>");
    	  out.println("<b>Listing Category: </b>" + listing.getCategory().getName()+ "<br>");
    	  out.print("<b>Listing Tags: </b>");
    	  List<Tag> Ltags = listing.getTags();
    	  for(Tag tag : Ltags) {
    		  out.print(tag.getName() + " ");
    	  }
    	  out.println("<br>");
      }
      
      out.println("<a href=/JojoShack/search.html>Search Listings</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}