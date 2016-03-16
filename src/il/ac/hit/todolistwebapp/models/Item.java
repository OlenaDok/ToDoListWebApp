package il.ac.hit.todolistwebapp.models;

import java.util.Date;

import org.hibernate.annotations.Proxy;

/**
 * Entity class Item
 */
@Proxy(lazy=false)
public class Item {
	
	private Long id;
	private String title;
	private String description;
	private ItemCategory itemCategory;
	private Date deadLine;
	private boolean isDone;
	
	public Item() {
	}
/*
	public Item(String title, String description, ItemCategory itemCategory) {
		this.title = title;
		this.description = description;
		this.itemCategory = itemCategory;
		this.isDone = false;
	}
	*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", description=" + description + ", itemCategory=" + itemCategory
				+ ", isDone=" + isDone + "]";
	}
}
