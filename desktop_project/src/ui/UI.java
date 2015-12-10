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
import domain.DbException;
import domain.Product;

/**
 * @author Yannick Crabb�
 */
public class UI {

	public UI() {
	}
	
	

	public void makeUI(Controller controller) {
		JFrame frameCashier = new JFrame("Shop");
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frameCashier.add(pane);

		String[] columnNames = { "Description", "Quantity", "Unit price", "Total price" };
		Object[][] tableData = new Object[420][4];
		JTable productTable = new JTable(tableData, columnNames);
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

		JTextField productCode = new JTextField(10);
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

		JTextField quantity = new JTextField(2);
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

		JTextField toPay = new JTextField(5);
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

		JTextField price = new JTextField(5);
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

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(quantity.getText()) <= 0)
					JOptionPane.showMessageDialog(null, "Quantity should be above 0.", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						controller.addProductToSale(productCode.getText(), quantity.getText());
						showNewPrice(toPay, controller);
						showNewPrice(price, controller);
						updateTable(tableData, controller);
						productTable.repaint();
					} catch (DbException DbException) {
						JOptionPane.showMessageDialog(null,
								"Product with ID " + productCode.getText() + " doesn't exist.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
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
				}
				else {
					//scherm leegmaken voor nieuwe sale
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

	private void showNewPrice(JTextField field, Controller controller) {
		field.setText(String.valueOf(controller.getTotalPrice()));
	}

	private void updateTable(Object[][] tableData, Controller controller) {
		int i = 0;
		for (Product pr : controller.getProducts()) {
			tableData[i][0] = pr.getDescription();
			tableData[i][1] = controller.getProductQuantity(pr);
			tableData[i][2] = pr.getPrice();
			tableData[i][3] = pr.getPrice() * controller.getProductQuantity(pr);
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
}