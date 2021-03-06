/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Category;
import datamodel.Listing;
import datamodel.Tag;
import datamodel.User;
import datamodel.User.UserType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   // Search Listing Names
   public static List<Listing> searchListings(String keyword) {
      List<Listing> resultList = new ArrayList<Listing>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         
         System.out.println((Listing)session.get(Listing.class, 1)); 
         List<?> listings = session.createQuery("FROM Listing").list();
         
         for (Iterator<?> iterator = listings.iterator(); iterator.hasNext();) {
        	 Listing listing = (Listing) iterator.next();
            if (listing.getName().toLowerCase().contains(keyword) || listing.getName().contains(keyword)) {
               resultList.add(listing);
            }
            List<Tag> tags = listing.getTags();
            for (Tag tag : tags) {
            	if(tag.getName().toLowerCase().contains(keyword) || tag.getName().contains(keyword)) {
            		resultList.add(listing);
            	}
            }
         }
         tx.commit();
         
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      
      return resultList;
   }
   
   
   //Create Listing
   public static void createListing(String name, String description, int categoryID, List<String> tags, User user) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
    	  // Get the user object from the database and don't save the httpsession user object directly.
    	 User owner = (User) session.get(User.class, user.getId());
         tx = session.beginTransaction();
         session.save(owner);
         
         Category category = (Category) session.get(Category.class, categoryID);
         
         Listing newListing = new Listing(name, description, owner);
         newListing.setCategory(category);
         
         // TODO: Check to see if tags exist before creating them? If we want to be less lazy that is
         for (String tag : tags) {
        	 Tag newTag = new Tag();
        	 newTag.setName(tag);
        	 createTag(newTag);
        	 newListing.addTag(newTag);
         }
         
         session.save(newListing);
         
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   // TODO: Learn how to create entities with relationships
   //Create Listing with category and tags
//   public static void createListing(String name, String description, Category category, List<Tag> tags) {
//      Session session = getSessionFactory().openSession();
//      Transaction tx = null;
//      try {
//         tx = session.beginTransaction();
//         session.save(new Listing(name, description, category, tags));
//         tx.commit();
//      } catch (HibernateException e) {
//         if (tx != null)
//            tx.rollback();
//         e.printStackTrace();
//      } finally {
//         session.close();
//      }
//   }
   
   //Create Tag
   public static void createTag(Tag tag) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(tag);
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   //Create Category
   public static void createCategory(String name, String description) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Category(name, description));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   //register user
   public static void registerUser(String username, String password, String email, UserType userType) {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   
	   try {
		   tx = session.beginTransaction();
		   session.save(new User(username, email, password, userType));
		   tx.commit();
	   }
	   catch (HibernateException e ) {
		   if (tx != null)
			   tx.rollback();
		   e.printStackTrace();
	   } finally {
		   session.close();
	   }
   }
   
   //check login
   public static List<User> queryUsers(String username) {
	   List<User> results = new ArrayList<User>();

	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   
	   try {
		   tx = session.beginTransaction();
		   
		   List<?> users = session.createQuery("FROM User").list();
	         
	       for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	    	   User user = (User) iterator.next();
	    	   if (user.getUsername().equals(username)) {
	    		   results.add(user);
	    	   }
	       }
	       tx.commit();
	   } catch (HibernateException e) {
	        if (tx != null)
	            tx.rollback();
	        e.printStackTrace();
	   } finally {
	        session.close();
	   }
	   
	   return results;
   }
   
   //Get Listing
   public static Listing GetListing(int listId) {
      Session session = getSessionFactory().openSession();
      Listing joinedListing = new Listing();
      try {
    	 joinedListing = (Listing) session.get(Listing.class, listId);
      } catch (HibernateException e) {
         e.printStackTrace();
      } finally {
         session.close();
      }
      return joinedListing;
   }
   
   //Join Listing
   public static void JoinListing(User user, Listing listing) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
    	 User owner = (User) session.get(User.class, user.getId());
    	 Listing joinedListing = (Listing) session.get(Listing.class, listing.getId());
    	 owner.addJoinedListings(joinedListing);
         tx = session.beginTransaction();
         session.save(owner);
         session.save(joinedListing);
         
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   //Delete Listing
   public static void DeleteListing(int listId) {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   try {
		   Listing listing = (Listing) session.get(Listing.class, listId);
		   tx = session.beginTransaction();
		   session.delete(listing);
		   
		   tx.commit();
		   System.out.println(listing.getId().toString() + " Deleted");
	   } catch (HibernateException e) {
		   e.printStackTrace();
	   } finally {
		   session.close();
	   }
   	}
   
   // Returns Listings owned by that user id
   public static List<Listing> getOwnedListings(int userId){
	   	Session session = getSessionFactory().openSession();
	   	List<Listing> listings = new ArrayList<Listing>();
	   	
	   	try {
	   		User user = (User) session.get(User.class, userId);
	   		listings = user.getOwnedListings();
	   		
	   	} catch (HibernateException e) {
	        e.printStackTrace();
     	} finally {
	        session.close();
     	}
	   	
	   	return listings;
   }
   
   // Returns Listings joined by that user id
   public static List<Listing> getJoinedListings(int userId){
	   	Session session = getSessionFactory().openSession();
	   	List<Listing> listings = new ArrayList<Listing>();
	   	
	   	try {
	   		User user = (User) session.get(User.class, userId);
	   		listings = user.getJoinedListings();
	   		
	   	} catch (HibernateException e) {
	        e.printStackTrace();
      	} finally {
	        session.close();
      	}
	   	
	   	return listings;
  }
   
}
