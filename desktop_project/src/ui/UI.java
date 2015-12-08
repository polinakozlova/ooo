package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import domain.DbException;
//import javax.swing.table.DefaultTableModel;
import domain.Product;

/**
 * @author Yannick Crabb�
 */

public class UI {
	private int index = 0;

	public UI() {
	}

	public void makeUI(Controller controller) {
		JFrame frameCashier = new JFrame("Shop");
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frameCashier.add(pane);

		JLabel labelProduct = new JLabel("Product");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		pane.add(labelProduct, c);

		JTextField productCode = new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 3;
		c.gridy = 4;
		pane.add(productCode, c);

		JLabel labelToPay = new JLabel("To pay");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		pane.add(labelToPay, c);

		JTextField toPay = new JTextField(5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 3;
		c.gridy = 5;
		toPay.setEnabled(false);
		pane.add(toPay, c);

		JLabel labelQuantity = new JLabel("Quantity");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 5;
		c.gridy = 4;
		pane.add(labelQuantity, c);

		JTextField quantity = new JTextField(2);
		quantity.setText("1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 6;
		c.gridy = 4;
		pane.add(quantity, c);

		JButton add = new JButton("Add");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 5;
		c.gridy = 5;
		pane.add(add, c);

		String[] columnNames = { "Description", "Quantity", "Unit price", "Total price" };
		Object[][] tableData = new Object[420][4];
		JTable productTable = new JTable(tableData, columnNames);

		JScrollPane tableContainer = new JScrollPane(productTable);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridheight = 50;
		c.gridx = 0;
		c.gridy = 4;
		pane.add(tableContainer, c);

		frameCashier.pack();
		frameCashier.setLocationRelativeTo(null);
		frameCashier.setResizable(false);
		frameCashier.setVisible(true);

		JFrame frameCustomer = new JFrame("To pay");
		JPanel paneCust = new JPanel(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		frameCustomer.add(paneCust);

		JLabel label = new JLabel("To pay");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		paneCust.add(label, cc);

		JTextField price = new JTextField(5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 3;
		c.gridy = 4;
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
						recalculatePrice(toPay, controller);
						recalculatePrice(price, controller);
						updateOverview(tableData, controller);
						productTable.repaint();
					} catch (DbException DbException) {
						JOptionPane.showMessageDialog(null,
								"Product with ID " + productCode.getText() + " doesn't exist.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		productTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (Integer.parseInt(tableData[e.getFirstRow()][1].toString()) == 0) {
					controller.removeProductFromSale(
							controller.getIDByDescription(tableData[e.getFirstRow()][0].toString()));
				} else if (Integer.parseInt(tableData[e.getFirstRow()][1].toString()) < 0) {
					JOptionPane.showMessageDialog(null, "Quantity should be 0 or higher", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					controller.setProductSaleQuantity(
							controller.getIDByDescription(tableData[e.getFirstRow()][0].toString()),
							Integer.parseInt(tableData[e.getFirstRow()][1].toString()));

				}
				recalculatePrice(toPay, controller);
				recalculatePrice(price, controller);
				updateOverview(tableData, controller);
				productTable.repaint();
			}
		});
	}

	private void recalculatePrice(JTextField field, Controller controller) {
		field.setText(controller.getTotalPrice());
	}

	private void updateOverview(Object[][] tableData, Controller controller) {
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