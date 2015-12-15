package ui;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class ProductTableChangedListener implements TableModelListener {
	private TextField toPay;
	private TextField price;
	private JTable productTable;
	//private Object[][] tableData;
	private Controller controller;

	public ProductTableChangedListener(TextField toPay, TextField price,
			JTable productTable, Controller controller) {
		this.toPay = toPay;
		this.price = price;
		this.productTable = productTable;
		this.controller = controller;
	}

	public void tableChanged(TableModelEvent e) {
		DefaultTableModel model = (DefaultTableModel) productTable.getModel();
		String productDescription = (String) productTable.getModel().getValueAt(e.getFirstRow(), 0);
		String productID = controller.getIDByDescription(productDescription);
		String quantityString =  String.valueOf(productTable.getModel().getValueAt(e.getFirstRow(), 1));
		int quantity = Integer.parseInt(quantityString);
		double productPrice = (double) productTable.getModel().getValueAt(e.getFirstRow(), 2);
		try {
			if (quantity == 0) {
				controller.removeProductFromSale(productID);
				model.removeRow(e.getFirstRow());		
			} else if (quantity < 0) {
				JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
						JOptionPane.ERROR_MESSAGE);
			} else {
				controller.setProductSaleQuantity(productID, quantity);
			}
			toPay.update();
			price.update();
			//controller.updateSaleTable(tableData);
			productTable.repaint();
		} catch (NumberFormatException NumberFormatException) {
			JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}