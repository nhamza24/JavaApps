package nh.todoList.app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import nh.todoList.app.model.Task;

public class TaskTableModel extends AbstractTableModel {
	
	private List<Task> db;
	
	private String[] colNames = { "Description", "erledigt"};
	
	public TaskTableModel() {
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}



	public void setData(List<Task> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Task task = db.get(row);
		
		switch(col) {
		case 0:
			return task.getDescription();
		case 1:
			return task.isErledigt()? "√":"☓";
		}
		
		return null;
	}
	public Task getTaskAtRow(int row) {
		return  db.get(row);
		
		
	}
}
