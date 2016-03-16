/**
 * 
 */
package il.ac.hit.todolistwebapp.dao;

import java.util.List;

import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.models.Item;

/**
 * Interface Item DAO
 * @author Lena
 *
 */
public interface IItemDAO {
	
	/**
	 * Creation new Item
	 * @param userId
	 * @param newItem
	 * @return boolean - is item was create
	 * @throws ToDoListPlatformException
	 */
	public boolean createNewItem(Long userId, Item newItem) throws ToDoListPlatformException;
	
	/**
	 * Update item in database
	 * @param userId
	 * @param itemToUpdate
	 * @return boolean - is item was update
	 * @throws ToDoListPlatformException
	 */
	public boolean updateItem(Long userId, Item itemToUpdate) throws ToDoListPlatformException;
	
	/**
	 * Get Item from database
	 * @param userId
	 * @param itemId
	 * @return User's Item 
	 * @throws ToDoListPlatformException
	 */
	public Item getItem(Long userId, Long itemId) throws ToDoListPlatformException;
	
	/**
	 * Delete Item from database
	 * @param userId
	 * @param itemToDelete
	 * @return boolean - is item was delete
	 * @throws ToDoListPlatformException
	 */
	public boolean deleteItem(Long userId, Item itemToDelete) throws ToDoListPlatformException;
	
	/**
	 * Get all user's items
	 * @param userId
	 * @return list of all user's items 
	 * @throws ToDoListPlatformException
	 */
	public List<Item> getAllItems(Long userId) throws ToDoListPlatformException;
}
