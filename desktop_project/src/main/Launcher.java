package main;

import controller.Controller;
import ui.UI;

/**
 * @author Yannick Crabb�
 */
public class Launcher {
	public static void main(String[] args) {
		Controller controller = new Controller();
		UI ui = new UI();
		ui.makeUI(controller);	
	}

}
