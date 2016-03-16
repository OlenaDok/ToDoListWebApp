package il.ac.hit.todolistwebapp.dao;

import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.models.User;

/**
 * Interface User DAO
 * @author Lena
 *
 */
public interface IUserDAO {
	
	/**
	 * Creation of new user
	 * @param newUser
	 * @return boolean - is user was create
	 * @throws ToDoListPlatformException
	 */
	public boolean createNewUser(User newUser) throws ToDoListPlatformException;
	
	/**
	 * Connection user
	 * @param name
	 * @param password
	 * @return user's id
	 * @throws ToDoListPlatformException
	 */
	public Long connectUser(String name, String password) throws ToDoListPlatformException;
	
	/**
	 * Get user 
	 * @param id
	 * @return user
	 * @throws ToDoListPlatformException
	 */
	public User getUser(Long id) throws ToDoListPlatformException;
	
	/**
	 * Update user
	 * @param id
	 * @param newData
	 * @return boolean - is user was update
	 * @throws ToDoListPlatformException
	 */
	public boolean updateUser(Long id, User newData) throws ToDoListPlatformException;
	
	/**
	 * Delete user
	 * @param id
	 * @return boolean - is user was delete
	 * @throws ToDoListPlatformException
	 */
	public boolean deleteUser(Long id) throws ToDoListPlatformException;

}
