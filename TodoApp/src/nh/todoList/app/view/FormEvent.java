package nh.todoList.app.view;

import java.util.EventObject;

@SuppressWarnings("serial")
public class FormEvent extends EventObject {

	private String desciption;
	private boolean erledigt;
	private int selectedRow;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String desciption, boolean erledigt, int selectedRow) {
		super(source);
		this.selectedRow = selectedRow;
		this.desciption = desciption;
		this.erledigt = erledigt;
	}
	public FormEvent(Object source, String desciption, boolean erledigt) {
		super(source);
		this.selectedRow = -1;
		this.desciption = desciption;
		this.erledigt = erledigt;
	}

	public boolean isErledigt() {
		return erledigt;
	}

	public String getDescription() {
		return desciption;
	}

	public void setDescription(String desciption) {
		this.desciption = desciption;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

}
