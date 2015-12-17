package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class ConfirmPromoCodeButtonActionListener implements ActionListener {
	private JTextField promoCode;
	private Controller controller;
	
	public ConfirmPromoCodeButtonActionListener(JTextField promoCode, Controller controller) {
		this.promoCode = promoCode;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String promoCodeString = promoCode.getText();
		double newPrice = controller.getReducedPrice(promoCodeString);
		if (newPrice >= 0)
			controller.setSalePrice(newPrice);
		else 
			JOptionPane.showMessageDialog(null, "You can't lower the price any more!", "Error", JOptionPane.ERROR_MESSAGE);
	}
}