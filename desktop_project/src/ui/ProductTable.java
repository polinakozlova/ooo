package ui;

import observer.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

@SuppressWarnings("serial")
public class ProductTable extends JTable implements Observer {
	private String[] columnNames = { "Description", "Quantity", "Unit price", "Total price" };
	private Controller controller;
	
	public ProductTable(Controller controller) {
		this.controller = controller;
		this.setModel(new DefaultTableModel());
		for (String n : columnNames) {
			((DefaultTableModel) getModel()).addColumn(n);
		}	
		controller.registerObserver(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setText(String message) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
        if (col == 1) {
            return true;
        } else {
            return false;
        }
    }
}