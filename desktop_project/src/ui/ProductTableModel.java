package ui;

import javax.swing.table.DefaultTableModel;

//tableModel = new DefaultTableModel(new Object[]{"column1","column2"},0);
//table.setModel(tableModel);
//btnAdd.addActionListener(new ActionListener(){
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int count = tableModel.getRowCount()+1;
//        tableModel.addRow(new Object[]{txtField1.getText(),txtField1.getText()});
//    }
//});

@SuppressWarnings("serial")
public class ProductTableModel extends DefaultTableModel {
	private String[] columnNames = { "Description", "Quantity", "Unit price", "Total price" };
    private Object[][] tableData;
    
    public ProductTableModel() {
    	this.tableData = new Object[0][4];
    }
    
    public Object[][] getTableData() {
    	return this.tableData;
    }

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tableData.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex][columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
        if (col == 3) {
            return true;
        } else {
            return false;
        }
    }
}
