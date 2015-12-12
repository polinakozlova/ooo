package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.DbException;

public class Action implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("add")){
				try {
					Object source = e.getSource();
					String str = e.paramString().
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
	}

}
