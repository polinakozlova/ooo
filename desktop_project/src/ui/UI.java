package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

/**
 * @author Yannick Crabbe
 */
public class UI {

	public void makeUI(Controller controller) {
		JFrame frameCashier = new JFrame("Shop");
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frameCashier.add(pane);

		ProductTable productTable = new ProductTable(controller);
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

		TextField toPay = new TextField(controller);
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

		TextField price = new TextField(controller);
		cc.fill = GridBagConstraints.HORIZONTAL;
		cc.insets = new Insets(0, 5, 0, 0);
		cc.gridx = 1;
		cc.gridy = 0;
		price.setEnabled(false);
		paneCust.add(price, cc);

		frameCustomer.setSize(200, 200);
		frameCustomer.pack();
		frameCustomer.setLocationRelativeTo(null);
		// frameCustomer.setResizable(false);
		frameCustomer.setVisible(true);

		add.addActionListener(new AddButtonActionListener(productCode, quantity, productTable, controller) {
		});

		confirmPromo.addActionListener(new ConfirmPromoCodeButtonActionListener(promoCode, controller) {
		});

		confirmSale.addActionListener(
				new ConfirmSaleButtonActionListener(productCode, quantity, toPay, price, productTable, controller) {
				});

		productTable.getModel()
				.addTableModelListener(new ProductTableChangedListener(toPay, price, productTable, controller));
	}
}