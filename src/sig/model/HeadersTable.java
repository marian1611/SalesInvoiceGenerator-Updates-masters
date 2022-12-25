
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import sig.view.invoiceFrame;


public class HeadersTable extends AbstractTableModel {

    private ArrayList<invoiceHeader> _invoices;
    private String[] cols = {"Invoice Num", "Invoice Date", "Customer Name", "Invoice Total"};
    
    public HeadersTable(ArrayList<invoiceHeader> invoicesArray) {
        this._invoices = invoicesArray;
    }

    @Override
    public int getRowCount() {
        return _invoices.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        invoiceHeader _v = _invoices.get(rowIndex);
        switch (columnIndex) {
        case 0: 
            return
                    _v.getNum();
        case 1: 
            return 
                    invoiceFrame.dateFormat.format(_v.getDate());
        case 2: 
            return 
                    _v.getCustomer();
        case 3:
            return
                    _v.getItemTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
}
