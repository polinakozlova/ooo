//package ui;
//
//import observer.Observer;
//import javax.swing.JTable;
//
//
//import controller.Controller;
//
//@SuppressWarnings("serial")
//public class ProductTable extends JTable implements Observer {
//	private String[] columnNames;
//	//private Object[][] tableData = new Object[420][4];
//	private Controller controller;
//	
//	public ProductTable() {
//		JTable bla = new JTable(dkd, dhhd);
//		ProductTableModel productTableModel = new ProductTableModel();
//		this.setModel(productTableModel);
//		this.controller = controller;
//		controller.registerObserver(this);
//	}
//	
//	public Object[][] getTableData() {	
//		
//	}
//
//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setText(String message) {
//		// TODO Auto-generated method stub
//		
//	}
//}
