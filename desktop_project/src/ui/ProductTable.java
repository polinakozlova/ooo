package ui;

import observer.Observer;
import javax.swing.JTable;


import controller.Controller;

@SuppressWarnings("serial")
public class ProductTable extends JTable implements Observer {
	private String[] columnNames = { "Description", "Quantity", "Unit price", "Total price" };
	private Object[][] tableData = new Object[420][4];
	private Controller controller;
	
	public ProductTable(Controller controller) {
		ProductTableModel productTableModel = new ProductTableModel(columnNames, tableData);
		this.setModel(productTableModel);
		this.controller = controller;
		controller.registerObserver(this);
	}
	
	public Object[][] getTableData() {
		
		return tableData;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setText(String message) {
		// TODO Auto-generated method stub
		
	}
}
