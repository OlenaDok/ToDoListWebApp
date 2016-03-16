package il.ac.hit.todolistwebapp.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.hit.todolistwebapp.dao.NodeDAO;
import il.ac.hit.todolistwebapp.helper.CorrectInput;
import il.ac.hit.todolistwebapp.helper.PasswordHelper;
import il.ac.hit.todolistwebapp.models.*;


/**
 * Class implement HttpServlet
 * @author Lena
 *
 */
@WebServlet("/controller/*")
public class Controller extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8831555613516838658L;
	
	private static final String P_HOME = "/home";
	private static final String P_LOGIN = "/login";
	private static final String P_NEWUSER = "/newuser";
	private static final String P_ADDITEM = "/additem";
	private static final String P_UPDATEITEM = "/updateitem";
	private static final String P_UPDATING = "/updating";
	private static final String P_DELETEITEM = "/deleteitem";
	private static final String P_LOGUOT = "/logout";
	private static final String P_INFORM = "/inform";
	
	private static final String JSP_BASE = "http://localhost:8080/ToDoListWebApp";
	private static final String JSP_NEWUSER = "/newuser.jsp";
	private static final String JSP_LOGIN = "/login.jsp";
	private static final String JSP_HOME = "/index.jsp";
	private static final String JSP_ERROR = "/error.jsp";
	private static final String JSP_MYHOMEPAGE = "/myhomepage.jsp";
	private static final String JSP_UPDATEITEM = "/update.jsp";
	private static final String JSP_INFORM= "/inform.jsp";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		User user = null;
		PasswordHelper ph = new PasswordHelper();
		
		String univerUsername = null, univerPassword = null, univerEmail = null;
		String forwardPage = null;
		
		response.setContentType("text/html");
		
		switch(path){
		
		case P_NEWUSER:
		{
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			boolean isExist = false;
			String message = null;
			
			
			System.out.println(userName);
			if(CorrectInput.isInputEmpty(userName))	{
				message = "User name is not correct.\nPlease try again.";
				request.setAttribute("userMessage", message);
				forwardPage = JSP_NEWUSER;
				dispatcher = getServletContext().getRequestDispatcher(JSP_NEWUSER);
				dispatcher.forward(request, response);
				break;
			}
			if(CorrectInput.isInputEmpty(password)) {
				message = "Password is not correct.\nPlease try again.";
				request.setAttribute("userMessage", message);
				forwardPage = JSP_NEWUSER;
				dispatcher = getServletContext().getRequestDispatcher(JSP_NEWUSER);
				dispatcher.forward(request, response);
				break;
			}
			if(CorrectInput.isInputEmpty(email)) {
				message = "Email is not correct.\nPlease try again.";
				request.setAttribute("userMessage", message);
				forwardPage = JSP_NEWUSER;
				dispatcher = getServletContext().getRequestDispatcher(JSP_NEWUSER);
				dispatcher.forward(request, response);
				break;
			}
			System.out.println(CorrectInput.isCorrectInput(password));
			if(CorrectInput.isCorrectInput(userName) && CorrectInput.isCorrectInput(password) && CorrectInput.isCorrectEmail(email)){
				univerUsername = CorrectInput.universalInput(userName);
				univerPassword = CorrectInput.universalInput(password);
				univerEmail = CorrectInput.universalInput(email);
			}
			else{
				message = "One or more fields is not correct.\nPlease try again.";
				request.setAttribute("userMessage", message);
				forwardPage = JSP_NEWUSER;
				dispatcher = getServletContext().getRequestDispatcher(JSP_NEWUSER);
				dispatcher.forward(request, response);
				break;
			}
			
			user = new User(univerUsername, ph.encode(univerPassword), univerEmail);
			System.out.println("new user " + user.toString());
			System.out.println("+++++" + ph.encode(univerPassword) + "++++++++++++");
			try{
				isExist = NodeDAO.getInstance().isUserExistSameName(user);
				System.out.println("controller- is user exsist? " + isExist);
				if(isExist!=true){
					
					NodeDAO.getInstance().createNewUser(user);
				
					Long userId = NodeDAO.getInstance().connectUser(univerUsername, ph.encode(univerPassword));
					Cookie cookie = new Cookie("userID", String.valueOf(userId));
					
					request.getSession().setAttribute("userID", userId);
					request.getSession().setAttribute("userName", univerUsername);
					
					if (cookie != null)
					{
						cookie.setMaxAge(3600);
						response.addCookie(cookie);
					}
					forwardPage = JSP_MYHOMEPAGE;
					request.getSession().setAttribute("userID", userId);
					request.getSession().setAttribute("username", univerUsername);
					dispatcher = getServletContext().getRequestDispatcher(forwardPage);
					dispatcher.forward(request, response);
					break;
			}
			else{
				request.setAttribute("userMessage", "User already exist");
				forwardPage = JSP_LOGIN;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
			}
				//dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				//dispatcher.forward(request, response);
				
			}catch(Exception ex){
				
				forwardPage = JSP_ERROR;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
				break;
			}
		}
		
		case P_LOGIN:
		{
			Long userID = 0L;
			User userLogin = new User();
			boolean isExist = false;
			System.out.println("srart" + forwardPage);
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(CorrectInput.isCorrectInput(userName) && CorrectInput.isCorrectInput(password)){
				univerUsername = CorrectInput.universalInput(userName);
				univerPassword = CorrectInput.universalInput(password);
			}
			System.out.println("correct input " + univerUsername + " " + univerPassword);	
			userLogin.setUsername(univerUsername);
			userLogin.setPassword(ph.encode(univerPassword));
			System.out.println(userLogin.toString());
			
			try{
				isExist = NodeDAO.getInstance().isUserExistSameName(userLogin);
				System.out.println("controller- is user exsist? " + isExist);
				if(isExist==true){
									
				userID = NodeDAO.getInstance().connectUser(univerUsername, ph.encode(univerPassword));
				System.out.println("user id = " + userID);
				
				Cookie cookie = new Cookie("userID", String.valueOf(userID));
				if (cookie != null)
				{
					cookie.setMaxAge(3600);
					response.addCookie(cookie);
				}

				request.getSession().setAttribute("userID", userID);
				request.getSession().setAttribute("userName", univerUsername);
				
				forwardPage = JSP_MYHOMEPAGE;
					
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
				break;
				}
				else {
					
					request.setAttribute("userMessage", "User not exist");
					forwardPage = JSP_NEWUSER;
					System.err.println("we are going to " + forwardPage);
					
					dispatcher = getServletContext().getRequestDispatcher(JSP_NEWUSER);
					dispatcher.forward(request, response);
					break;
				}
			}catch(Exception ex){
				forwardPage = JSP_ERROR;
				request.setAttribute("userMessage", "Can't conect user ");
				dispatcher = getServletContext().getRequestDispatcher(JSP_ERROR);
				ex.printStackTrace();
				dispatcher.forward(request, response);
				break;
			}
		}
		
		case P_LOGUOT:
		{
			request.logout();
			request.getSession().invalidate();
			forwardPage = JSP_BASE + JSP_HOME; 
			System.out.println("we are going to " + forwardPage);
			response.sendRedirect(forwardPage);
			//dispatcher = request.getServletContext().getRequestDispatcher(forwardPage);
			//dispatcher.forward(request, response);
			break;
		}
		
		case P_INFORM:
		{
			forwardPage = P_HOME; 
			System.out.println("we are going to " + forwardPage);
			dispatcher = request.getServletContext().getRequestDispatcher(forwardPage);
			dispatcher.forward(request, response);
			break;
		}
		
		case P_DELETEITEM:
		{
			System.out.println("in delete item");
			String message = null;
			boolean isDelete;
			
			String id = request.getParameter("itemID");
			Long itemID = Long.parseLong(id);
			Long userID = (Long) (request.getSession().getAttribute("userID"));
			System.out.println("item id = " + itemID);
						
			try
			{
				Item itemToDelete = NodeDAO.getInstance().getItem(userID, itemID);
				//System.err.println(itemToDelete.toString());
				isDelete = NodeDAO.getInstance().deleteItem(userID, itemToDelete);
				
				User userUp = NodeDAO.getInstance().getUser(userID);
				NodeDAO.getInstance().updateUser(userID, userUp);
				
				message = "The Item was sussecfully deleted!";
				request.setAttribute("userMessage", message);
				
				System.err.println("is delete " + isDelete);
				
				forwardPage = JSP_INFORM;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
				
			}
			catch (Exception ex)
			{
				request.setAttribute("userMessage", ex.getMessage());
				forwardPage = JSP_ERROR;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
			}
			finally {
				//forwardPage = JSP_MYHOMEPAGE;
				//System.out.println("we are gooing to " + forwardPage);
				
			}
			
			break;
		}
		case P_ADDITEM:
		{
			System.out.println("in p_additem");
			String message = null;
			Item newItem = new Item();
			
			String itemTitle = request.getParameter("title");
			System.out.println(itemTitle);
			String itemDescription = request.getParameter("description");
			String itemCategory = request.getParameter("category");
			String deadLine = request.getParameter("deadline");
			
			Long userID = (Long) (request.getSession().getAttribute("userID"));
			System.out.println("\n\n++in p_add" + deadLine);
				
				if(itemTitle != null){
					newItem.setTitle(itemTitle);
				}
				if(itemDescription != null){
					newItem.setDescription(itemDescription);
				}
				
				if(itemCategory != null){
					newItem.setItemCategory(ItemCategory.valueOf(itemCategory));
				}
				
				if(deadLine != null){
					System.out.println("dead line from form");
					newItem.setDeadLine((CorrectInput.getDate(deadLine)));
				}
				
				System.out.println("....new Item " + newItem.toString());
				try
				{
				if (NodeDAO.getInstance().createNewItem(userID, newItem)) {
					
					User userUp = NodeDAO.getInstance().getUser(userID);
					NodeDAO.getInstance().updateUser(userID, userUp);
					message = "The Item was sussecfully added!";
					request.setAttribute("userMessage", message);
					forwardPage = JSP_INFORM;
					dispatcher = getServletContext().getRequestDispatcher(forwardPage);
					dispatcher.forward(request, response);
					break;
				}
				else {
					message = "An error occured trying to add new item.";
					request.getServletContext().setAttribute("userMessage", message);
					dispatcher = getServletContext().getRequestDispatcher(JSP_MYHOMEPAGE);
					dispatcher.forward(request, response);
					//forwardPage = JSP_MYHOMEPAGE;
					break;
				}
			}
			catch (Exception ex) {
				
				request.setAttribute("userMessage", ex.getMessage());
				dispatcher = getServletContext().getRequestDispatcher(JSP_ERROR);
				dispatcher.forward(request, response);
				//forwardPage = JSP_ERROR;
				break;
			}
			finally {
				
				//System.out.println(".........additem go to " + forwardPage);
				//dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				//dispatcher.forward(request, response);
			}
		}
		
		case P_UPDATEITEM:
		{
			Long itemID = null;
			Long userID = null;
			try
			{
				itemID = Long.parseLong(request.getParameter("itemID"));
				//Long userID = Long.parseLong((String) request.getAttribute("userID"));
				userID = (Long) (request.getSession().getAttribute("userID"));
				System.out.println("item id = " + itemID + " useId " + userID);
				Item itemToUpdate = NodeDAO.getInstance().getItem(userID, itemID);
				System.out.println("item to update " + itemToUpdate.toString());

				request.getSession().setAttribute("itemID", itemToUpdate.getId());
				request.getSession().setAttribute("itemTitle", itemToUpdate.getTitle());
				request.getSession().setAttribute("itemDescription", itemToUpdate.getDescription());
				request.getSession().setAttribute("category", itemToUpdate.getItemCategory().toString());
				request.getSession().setAttribute("deadline", itemToUpdate.getDeadLine().toString());
				
				request.getSession().setAttribute("isDone", String.valueOf(itemToUpdate.isDone()));
				
				forwardPage = JSP_UPDATEITEM;
				response.sendRedirect(JSP_BASE + forwardPage);
				//dispatcher = getServletContext().getRequestDispatcher(JSP_UPDATEITEM);
				//dispatcher.forward(request, response);
				break;
				
				
			} catch (Exception ex)
			{
				request.setAttribute("userMessage", ex.getMessage());
				forwardPage = JSP_ERROR;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
				break;
			}
			finally {
				//dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				//dispatcher.forward(request, response);
			}
		}
		
		case P_UPDATING:
		{
			Long itemID = null, userID = null;
			String message = null;
			System.out.println("in update");
			try
			{
				itemID = Long.parseLong(request.getParameter("itemID"));
				userID = Long.parseLong(request.getParameter("userID"));
				Item itemToUpdate = NodeDAO.getInstance().getItem(userID, itemID);
				
				String newTitle = (String) request.getParameter("title");
				String newDescription = (String) request.getParameter("description");
				String newCategory = (String) request.getParameter("category");
				String newDeadLine = (String) request.getParameter("deadline");
				String newState = (String) request.getParameter("isDone");
				
				itemToUpdate.setTitle(newTitle);
				itemToUpdate.setDescription(newDescription);
				itemToUpdate.setItemCategory(ItemCategory.valueOf(newCategory));
				itemToUpdate.setDeadLine(CorrectInput.getDate(newDeadLine));
				itemToUpdate.setDone(Boolean.valueOf(newState));
				
				if (NodeDAO.getInstance().updateItem(userID, itemToUpdate))
				{
					message = "The Item was sucsessully update!";
					request.setAttribute("userMessage", message);
					forwardPage = JSP_INFORM;
					dispatcher = getServletContext().getRequestDispatcher(forwardPage);
					dispatcher.forward(request, response);
					break;
				}
				else
				{
					message = "An error occured trying to delete item.";
					request.getServletContext().setAttribute("userMessage", message);
					forwardPage = JSP_MYHOMEPAGE;
					dispatcher = getServletContext().getRequestDispatcher(forwardPage);
					dispatcher.forward(request, response);
					break;
				}
			}
			catch (Exception ex)
			{
				System.out.println("item id in ex = " + itemID);
				request.setAttribute("userMessage", ex.getMessage());
				forwardPage = JSP_ERROR;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
				break;
			}
		}

		default:
		case P_HOME:
		{
			
			if (request.getSession().getAttribute("userID") == null)
			{
				Cookie[] cookies = request.getCookies();
				if (cookies != null)
				{
					for (Cookie cookie : cookies)
					{
						if (cookie.getName().equals("userID"))
						{
							request.getSession().setAttribute("userID", cookie.getValue());
							request.setAttribute("userMessage", "Connected using cookies");
							break;
						}
					}
				}
			}

			Object ob = request.getSession().getAttribute("userID");
			
			Long userID = 0L;
			if (ob != null)
			{
				userID = Long.parseLong(ob.toString());
			}
			if(ob == null){
				forwardPage = JSP_HOME;
				dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
				break;
				
			}
			System.out.println("in home " + userID);
			try {
				User userInBase = NodeDAO.getInstance().getUser(userID);
				
				request.getSession().setAttribute("userID", userInBase.getId());
				request.getSession().setAttribute("userName", userInBase.getUsername());
				
				forwardPage = JSP_MYHOMEPAGE;
				response.sendRedirect(JSP_BASE + JSP_MYHOMEPAGE);
				//dispatcher = getServletContext().getRequestDispatcher(forwardPage);
				//dispatcher.forward(request, response);
				break;
				
				} catch (Exception ex) {
					
					request.setAttribute("userMessage", ex.getMessage());
					forwardPage = JSP_ERROR;
					dispatcher = getServletContext().getRequestDispatcher(forwardPage);
					dispatcher.forward(request, response);
					break;
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
