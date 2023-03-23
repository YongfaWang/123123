package main.java.reader.util;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableMode extends AbstractTableModel{
	
	Vector data=null;
	Vector columnName=null;
	
	public TableMode(Vector data,Vector columnName){
		this.data=data;
		this.columnName=columnName;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector)data.get(arg0)).get(arg1);
	}
	
	public String getColumnName(int column){
		return (String)columnName.get(column);
	}

}
