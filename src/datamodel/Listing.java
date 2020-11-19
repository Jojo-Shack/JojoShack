package datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "listing")
public class Listing {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name", nullable = false, columnDefinition = "varchar(100)")
   private String name;

   @Column(name = "description", columnDefinition = "varchar(10000)")
   private String description;
   
   @ManyToOne
   @JoinColumn(name="fk_category")	// Foreign Key
   private Category category;
   
   @ManyToMany
   @JoinTable(
		   name = "listing_tag", 
		   joinColumns = { 
				   @JoinColumn(name = "fk_listing") //Foreign Key
				   }, 
		   inverseJoinColumns = { 
				   @JoinColumn(name = "fk_tag") //Foreign Key
				   }
		   )
   private List<Tag> tags = new ArrayList<Tag>();
   
   public Listing() {
	   super();
   }

   public Listing(Integer id, String name, String description, Category category, List<Tag> tags) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.category = category;
      this.tags = tags;
   }

   public Listing(String name, String description, Category category, List<Tag> tags) {
      this.name = name;
      this.description = description;
      this.category = category;
      this.tags = tags;
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
   
   public Category getCategory() {
	   return category;
   }
   
   public void setCategory(Category category) {
	   this.category = category;
   }
   
   public List<Tag> getTags(){
	   return tags;
   }
   
   public void setTags(List<Tag> tags) {
	   this.tags = tags;
   }
   
   public void addTag(Tag t) {
	   this.tags.add(t);
	   t.getListings().add(this);
   }
   
   public void removeTag(Tag t) {
	   this.tags.remove(t);
	   t.getListings().remove(this);
   }
   
   // TODO: Double check how to do a bidirectional relationship for Category
}