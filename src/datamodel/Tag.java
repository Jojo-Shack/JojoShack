package datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name", nullable = false, columnDefinition = "varchar(100)")
   private String name;
   
   @ManyToMany(mappedBy="tags")
   private List<Listing> listings = new ArrayList<Listing>();
   
   public Tag() {
	   super();
   }
   
   public Tag(Integer id, String name, List<Listing> listings) {
	  this.id = id;
      this.name = name;
      this.listings = listings;
   }
   
   public Tag(String name, List<Listing> listings) {
      this.name = name;
      this.listings = listings;
   }
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public List<Listing> getListings(){
	   return listings;
   }
   
   public void setListings(List<Listing> listings) {
	   this.listings = listings;
   }
   
   public void addListing(Listing l) {
	   this.listings.add(l);
	   l.getTags().add(this);
   }
   
   public void removeListing(Listing l) {
	   this.listings.remove(l);
	   l.getTags().remove(this);
   }
}