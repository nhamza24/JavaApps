package nh.todoList.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import nh.todoList.app.model.Task;

public class TablePanel extends JPanel {
	
	private JTable table;
	private TaskTableModel tableModel;
	private JPopupMenu popup;
	private TaskTableListener taskTableListener;
	private int selectedRow;
	private RowSelectedListener listner;
	public TablePanel(RowSelectedListener rowSelectedListner) {
		
		tableModel = new TaskTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		listner=rowSelectedListner;
		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				selectedRow=row;
				rowSelectedListner.setSelectedRow(row);
				rowSelectedListner.setSelectedTask(tableModel.getTaskAtRow(row));
				table.getSelectionModel().setSelectionInterval(row, row);
				if(e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		    	
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(tableModel.getValueAt(row, 1).equals("☓")   ? Color.CYAN: Color.GREEN);
		        return c;
		    }
		});
		
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				
				if(taskTableListener != null) {
					taskTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Task> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public void setTaskTableListener(TaskTableListener listener) {
		this.taskTableListener = listener;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	 

}
