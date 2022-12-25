package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LinesTable extends AbstractTableModel {

    private ArrayList<invoiceLine> _lines;
    private String[] cols = {"Item Name", "Unit Price", "Count", "Line Total"};

    public LinesTable(ArrayList<invoiceLine> linesArray) {
        this._lines = linesArray;
    }

    @Override
    public int getRowCount() {
        return _lines == null ? 0 : _lines.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (_lines == null) {
            return "";
        } else {
            invoiceLine Alllines = _lines.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return Alllines.getItem();
                case 1:
                    return Alllines.getPrice();
                case 2:
                    return Alllines.getCount();
                case 3:
                    return Alllines.getLineTotal();
                default:
                    return "";
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

}


