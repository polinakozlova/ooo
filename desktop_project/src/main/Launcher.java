package main;
import controller.Controller;
import ui.UI;

/**
 * @author Yannick Crabb�
 */
public class Launcher {
	public static void main(String[] args) {
		new UI().makeUI(new Controller());	
	}
}
