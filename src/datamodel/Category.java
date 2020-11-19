package datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name", nullable = false, columnDefinition = "varchar(100)")
   private String name;
   
   @Column(name = "description", columnDefinition = "varchar(10000)")
   private String description;
   
   @OneToMany(mappedBy="category")
   private List<Listing> listings = new ArrayList<Listing>();
   
   public Category() {
	   super();
   }
   
   public Category(Integer id, String name, String description, List<Listing> listings) {
	      this.id = id;
	      this.name = name;
	      this.description = description;
	      this.listings = listings;
	   }

	   public Category(String name, String description, List<Listing> listings) {
	      this.name = name;
	      this.description = description;
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

	   public String getDesc() {
	      return description;
	   }

	   public void setDesc(String description) {
	      this.description = description;
	   }
	   
	   public List<Listing> getListings(){
		   return listings;
	   }
	   
	   public void setListings(List<Listing> listings) {
		   this.listings = listings;
	   }
	   
	   public void addListing(Listing l) {
		   this.listings.add(l);
		   l.setCategory(this);
	   }
	   // TODO: Double check how to do a bidirectional relationship for Listing
}