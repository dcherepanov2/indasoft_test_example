package GUI;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3551233411661574301L;
	private int columnCount = 5;
	private ArrayList<String[]> data = new ArrayList<>() ;
	
	public EmployeeTableModel() {}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnCount;
	}
	

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String[] rows = data.get(rowIndex);
		return rows[columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
			case 0: return "id";
			case 1: return "departament_id";
			case 2: return "chief_id";
			case 3: return "name";
			case 4: return "salary";		
		}
		return "";
	}
	
	public void addDate(String[] row) {
		String[] rowTable = new String[getColumnCount()];
		rowTable = row;
		data.add(rowTable);
	}
}
