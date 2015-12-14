package ui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ProductTableModel extends DefaultTableModel {
	private String[] columnNames;
    private Object[][] tableData;
    
    public ProductTableModel(String[] columnNames, Object[][]tableData) {
    	this.tableData = tableData;
    	this.columnNames = columnNames;
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
