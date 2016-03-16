package il.ac.hit.todolistwebapp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import il.ac.hit.todolistwebapp.dao.NodeDAO;
import il.ac.hit.todolistwebapp.exception.ToDoListPlatformException;
import il.ac.hit.todolistwebapp.helper.CorrectInput;
import il.ac.hit.todolistwebapp.models.Item;
import il.ac.hit.todolistwebapp.models.ItemCategory;

/**
 * Class for display user's Item List
 * @author Lena
 *
 */
public class ListOutput extends SimpleTagSupport {
	
	private long userID;
	
	
	public void setUserID(long userID) {
		this.userID = userID;
	}

	StringBuilder output = new StringBuilder();
	
	public void generateOutput(Long userId){
				
		List<Item> items = null;
		
		try {
			items = NodeDAO.getInstance().getAllItems(userId);
			
		} catch (ToDoListPlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		output.append("<table class = \"table table-condensed\">");
		output.append("<h3>To Do List</h3>");
		output.append("<thead><tr><h2><th>Delete</th><th>Title</th><th>Description</th>");
		output.append("<th>ItemCategory</th><th>DeadLine</th><th>Is Done</th><th>Update</th>");
		output.append("</h2></tr></thead>");

		if (items != null)
		{
			for (Item item : items)
			{
				Long itemID = item.getId();
				String title = item.getTitle(), description = item.getDescription();
				boolean state = item.isDone();
				ItemCategory category = item.getItemCategory();
				String deadLine = CorrectInput.strDate(item.getDeadLine());

				output.append("<tbody<tr>");
				output.append("<td><form action='/ToDoListWebApp/controller/deleteitem' method='get'>");
				output.append("<button class=\"btn btn-lg btn-default\" type='submit'><span class='glyphicon glyphicon-remove' style='color:red;'></span> Delete</button>");
				output.append("<input name=\"itemID\" type=\"hidden\" value=" + String.valueOf(itemID) + " /></form></td>");
				output.append("<td>" + title + "</td>");
				output.append("<td>" + description + "</td>");
				output.append("<td>" + String.valueOf(category) + "</td>");
				output.append("<td>" + deadLine + "</td>");
				//output.append("<td>" + String.valueOf(dateStr[1]) + "</td>");
				String str = String.valueOf(state).equals("true") ? "done" : "in procces";
				output.append("<td> " + str + "</td>");
				output.append("<td><form action='/ToDoListWebApp/controller/updateitem' method='get'> ");
				output.append("<button class=\"btn btn-lg btn-default\" type=\"submit\"><span class=\"glyphicon glyphicon-edit\" style=\"color:blue;\"></span> Edit</button>");
				output.append("<input name=\"itemID\" type=\"hidden\" ");
				output.append("value=" + String.valueOf(itemID) + " /></form></td>");
				//output.append("<session.setAttribute('itemID'," + String.valueOf(itemID) + ");/></form></td>");
				output.append("</tbody>");
			}
			output.append("</table>");
		}
		else
			output.append("you don not have item's to do");
	}
	
	public void doTag() throws IOException, JspException{
		
		if(userID != 0){
			generateOutput(userID);
			JspWriter out = getJspContext().getOut();
			out.print(output.toString());
		}
	}

}
