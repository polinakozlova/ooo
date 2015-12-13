package ui;

import javax.swing.JTextField;

import controller.Controller;
import observer.Observer;
import observer.Observable;

@SuppressWarnings("serial")
public class TextField extends JTextField implements Observer {
	private Controller controller;
	private String text;
	
	public TextField(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void update() {
		this.setText(String.valueOf(controller.getTotalPrice()));	
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
	}
}
