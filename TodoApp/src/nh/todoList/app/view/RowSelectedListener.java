package nh.todoList.app.view;

import nh.todoList.app.model.Task;

public interface RowSelectedListener {
	void setSelectedRow(int row);
    void setSelectedTask(Task task);
}
