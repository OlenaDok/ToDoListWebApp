package test;


import org.junit.Test;

import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.helper.PasswordHelper;
import junit.framework.TestCase;

public class PasswordEncoderTest extends TestCase{

	private CharSequence password;
	private PasswordHelper ph = new PasswordHelper();
	
	public PasswordEncoderTest() {
		password = "e10adc3949ba59abbe56e057f20f883e";
	}
	
	public void EqualsEncodePassword(){
		assertEquals(password, ph.encode("123456"));
	}
	
	@Test
	public void test() throws ToDoListPlatformException {
		EqualsEncodePassword();
	}

}
