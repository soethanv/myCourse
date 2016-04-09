package model;

import main.Panes;

public class Model {
	
	int currentPane;
	int prevPane;
	
	
	public Model() {
		currentPane = Panes.HOME_PANE;
		prevPane = Panes.HOME_PANE;
		
	}
	
	public void switchPanel(int i) {
		prevPane = currentPane;
		currentPane = i;
	}

}
