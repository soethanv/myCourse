package view;

import javax.swing.JFrame;

import model.Model;

public class Frame extends JFrame {
	
	Model model;
	
	public Frame(Model model) {
		super("Schedule Helper");
		
		this.model =  model;
		
		
	}

}
