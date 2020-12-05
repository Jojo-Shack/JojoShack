package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	public enum UserType {
		VOLUNTEER,
		ORGANIZATION
	}
	
	@Id  // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "username", nullable = false, columnDefinition = "varchar(45)")
	private String username;
	
	@Column(name = "email", nullable = false, columnDefinition = "varchar(45)")
	private String email;
	
	@Column(name = "password", nullable = false, columnDefinition = "varchar(65)")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserType type;

	public User() {
		super();
	}

	public User(Integer id, String username, String email, String password, UserType type) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public User(String username, String email, String password, UserType type) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
}