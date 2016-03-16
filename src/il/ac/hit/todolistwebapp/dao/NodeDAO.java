/**
 * 
 */
package il.ac.hit.todolistwebapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.models.Item;
import il.ac.hit.todolistwebapp.models.User;
import il.ac.hit.todolistwebapp.utils.HibernateUtil;

/**
 * Implementation of IItemDAO && IUserDAO
 * Singleton pattern
 * @author Lena
 *
 */

public class NodeDAO implements IUserDAO, IItemDAO {
	
	private static NodeDAO instance;
	
	public static synchronized NodeDAO getInstance()
	{
		if (instance == null)
		{
			instance = new NodeDAO();
		}

		return instance;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAllItems(Long userId) throws ToDoListPlatformException {
		
		Session session = null;
		List<Item> items = null;
		
		try {
			 	session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				items =  session.createQuery("from Item where USERID = " + userId).list();
				
				if(items== null){
					
					throw new ToDoListPlatformException("Error: can't load items");
				}
						        
		      } catch (HibernateException e) {
		    	  
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  
		    	  throw new ToDoListPlatformException("Error with getting all items of user", e);
		      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }	

		return items;	
	}

	@Override
	public Item getItem(Long userId, Long itemId) throws ToDoListPlatformException {
		
		Session session = null;
		Item item = null;
		
		try {
			 	session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				item = (Item) session.load(Item.class, itemId);
				
				if(item== null){
					
					throw new ToDoListPlatformException("Error: can't load item");
				}
						        
		      } catch (HibernateException e) {
		    	  
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  
		    	  throw new ToDoListPlatformException("Error with getting item of user", e);
		      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }	

		return item;	
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IItemDAO#createNewItem(java.lang.Long, il.ac.hit.todoapp.models.Item)
	 */
	@Override
	public boolean createNewItem(Long userId, Item newItem) throws ToDoListPlatformException {
		
		boolean success = false;
		
		if(!isItemExist(userId, newItem)){
			
			Session session = null;
			
		    try {
		    	
		    	session = HibernateUtil.getSessionFactory().openSession();	
				session.beginTransaction();
				
				User userInBase = (User) getUser(userId);
				
				userInBase.addItem(newItem);
							
		        session.saveOrUpdate(userInBase);
		        session.getTransaction().commit();

		        success = true;
		        
		      } catch (HibernateException e) {
		    	  
		    	  if(session.getTransaction().wasCommitted()){
						System.out.println("was commited");
					}
			        else{
			        	 System.out.println("no commit");
			        }
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  
		    	  throw new ToDoListPlatformException(e.getMessage(), e);
		      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }		
			}
			else{
				
				throw new ToDoListPlatformException("item exist in webapplication");
			}
		
		System.err.println("done? " + success);
		return success;
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IItemDAO#updateItem(java.lang.Long, il.ac.hit.todoapp.models.Item)
	 */
	@Override
	public boolean updateItem(Long userId, Item itemToUpdate) throws ToDoListPlatformException {
		
		boolean success = false;
		
		if(itemToUpdate!=null){

			Session session = null;
			
		    try {
		    	
		    	session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				Long id = getItemId(itemToUpdate, userId);
				System.err.println("++++++\n"+ id + "\n+++++");
				Item itemInBase = (Item) session.load(Item.class, id);
				System.out.println("++++++\n"+ id + "++++\n");
				System.out.println("++++++\n"+ itemInBase.toString() + "++++\n");
				System.out.println("++++++\n"+ itemToUpdate.toString() + "++++\n");
				System.out.println((isItemExist(userId, itemToUpdate)));
				if(isItemExist(userId, itemToUpdate) == true)
				{
					if(itemInBase != null){
						if(!itemToUpdate.getTitle().equals(null)){
							itemInBase.setTitle(itemToUpdate.getTitle());
							success = true;
						}
						
						if(!(itemToUpdate.getDescription()==null)){
							itemInBase.setDescription(itemToUpdate.getDescription());
							success = true;
						}
						if(!(itemToUpdate.getItemCategory()==null)){
							itemInBase.setItemCategory(itemToUpdate.getItemCategory());
							System.out.println("item catrgory +++\n");
							success = true;
						}
						if(!(itemToUpdate.getDeadLine()==null)){
							itemInBase.setDeadLine(itemToUpdate.getDeadLine());
							success = true;
						}
						if(itemInBase.isDone()==false && itemToUpdate.isDone()==true){
							itemInBase.setDone(itemToUpdate.isDone());
							success = true;
						}
					}
				}
				else{
					throw new ToDoListPlatformException("The item not exist");
				}
				
				session.save(itemInBase);
				
		        session.getTransaction().commit();
		        
		      } catch (HibernateException e) {
		    	  
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  
		    	throw new ToDoListPlatformException(" Error with uppdating items", e);
		      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }		
			}
		System.out.println(success);
		return success;
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IItemDAO#deleteItem(java.lang.Long, il.ac.hit.todoapp.models.Item)
	 */
	@Override
	public boolean deleteItem(Long userId, Item itemToDelete) throws ToDoListPlatformException {
		
		boolean success = false;
		System.err.println("we want to delete"+itemToDelete.toString());
		if(itemToDelete!=null){

			Session session = null;
			
		    try {
		    	
		    	session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				Item itemInBase = (Item) session.load(Item.class, getItemId(itemToDelete, userId));
				System.out.println("+++++++++++\n" + itemInBase.toString() + "\n++++++++++++");
				session.delete(itemInBase);
			    session.getTransaction().commit();
			    
			    success = true;
			    
		    }catch (HibernateException e) {
		    	  
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  
		    	throw new ToDoListPlatformException(" Error with deleting items", e);
		      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }		
			}
		return success;
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IUserDAO#createNewUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean createNewUser(User newUser) throws ToDoListPlatformException {
		
		boolean success = false;
		
		if(newUser!=null && !isUserExistSameNameAndEmail(newUser)){
			System.out.println("\nisUserExistSameNameAndEmail -- " + isUserExistSameNameAndEmail(newUser));
			Session session = null;
			
		    try {
		    	
		    	session = HibernateUtil.getSessionFactory().openSession();	
				session.beginTransaction();
							
		        session.save(newUser);
		        session.getTransaction().commit();
		        
		        success = true;
		        
		      } catch (HibernateException e) {
		    	  
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  
		    	  throw new ToDoListPlatformException("Error: creation new user", e);
			      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }		
			}
		else {
	    	  throw new ToDoListPlatformException("Error: user is null or exist in database");
		}
		return success;
			
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IUserDAO#connectUser(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Long connectUser(String name, String password) throws ToDoListPlatformException {
		
		Long id = null;
		Session session = null;
		
		if(name!=null && password!=null)
		{
			try{
				//session = HibernateUtil.getSessionFactory().openSession();	
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				
				List<User> users = session.createQuery("from User").list();
				
				for(User user: users){
					if(user.getUsername().equals(name) && user.getPassword().equals(password))
						id = user.getId();
				}
				
				session.getTransaction().commit();
				
			}catch(HibernateException ex){
				
				if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
				throw new ToDoListPlatformException("Error: connection user - can't get user id", ex);
	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		}
				
		return id;
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IUserDAO#updateUser(java.lang.Long)
	 */
	@Override
	public boolean updateUser(Long id, User newData) throws ToDoListPlatformException {
		
		boolean success = false;
		
		if(id!=null){

			Session session = null;
			System.err.println("user in base to update user " + id);
			
		    try {
		    	
		    	session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				if(isUserExist(id)){
				User userInBase = (User) session.load(User.class, id);				
				System.out.println("\n***user in base to update user " + userInBase);
				
				
				session.saveOrUpdate(userInBase);
		        session.getTransaction().commit();
		        
				}
				else{
					throw new ToDoListPlatformException("Error: user not exist ->" + id);
				}
		        
		      } catch (Exception e) {
		    	  
		    	  if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new ToDoListPlatformException("No transaction!");
					}
		    	  e.printStackTrace();
		    	throw new ToDoListPlatformException("Error: can't uppdate user ", e);
		      
		      } finally {
		    	  
		        if (session != null && session.isOpen()) {

		          session.close();
		        }
		      }	
			}
		return success;
	}

	/* (non-Javadoc)
	 * @see il.ac.hit.todoapp.dao.IUserDAO#deleteUser(java.lang.Long)
	 */
	@Override
	public boolean deleteUser(Long id) throws ToDoListPlatformException {
		
		boolean success = false;
		Session session = null;
		
	    try {
	    	
	    	session = HibernateUtil.getSessionFactory().openSession();			
			session.beginTransaction();
			User todelete = (User)session.load(User.class, id);
	        session.delete(todelete);
	        session.getTransaction().commit();
	        success = true;
	        
	      } catch (HibernateException e) {
	    	  
	    	  if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
	    	  throw new ToDoListPlatformException("Error: delete the user", e);
	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }	
	    return success;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws ToDoListPlatformException {
		
		Session session = null;
		List<User> users = new ArrayList<User>();
	    try {
	    	
	    	session = HibernateUtil.getSessionFactory().openSession();			
			users = (List<User>) session.createQuery("from User").list();
	        
	      } catch (HibernateException e) {
	    	  
	    	  throw new ToDoListPlatformException("Error: can't get all users", e);
	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }	
	    return users;
	}

	@SuppressWarnings("unchecked")
	private boolean isItemExist(Long userId, Item newItem) throws ToDoListPlatformException {

		boolean isExist = false;
		Session session = null;
		System.out.println("new itemid" + newItem.getId());
		if(newItem!=null && userId!=null)
		{
			try{
				session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				List<Item> items = session.createQuery("from Item where USERID=" + userId).list();
				
				System.out.println(items.toString());
				for(Item itemInBase: items){
					if(newItem.getTitle().equals(itemInBase.getTitle()) || newItem.getId() == itemInBase.getId())
						
						isExist = true;
				}
				System.out.println(isExist);
				session.getTransaction().commit();
				
			}catch(HibernateException ex){
				
				if (session.getTransaction() != null){
					
					session.getTransaction().rollback();
				}
				else {
					
					throw new ToDoListPlatformException("No transaction!");
				}
				
			throw new ToDoListPlatformException("Error with checking items", ex);
	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		}
		else{
			throw new ToDoListPlatformException("Error: iserId or item to check is null");
		}
		
		return isExist;
	}

	@Override
	public User getUser(Long userID) throws ToDoListPlatformException {
		
		User user = null;
		
		Session session = null;
		
	    try {
	    	
	    	session = HibernateUtil.getSessionFactory().openSession();			
			session.beginTransaction();
			
			user = (User) session.get(User.class, userID);
			
			System.out.println("****\n" + user + "\n+++++");
	      } catch (HibernateException e) {
	    	  
	    	  if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
	    	throw new ToDoListPlatformException("Error: can't take user with id", e);
	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		
		return user;
		
	}
	
	@SuppressWarnings("unchecked")
	private Long getItemId(Item item, Long userID) throws ToDoListPlatformException {
		
		Long itemId = null;
		Session session = null;
		
	    try {
	    	
	    	session = HibernateUtil.getSessionFactory().openSession();			
			session.beginTransaction();
			System.err.println(item.toString());
			
			List<Item> itemsOfUser = session.createQuery("from Item where USERID =" + userID).list();
			System.out.println(itemsOfUser.toString());
			for(Item items: itemsOfUser){	
				if(items.getTitle().equals(item.getTitle())){
						System.out.println("itemId = " + items.getTitle());
						itemId = items.getId();
						System.out.println("itemId = " + itemId);
				}
			}
			
	      } catch (HibernateException e) {
	    	  
	    	  if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
	    	throw new ToDoListPlatformException("Error: can't take item id", e);
	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		
		return itemId;
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean isUserExistSameNameAndEmail(User newUser) throws ToDoListPlatformException {
		
		System.err.println("in func isUserExistSameNameAndEmail");
		boolean isExist = false;
		Session session = null;
		System.err.println("\n++++" + newUser.toString() + "\n++++");
		
		if(newUser!=null)
		{
			try{
				session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				List<User> users = session.createQuery("from User").list();
				
				System.err.println("list of user in base " + users.toString());
				
				for(User userInBase: users){
					if(isUserExistSameName(newUser) && newUser.getEmail()==(userInBase.getEmail())){
						isExist = true;
						System.err.println("user exist " + isExist);
					}
						
				}
				
				session.getTransaction().commit();
				
			}catch(HibernateException ex){
				if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
				throw new ToDoListPlatformException("Error: can't check if user exist", ex);	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		}
		System.out.println("is user exist -- " + isExist);
		return isExist;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isUserExistSameName(User newUser) throws ToDoListPlatformException {
		System.err.println("\nisUserExistSameName");
		System.err.println(newUser.toString());
		boolean isExist = false;
		Session session = null;
		
		if(newUser!=null)
		{
			try{
				session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				List<User> usersInBase = session.createQuery("from User").list();
				
				System.out.println("***isUserExistSameName list***\n" + usersInBase.toString());
				System.out.println("srart");
				System.out.println(newUser.getUsername());
				System.out.println(newUser.getPassword());
				for(User user: usersInBase){
					if(user.getUsername().equals(newUser.getUsername()) && user.getPassword().equals(newUser.getPassword())){
						System.out.println(newUser.getUsername() + " " + newUser.getPassword());
						System.out.println(user.getUsername().equals(newUser.getUsername()) + " "+ user.getPassword().equals(newUser.getPassword()));
						isExist = true;
					}
				}
				session.getTransaction().commit();
				
			}catch(HibernateException ex){
				if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
				ex.printStackTrace();
				throw new ToDoListPlatformException("Error: can't check if user exist this same name and password", ex);	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		}
		System.err.println("isUserExistSameName " + isExist);
		return isExist;
	}

	private boolean isUserExist(Long id) throws ToDoListPlatformException {
		
		boolean isExist = false;
		Session session = null;
		
		if(id!=null)
		{
			try{
				session = HibernateUtil.getSessionFactory().openSession();			
				session.beginTransaction();
				
				//List<User> users = session.createQuery("from User").list();
				//System.out.println("isUserExist - list of users " + users.toString());
				User us = (User) session.load(User.class, id);
				System.out.println("isUserExist - load user " + us.toString());
				/*
				for(User userInBase: users){
					
					if(userInBase.getId() == id){
						isExist = true;
						System.err.println("\nfind\n");
					}
					System.err.println("\nnot find\n");
				}*/
				if(us != null){
					isExist = true;
				}
				
				session.getTransaction().commit();
				
			}catch(HibernateException ex){
				if (session.getTransaction() != null)
				{
					session.getTransaction().rollback();
				}
				else
				{
					throw new ToDoListPlatformException("No transaction!");
				}
	    	  
				throw new ToDoListPlatformException("Error: can't check if user exist", ex);	      
	      } finally {
	    	  
	        if (session != null && session.isOpen()) {

	          session.close();
	        }
	      }		
		}
				
		return isExist;
	}
}
