package nh.todoList.test.app.view;

import nh.todoList.test.app.model.Task;

public interface RowSelectedListener {
	void setSelectedRow(int row);
    void setSelectedTask(Task task);
}
