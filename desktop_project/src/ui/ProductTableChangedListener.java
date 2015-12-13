package ui;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class ProductTableChangedListener implements TableModelListener {
	private JTextField toPay;
	private JTextField price;
	private JTable productTable;
	private Object[][] tableData;
	private Controller controller;
	private UI ui;

	public ProductTableChangedListener(JTextField toPay, JTextField price,
			JTable productTable, Object[][] tableData, Controller controller, UI ui) {
		this.toPay = toPay;
		this.price = price;
		this.productTable = productTable;
		this.tableData = tableData;
		this.controller = controller;
		this.ui = ui;
	}

	public void tableChanged(TableModelEvent e) {
		try {
			if (Integer.parseInt(tableData[e.getFirstRow()][1].toString()) == 0) {
				controller.removeProductFromSale(
						controller.getIDByDescription(tableData[e.getFirstRow()][0].toString()));
			} else if (Integer.parseInt(tableData[e.getFirstRow()][1].toString()) < 0) {
				JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
						JOptionPane.ERROR_MESSAGE);
			} else {
				controller.setProductSaleQuantity(
						controller.getIDByDescription(tableData[e.getFirstRow()][0].toString()),
						Integer.parseInt(tableData[e.getFirstRow()][1].toString()));
			}
			ui.showNewPrice(toPay, controller);
			ui.showNewPrice(price, controller);
			controller.updateSaleTable(tableData);
			productTable.repaint();
		} catch (NumberFormatException NumberFormatException) {
			JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}