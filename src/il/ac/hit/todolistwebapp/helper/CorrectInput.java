package il.ac.hit.todolistwebapp.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * Class that checks is input correct, 
 * give not case sensitive input
 * Transformation of date to string and back
 * 
 * @author Lena
 *
 */
public class CorrectInput {
	
	public static boolean isCorrectInput(String username){
		
		Pattern p = Pattern.compile("^[A-Za-z0-9_]+");  
	    Matcher m = p.matcher(username);  
	    
	    return m.matches();
	}
	
	public static String universalInput(String input){
		
		return input.toLowerCase();
	}
	
	public static boolean isInputEmpty(String input){
		
		System.out.println(input);
		return input.isEmpty();
	}
	
	public static void ErrorMessageInputEmpty(String input){
		
		JOptionPane.showMessageDialog(null ,"The " + input + " is empty!\nPlease fill the data!"," ERROR MESSAGE ",JOptionPane.WARNING_MESSAGE);
	}
	
	public static boolean isCorrectEmail(String email){
		
		boolean isCorrect = false;
		
		int atpos = email.indexOf("@");
		int dotpos = email.lastIndexOf(".");
		
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>= email.length())
		  {
			JOptionPane.showMessageDialog(null ,"The e-mail is not correct!\nPlease check again"," ERROR MESSAGE ",JOptionPane.WARNING_MESSAGE);
			return isCorrect;
		  }
		
		isCorrect = true;
		
		return isCorrect;
	}
	
	public static Date getDate(String datestr) {
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm a");
		Date d = new Date();
		
		try
		{
		    d = dateFormat .parse(datestr);
		 		    
		} catch (ParseException e)
		{
		     System.out.print("problem parsing date");
		}
		System.out.println("\n\n **** " + d + " ****\n\n");
		return d;
	}
	
public static String strDate(Date date) {
		
		SimpleDateFormat  dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm a");
		String strDate = null;
		
		strDate = dateFormat.format(date);
		
		return strDate;
	}
}
