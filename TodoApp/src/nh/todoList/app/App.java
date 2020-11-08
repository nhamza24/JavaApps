package nh.todoList.app;
import javax.swing.SwingUtilities;

import nh.todoList.app.view.MainFrame;


public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			
			public void run(){
				new MainFrame();
				}
		});
		
		}

}
