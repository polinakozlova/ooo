package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import controller.Controller;
//import domain.DbException;
import domain.Product;

/**
 * @author Yannick Crabb�
 */
public class UI {
	private JTextField quantity;
	private JTextField productCode;
	private JTextField price;
	private JTextField toPay;
	private String[] columnNames;
	private Object[][] tableData;
	private JTable productTable;
	private Controller controller;

	public UI() {
		this.quantity = new JTextField(2);
		this.productCode = new JTextField(10);
		this.price = new JTextField(5);
		this.toPay = new JTextField(5);
		this.columnNames = new String[] { "Description", "Quantity", "Unit price", "Total price" };
		this.tableData = new Object[420][4];
		this.productTable = new JTable(tableData, columnNames);
		this.controller = new Controller();
	}

	public void makeUI() {
		JFrame frameCashier = new JFrame("Shop");
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frameCashier.add(pane);

		JScrollPane tableContainer = new JScrollPane(productTable);
		// c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(tableContainer, c);

		JLabel labelProduct = new JLabel("Product");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		pane.add(labelProduct, c);

		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 2;
		c.gridy = 0;
		pane.add(productCode, c);

		JLabel labelQuantity = new JLabel("Quantity");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 3;
		c.gridy = 0;
		pane.add(labelQuantity, c);

		quantity.setText("1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 4;
		c.gridy = 0;
		pane.add(quantity, c);

		JButton add = new JButton("Add");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 0, 5);
		c.gridx = 5;
		c.gridy = 0;
		pane.add(add, c);

		JLabel labelToPay = new JLabel("To pay");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(labelToPay, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		toPay.setEnabled(false);
		pane.add(toPay, c);

		JLabel labelPromoCode = new JLabel("Promo code");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(labelPromoCode, c);

		JTextField promoCode = new JTextField(5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		toPay.setEnabled(false);
		pane.add(promoCode, c);

		JButton confirmPromo = new JButton("Confirm");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 5, 0, 5);
		c.gridx = 3;
		c.gridy = 2;
		pane.add(confirmPromo, c);

		JButton confirmSale = new JButton("Confirm sale");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 5;
		pane.add(confirmSale, c);

		frameCashier.pack();
		frameCashier.setLocationRelativeTo(null);
		frameCashier.setResizable(false);
		frameCashier.setVisible(true);

		JFrame frameCustomer = new JFrame("To pay");
		JPanel paneCust = new JPanel(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		frameCustomer.add(paneCust);

		JLabel label = new JLabel("To pay");
		cc.fill = GridBagConstraints.HORIZONTAL;
		cc.gridx = 0;
		cc.gridy = 0;
		paneCust.add(label, cc);

		cc.fill = GridBagConstraints.HORIZONTAL;
		cc.insets = new Insets(0, 5, 0, 0);
		cc.gridx = 1;
		cc.gridy = 0;
		toPay.setEnabled(false);
		paneCust.add(price, cc);

		frameCustomer.pack();
		frameCustomer.setLocationRelativeTo(null);
		frameCustomer.setResizable(false);
		frameCustomer.setVisible(true);

		add.addActionListener(
				new AddButtonActionListener(productCode, quantity, toPay, price, productTable, tableData, controller, this) {
				});

		confirmPromo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				if (controller.checkValidPromoCode(promoCode.getText())) {
					controller.adjustPriceAfterPromo();
					// recalculate price
					// TODO
				}
			}
		});

		confirmSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				double amountPaid = Double.parseDouble(JOptionPane.showInputDialog(pane, "Amount paid by customer: "));
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
		});

		productTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
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
					showNewPrice(toPay, controller);
					showNewPrice(price, controller);
					updateTable(tableData, controller);
					productTable.repaint();
				} catch (NumberFormatException NumberFormatException) {
					JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher.", "Invalid input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void showNewPrice(JTextField field, Controller controller) {
		field.setText(String.valueOf(controller.getTotalPrice()));
	}

	public void updateTable(Object[][] tableData, Controller controller) {
		int i = 0;
			for (Product pr : controller.getCurrentSale()) {
			tableData[i][0] = controller.getProductDescription(pr);
			tableData[i][1] = controller.getProductQuantity(pr);
			tableData[i][2] = controller.getProductPrice(pr);
			tableData[i][3] = controller.getProductPrice(pr) * controller.getProductQuantity(pr);
			i++;
		}
		while (i < 420) {
			tableData[i][0] = "";
			tableData[i][1] = "";
			tableData[i][2] = "";
			tableData[i][3] = "";
			i++;
		}
	}

	public JTextField getQuantity() {
		return this.quantity;
	}

	public JTextField getProductCode() {
		return this.productCode;
	}

	public JTextField getPrice() {
		return this.price;
	}

	public JTextField getToPay() {
		return this.toPay;
	}

	public JTable getProductTable() {
		return this.productTable;
	}

	public Object[][] getTableData() {
		return this.tableData;
	}

	public Controller getController() {
		return controller;
	}
}