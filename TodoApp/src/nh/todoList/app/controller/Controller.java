package nh.todoList.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import nh.todoList.app.model.Database;
import nh.todoList.app.model.Task;
import nh.todoList.app.view.FormEvent;

public class Controller {
	Database db = new Database();

	public List<Task> getTasks() {
		return db.getTasks();
	}

	public void removeTask(int index) {
		db.removeTask(index);
	}

	public void addTask(FormEvent ev) {
		String description = ev.getDescription();
		boolean erledigt = ev.isErledigt();

		Task task = new Task(description, erledigt);
		if( (db.getTasks().stream().map(e -> e.getDescription()).filter(s -> s.equals(description)).count()
				== 0)&&!"".equals(description)) {
			db.addTask(task);
		}
	}

	public void setTaskDone(FormEvent ev) {
		int index = ev.getSelectedRow();
		db.getTasks().get(index).setErledigt(ev.isErledigt());

	
	}

	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}

	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}
