package view;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.IconLibrary;

public class HomePane extends JPanel {
	
	JButton listButton, graphButton;
	
	JLabel listLabel, graphLabel;
	
	public HomePane() {
		super(new GridLayout(2,2));		
		this.setBorder(new EmptyBorder(new Insets(90, 90, 90, 90)));
		
		listButton = new JButton("View as List");
		graphButton = new JButton("View as Graph");
		
		listLabel = new JLabel();
		listLabel.setIcon(IconLibrary.LIST_ICON);
		listLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		graphLabel = new JLabel();
		graphLabel.setIcon(IconLibrary.GRAPH_ICON);
		graphLabel.setHorizontalAlignment(SwingConstants.CENTER);

		
		this.add(listLabel);
		this.add(graphLabel);
		this.add(listButton);
		this.add(graphButton);
		

	}

}
