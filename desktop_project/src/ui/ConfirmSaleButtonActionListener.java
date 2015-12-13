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
	private JTextField toPay;
	private JTextField price;
	private JTable productTable;
	private Object[][] tableData;
	private Controller controller;

	public ConfirmSaleButtonActionListener(JTextField productCode, JTextField quantity, JTextField toPay, JTextField price,
			JTable productTable, Object[][] tableData, Controller controller) {
		this.productCode = productCode;
		this.quantity = quantity;
		this.toPay = toPay;
		this.price = price;
		this.productTable = productTable;
		this.tableData = tableData;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			double amountPaid = Double.parseDouble(JOptionPane.showInputDialog(null, "Amount paid by customer: "));
			if (amountPaid <= Double.parseDouble(price.getText())) {
				JOptionPane.showMessageDialog(null,
						"The amount paid should be " + Double.parseDouble(price.getText()) + " or higher.",
						"Invalid input", JOptionPane.ERROR_MESSAGE);
			} else {
				// scherm leegmaken voor nieuwe sale
				for (int i = 0; i < 420; i++) {
					tableData[i][0] = "";
					tableData[i][1] = "";
					tableData[i][2] = "";
					tableData[i][3] = "";
				}
				productTable.repaint();
				controller.emptyCurrentSale();
				price.setText("0.0");
				toPay.setText("0.0");
				quantity.setText("1");
				productCode.setText("");
			}
		}
	}