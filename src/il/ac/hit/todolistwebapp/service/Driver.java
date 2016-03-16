package il.ac.hit.todolistwebapp.service;


import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.helper.CorrectInput;
import il.ac.hit.todolistwebapp.helper.PasswordHelper;

public class Driver {

	public static void main(String[] args) throws ToDoListPlatformException {
/*
		PasswordHelper p = new PasswordHelper();
		User user = new User();
		user.setUsername("Sergio");
		user.setPassword(p.encode("123"));
		user.setEmail("my@email.com");
		Item item0 = new Item();
		Item item1 = new Item();
		Item item2 = new Item();
		item0.setTitle("to call to the doctor");
		item0.setDescription("for my health");
		item0.setItemCategory(ItemCategory.HEALTH);
		item1.setTitle("to call to the doctor");
		item1.setDescription("for my health");
		item1.setItemCategory(ItemCategory.HEALTH);
		item2.setTitle("My Birthday");
		item2.setItemCategory(ItemCategory.BIRTHDAY);
		
		Set<Item> list = new HashSet<Item>();
		list.add(item0);
		user.setItemsList(list);
		
		
		try {
			//NodeDAO.getInstance().createNewUser(user);
			NodeDAO.getInstance().createNewUser(user);
			//System.out.println("++++" + us3.toString() + "\n*************");
		} catch (ToDoListPlatformException e) {
			//System.out.println("cann't add node");
			e.printStackTrace();
		}
		
		//Long id = NodeDAO.getInstance().connectUser("Sergio", "1234");
		
		
		
		//dao.createNewItem(1L, it3);
		//NodeDAO.getInstance().updateItem(1L, it3);
		
		//System.out.println(items.toString());
		
		
		//dao.deleteItem(1L, item0);
		/*
		String username = "Sergio_3";
		String password = "123qwe";
		String corEmail = "email@email.com";
		String notcorEmail = "email@ua.";
		PasswordHelper ph = new PasswordHelper();
		
		String newUsername = CorrectInput.universalInput(username);
		System.out.println(newUsername.toString());
		
		String newPasword = CorrectInput.universalInput(password);
		System.out.println(CorrectInput.isCorrectInput(newUsername));
		
		System.out.println(CorrectInput.isCorrectEmail(corEmail));
		CorrectInput.ErrorMessageInputEmpty("password");
		*/
		PasswordHelper ph = new PasswordHelper();
		String pasw = "123456";
		String codeppassw = ph.encode(pasw);
		System.out.println(codeppassw);
		System.out.println(CorrectInput.universalInput(codeppassw));
		System.out.println(codeppassw.equals(CorrectInput.universalInput(codeppassw)));
	
		
		/*
		Item item0 = new Item();
		String d = "02/15/2016 9:42 PM";
		item0.setDeadLine(CorrectInput.getDate(d));
		System.out.println(item0.getDeadLine());
		System.out.println(CorrectInput.strDate(item0.getDeadLine()));
		*/
	}
	
}
