package test;

import org.junit.Test;

import il.ac.hit.todolistwebapp.dao.NodeDAO;
import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.models.User;
import junit.framework.TestCase;

public class ConnectionDBtest extends TestCase{

	private User user = new User();
	
	public ConnectionDBtest(){
		
		user.setUsername("BredPit");
		user.setPassword("123456");
	}
	
	public void isExistTest() throws ToDoListPlatformException{
		
			assertFalse(NodeDAO.getInstance().isUserExistSameName(user));
	}
	
	@Test
	public void test() throws ToDoListPlatformException {
		isExistTest();
	}
		
	
	
	

}
