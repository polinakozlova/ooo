package ui;

import javax.swing.JTextField;
import controller.Controller;
import observer.Observer;

@SuppressWarnings("serial")
public class TextField extends JTextField implements Observer {
	private Controller controller;
	
	public TextField(Controller controller) {
		this.controller = controller;
		controller.registerObserver(this);
	}

	@Override
	public void update() {
		this.setText(String.valueOf(controller.getTotalPrice()));	
	}
}
