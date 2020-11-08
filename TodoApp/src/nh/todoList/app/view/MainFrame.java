package nh.todoList.app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import nh.todoList.app.controller.Controller;
import nh.todoList.app.model.Task;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements RowSelectedListener,ChangeModeListner {

	/**
	 * 
	 */
	/**
	 * 
	 */
	private FormPanel formPanel;
	private Controller controller;
	private TablePanel tablePanel;
	private boolean nochZuErldeigen=false;
	private File file = new File("tasks.ser");

	public MainFrame() {
		super("Todo List");

		setLayout(new BorderLayout());

		formPanel = new FormPanel(this);
		tablePanel = new TablePanel(this);

		controller = new Controller();

		tablePanel.setData(controller.getTasks());

		tablePanel.setTaskTableListener(new TaskTableListener() {
			public void rowDeleted(int row) {
				controller.removeTask(row);
			}
		});

	
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				if (e.getSelectedRow() == -1) {
					controller.addTask(e);
					tablePanel.refresh();
				}else {
					controller.setTaskDone(e);
					tablePanel.refresh();
				}
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(tablePanel, BorderLayout.CENTER);

		
		
		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			controller.loadFromFile(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				try {
					controller.saveToFile(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
	}

	@Override
	public void setSelectedRow(int row) {
		formPanel.setSelectedRow(row);

	}

	@Override
	public void setSelectedTask(Task task) {
		formPanel.setSelectedTask(task);
		
	}

 
	@Override
	public void changeMode(boolean nochzuEledigenmode) {
		this.nochZuErldeigen=!nochzuEledigenmode;
	if(nochZuErldeigen) {
		tablePanel.setData(controller.getTasks().stream().filter(t->!t.isErledigt()).collect(Collectors.toList()));
	}else {
		tablePanel.setData(controller.getTasks());
	}
	tablePanel.refresh();
		
	}

}
