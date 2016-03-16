package il.ac.hit.todolistwebapp.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Class for getting session factory
 * for interactions with the database
 * @author Lena
 *
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
	      return sessionFactory;
	}
	
	static {  
		
  	  try{
  		 sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
  		 //sessionFactory = new Configuration().configure().buildSessionFactory();
  		 System.out.println(sessionFactory.toString());
  		  
    } catch (Throwable ex) {
    	
    	System.err.println("Initial SessionFactory creation failed.\n" + ex);
    	throw new ExceptionInInitializerError(ex);
    }
  }
}
