package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class ConfirmSaleButtonActionListener implements ActionListener {
	private JTextField productCode;
	private JTextField quantity;
	private JTextField price;
	private JTextField promoCode;
	private JTable productTable;
	private Object[][] tableData;
	private Controller controller;

	public ConfirmSaleButtonActionListener(JTextField productCode, JTextField quantity, JTextField price,
			JTextField promoCode, JTable productTable, Object[][] tableData, Controller controller) {
		this.productCode = productCode;
		this.quantity = quantity;
		this.price = price;
		this.promoCode = promoCode;
		this.productTable = productTable;
		this.tableData = tableData;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double salePrice = Double.parseDouble(price.getText());
		salePrice = salePrice - controller.getDayDiscount();
		controller.setSalePrice(salePrice);
		double amountPaid = Double.parseDouble(JOptionPane.showInputDialog(null, "Amount paid by customer: "));
		double change = amountPaid - salePrice;
		if (amountPaid < Double.parseDouble(price.getText())) {
			JOptionPane.showMessageDialog(null,
					"The amount paid should be " + Double.parseDouble(price.getText()) + " or higher.", "Invalid input",
					JOptionPane.ERROR_MESSAGE);
		} else {
			for (int i = 0; i < 10; i++) {
				tableData[i][0] = "";
				tableData[i][1] = "";
				tableData[i][2] = "";
				tableData[i][3] = "";
			}
			productTable.repaint();
			controller.emptyCurrentSale();
			promoCode.setText("");
			quantity.setText("1");
			productCode.setText("");
			JOptionPane.showMessageDialog(null, "Price: " + salePrice + "\n" + "Amount paid by customer: " + amountPaid
					+ "\n" + "Change: " + change, null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
}