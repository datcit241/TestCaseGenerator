package table_model;

import entity.TestCase;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class TableModel extends AbstractTableModel {
    private String[] fieldNames = {"#", "Input 1", "Input 2", "Input 3", "Expected Output"};
    private List<TestCase> dataList = new ArrayList<>();
    private List<Object[]> rows = new ArrayList<>();

    public TableModel() {}

    public TableModel(List<TestCase> testcases, String[] fieldNames) {
        this(testcases);
        this.fieldNames = fieldNames;
    }

    public TableModel(List<TestCase> testcases) {
        this.dataList = testcases;

        testcases.forEach(testcase -> {
            this.rows.add(testcase.getValues().toArray());
        });
    }

    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    @Override
    public int getColumnCount() {
        return this.fieldNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rows.get(rowIndex)[columnIndex];
    }

    public Object getObjectAtRow(int rowIndex) {
        return this.dataList.get(rowIndex);
    }

    public String getColumnName(int columnIndex) {
        return this.fieldNames[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
//        return rows.size() == 0 ? null : this.rows.get(0)[columnIndex].getClass();
        return Object.class;
    }
}
