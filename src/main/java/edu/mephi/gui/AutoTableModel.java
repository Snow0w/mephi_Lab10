package edu.mephi.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AutoTableModel extends AbstractTableModel {
    private final int colCnt = 4;
    private ArrayList<String[]> dataArrayList;

    public AutoTableModel() {
        dataArrayList = new ArrayList<String[]>();
        // for (int i = 0; i < dataArrayList.size(); i++) {
        //     dataArrayList.add(new String[colCnt]);
        // }
    }

	@Override
	public int getRowCount() {
		return dataArrayList.size();
	}

	@Override
	public int getColumnCount() {
		return colCnt;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
        return dataArrayList.get(rowIndex)[columnIndex];
	}

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Марка";
            case 1: return "Модель";
            case 2: return "Год";
            default: return "Цена";
        }
    }

    public void addRow(String[] row) {
        dataArrayList.add(row);
    }

}

