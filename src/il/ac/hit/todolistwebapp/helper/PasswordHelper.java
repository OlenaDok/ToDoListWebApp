package il.ac.hit.todolistwebapp.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Helper class
 * Password encoder
 * @author Lena
 *
 */
public class PasswordHelper {
	
	private MessageDigest md;
	
	public PasswordHelper() {
		try {
			md = MessageDigest.getInstance("MD5");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String encode(CharSequence rawPassword) {
		
		if(md == null){
			return rawPassword.toString();
		}
		if(rawPassword != null){
			
		md.update(rawPassword.toString().getBytes());
		
		StringBuffer hexString = new StringBuffer();
		
		byte byteData[] = md.digest();
		
		for(int i=0; i<byteData.length; i++){
			
			String hex = Integer.toHexString(0xff & byteData[i]);
			
			if(hex.length()==1)
				hexString.append('0');
			
			hexString.append(hex);
		}
		return hexString.toString();
		}
		else
			return null;
	}
	
	public boolean matches(CharSequence rawPassword, String encodedPassword){
		return encode(rawPassword).equals(encodedPassword);
		}

}
