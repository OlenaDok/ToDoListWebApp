package il.ac.hit.todolistwebapp.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Proxy;

/**
 * Entity class of Users
 * @author Lena
 *
 */
@Proxy(lazy=false)
public class User {

	private Long id;
	private String username;
	private String password;
	private String email;
	private boolean isAdmin;
	
	@LazyToOne(value = LazyToOneOption.NO_PROXY)
	private Set<Item> itemsList;
	
	public User() {
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		isAdmin = false;
		this.email = email;
		itemsList = new HashSet<Item>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(Set<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public void addItem(Item item){
		this.itemsList.add(item);
	}
	
	public void deleteItem(Item item){
		this.itemsList.remove(item);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", isAdmin=" + isAdmin + ", itemsList=" + itemsList + "]";
	}
	
	
}
