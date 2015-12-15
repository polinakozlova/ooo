package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import domain.DbException;

/**
 * @author Yannick Crabbe
 */
public class AddButtonActionListener implements ActionListener {
	private JTextField productCode;
	private JTextField quantity;
	private JTable productTable;
	private Controller controller;

	public AddButtonActionListener(JTextField productCode, JTextField quantity, JTable productTable,
			Controller controller) {
		this.productCode = productCode;
		this.quantity = quantity;
		this.productTable = productTable;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) productTable.getModel();
		int quantityInt = Integer.parseInt(quantity.getText());
		String productID = productCode.getText();

		if (quantityInt <= 0)
			JOptionPane.showMessageDialog(null, "Quantity should be above 0.", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			try {
				controller.addProductToSale(productID, quantity.getText());
				model.addRow(new Object[] { controller.getProductDescription(productID), quantity.getText(),
						controller.getProductPrice(productID), quantityInt * controller.getProductPrice(productID)});
				// controller.updateSaleTable(tableData);
				// productTable.repaint();
			} catch (DbException DbException) {
				JOptionPane.showMessageDialog(null, "Product with ID " + productCode.getText() + " doesn't exist.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}