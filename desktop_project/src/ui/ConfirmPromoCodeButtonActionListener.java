package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class ConfirmPromoCodeButtonActionListener implements ActionListener {
	private JTextField promoCode;
	private Controller controller;
	private TextField toPay;
	private TextField price;
	
	public ConfirmPromoCodeButtonActionListener(JTextField promoCode, Controller controller, TextField  toPay, TextField price) {
		this.promoCode = promoCode;
		this.controller = controller;
		this.toPay = toPay;
		this.price = price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String promoCodeString = promoCode.getText();
		double newPrice = controller.getReducedPrice(promoCodeString);
		price.setText(String.valueOf(newPrice));
		toPay.setText(String.valueOf(newPrice));
	}
}