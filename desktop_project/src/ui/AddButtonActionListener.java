package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controller.Controller;
import domain.DbException;

/**
 * @author Yannick Crabbe
 */
public class AddButtonActionListener implements ActionListener {
	private JTextField productCode;
	private JTextField quantity;
	private JTextField toPay;
	private JTextField price;
	private JTable productTable;
	private Object[][] tableData;
	private Controller controller;
	private UI ui;

	public AddButtonActionListener(JTextField productCode, JTextField quantity, JTextField toPay, JTextField price,
			JTable productTable, Object[][] tableData, Controller controller, UI ui) {
		this.productCode = productCode;
		this.quantity = quantity;
		this.toPay = toPay;
		this.price = price;
		this.productTable = productTable;
		this.tableData = tableData;
		this.controller = controller;
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (Integer.parseInt(quantity.getText()) <= 0)
			JOptionPane.showMessageDialog(null, "Quantity should be above 0.", "Error",
					JOptionPane.ERROR_MESSAGE);
		else {
			try {
				controller.addProductToSale(productCode.getText(), quantity.getText());
				ui.showNewPrice(toPay, controller);
				ui.showNewPrice(price, controller);
				controller.updateSaleTable(tableData);
				productTable.repaint();
			} catch (DbException DbException) {
				JOptionPane.showMessageDialog(null,
						"Product with ID " + productCode.getText() + " doesn't exist.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}