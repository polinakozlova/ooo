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
	
	public ConfirmPromoCodeButtonActionListener(JTextField promoCode, Controller controller) {
		this.promoCode = promoCode;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		if (controller.checkValidPromoCode(promoCode.getText())) {
			controller.adjustPriceAfterPromo();
			// recalculate price
			// TODO
		}
		*/
		
	}

}
