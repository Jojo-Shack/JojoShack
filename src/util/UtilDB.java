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
   public static void createListing(String name, String description) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Listing(name, description));
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
   public static void createTag(String name) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Tag(name));
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
   public static List<User> checkLogin(String username) {
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
}
