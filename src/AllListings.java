import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Listing;
import util.UtilDB;




@WebServlet("/AllListings")
public class AllListings extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AllListings() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   List<Listing> DBlistings = UtilDB.searchListings("");
	   List<Listing> allListings = new ArrayList<Listing>();
	   
	   
	   for( Listing DBlisting : DBlistings) {
		   Listing listing = new Listing();
		   listing.setId(DBlisting.getId());
		   listing.setName(DBlisting.getName());
		   listing.setCategory(DBlisting.getCategory());
		   listing.setOwner(DBlisting.getOwner());
		   listing.setTags(DBlisting.getTags());
		   listing.setDesc(DBlisting.getDesc());
		   
		   allListings.add(listing);
	   }
	   
	   
	   request.setAttribute("listListing", allListings);
	   
	   RequestDispatcher dispatcher = request.getRequestDispatcher("listings.jsp");
	   
	   dispatcher.forward(request, response);
      
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}