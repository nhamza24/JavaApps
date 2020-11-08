package nh.todoList.test.app;
import javax.swing.SwingUtilities;

import nh.todoList.test.app.view.MainFrame;


public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			
			public void run(){
				new MainFrame();
				}
		});
		
		}

}
