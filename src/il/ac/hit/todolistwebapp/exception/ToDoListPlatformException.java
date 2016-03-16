package il.ac.hit.todolistwebapp.exception;

/**
 * Exception of this application
 * @author Lena
 *
 */
public class ToDoListPlatformException extends Exception {

	
	private static final long serialVersionUID = 6007692626121607610L;
	
	public ToDoListPlatformException(String msg)
	{
		super(msg);
	}
	public ToDoListPlatformException(String msg, Throwable throwable)
	{
		super(msg, throwable);
	}
}
