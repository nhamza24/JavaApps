package nh.todoList.test.app.model;

import java.io.Serializable;

public class Task implements Serializable {
	
	private static final long serialVersionUID = -8219218627533074108L;

	private static int count = 0;
	private String description;
	private boolean erledigt;
	private int id;

	public Task(String description) {
		this.description = description;
		this.id = count;
		count++;
	}
	public Task(String description, boolean erledigt) {
		this(description);
		this.erledigt=erledigt;
	}

	public boolean isErledigt() {
		return erledigt;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setErledigt(boolean erledigt) {
		this.erledigt = erledigt;
	}

}
