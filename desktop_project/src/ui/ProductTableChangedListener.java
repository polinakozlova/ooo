package ui;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class ProductTableChangedListener implements TableModelListener {
	private JTable productTable;
	private Object[][] tableData;
	private Controller controller;

	public ProductTableChangedListener(JTable productTable, Object[][] tableData,
			Controller controller) {
		this.productTable = productTable;
		this.tableData = tableData;
		this.controller = controller;
	}

	public void tableChanged(TableModelEvent e) {
		String productID = controller.getIDByDescription(tableData[e.getFirstRow()][0].toString());
		int newQuantity = Integer.parseInt(tableData[e.getFirstRow()][1].toString());
		try {
			if (newQuantity == 0) {
				controller.removeProductFromSale(productID);
			} else if (newQuantity < 0) {
				JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
						JOptionPane.ERROR_MESSAGE);
			} else {
				controller.setProductSaleQuantity(productID, newQuantity);
			}

			controller.updateSaleTable(tableData);
			productTable.repaint();
		} catch (NumberFormatException NumberFormatException) {
			JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}