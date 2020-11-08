package nh.todoList.app.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	
	private List<Task> tasks;
	
	public Database() {
		tasks = new LinkedList<Task>();
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(int index) {
		tasks.remove(index);
	}
	
	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Task[] thetasks = tasks.toArray(new Task[tasks.size()]);
		
		oos.writeObject(thetasks);
		
		oos.close();
	}
	
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Task[] thetasks = (Task[])ois.readObject();
			
			tasks.clear();
			
			tasks.addAll(Arrays.asList(thetasks));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
	}
}
