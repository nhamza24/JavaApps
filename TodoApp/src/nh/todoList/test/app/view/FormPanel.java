package nh.todoList.test.app.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import nh.todoList.test.app.model.Task;

public class FormPanel extends JPanel {

	private JLabel descriptionLabel;

	private JTextField descriptionField;
	private JButton addTaskBtn;
	private JButton markDoneBtn;
	private FormListener formListener;
	private int selectedRow;
	private JCheckBox erledigtCheck;
	private ChangeModeListner nochzuErledigen;
	private boolean nochZuErledigenmode = false;
	JScrollPane scrollPane;

	public FormPanel(ChangeModeListner nochzuErledigen) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		descriptionLabel = new JLabel("Description: ");

		descriptionField = new JTextField(10);

		erledigtCheck = new JCheckBox();
		erledigtCheck.setEnabled(false);
		addTaskBtn = new JButton("add Task");
		markDoneBtn = new JButton("Mark as done");

		// Set up mnemomics
		addTaskBtn.setMnemonic(KeyEvent.VK_O);
		this.nochzuErledigen = nochzuErledigen;
		erledigtCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String name = nameField.getText();
				String description = "";
				boolean erledigt = erledigtCheck.isSelected();
				FormEvent ev = new FormEvent(this, description, erledigt, selectedRow);
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}

			}
		});

		addTaskBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String name = nameField.getText();
				String description = descriptionField.getText();

				boolean erledigt = erledigtCheck.isSelected();
				FormEvent ev = new FormEvent(this, description, erledigt);
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		markDoneBtn.addActionListener(
				e -> {
					this.nochZuErledigenmode = !nochZuErledigenmode;
					nochzuErledigen.changeMode(nochZuErledigenmode);
				});

		Border innerBorder = BorderFactory.createTitledBorder("New Task");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(descriptionLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(descriptionField, gc);

//

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Erledigt: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(erledigtCheck, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addTaskBtn, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(markDoneBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.erledigtCheck.setEnabled(selectedRow >= 0);
		this.selectedRow = selectedRow;
	}

	public void setSelectedTask(Task task) {
		if (task != null) {
			descriptionField.setText(task.getDescription());
			erledigtCheck.setSelected(task.isErledigt());
			;
		}

	}
}
