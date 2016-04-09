package main;

import javax.swing.JFrame;

import model.Model;
import view.Frame;

public class Driver {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Frame frame = new Frame(model);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
