package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

import controller.Controller;

/**
 * @author Yannick Crabbe, Polina Kozlova
 */
public class UI {

	public void makeUI(Controller controller) {
		JFrame frameCashier = new JFrame("Shop");
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frameCashier.add(pane);

		String[] columnNames = { "Description", "Quantity", "Unit price", "Total price" };
		Object[][] tableData = new Object[10][4];
		JTable productTable = new JTable(tableData, columnNames);
		JScrollPane tableContainer = new JScrollPane(productTable);
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
		frameCashier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCashier.setVisible(true);

		JFrame frameCustomer = new JFrame("To pay");
		JPanel paneCust = new JPanel();
		frameCustomer.add(paneCust);

		JLabel label = new JLabel("To pay");
		paneCust.add(label);

		TextField price = new TextField(controller);
		price.setEnabled(false);
		paneCust.add(price);

		frameCustomer.pack();
		frameCustomer.setLocationRelativeTo(null);
		frameCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCustomer.setVisible(true);

		add.addActionListener(new AddButtonActionListener(productCode, quantity, productTable, tableData, controller) {
		});

		confirmPromo.addActionListener(new ConfirmPromoCodeButtonActionListener(promoCode, controller) {
		});

		confirmSale.addActionListener(new ConfirmSaleButtonActionListener(productCode, quantity, toPay, price,
				promoCode, productTable, tableData, controller) {
		});

		productTable.getModel()
				.addTableModelListener(new ProductTableChangedListener(productTable, tableData, controller));
	}
}