
package sig.controller;
    
import sig.model.invoiceHeader;
import sig.model.invoiceLine;
import sig.model.LinesTable;
import sig.view.invoiceFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Listeners implements ListSelectionListener {

    
   private  invoiceFrame _TheInvoiceFrame;

    public Listeners(invoiceFrame GUI) {
        this._TheInvoiceFrame = GUI;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int Theidx = _TheInvoiceFrame.getheaderTable().getSelectedRow();
        if (Theidx != -1) {
            invoiceHeader InvoiceHeader = _TheInvoiceFrame.getInvoicesArray().get(Theidx);
            ArrayList<invoiceLine> lines = InvoiceHeader.getLines();
            LinesTable DrawTable = new LinesTable(lines);
            _TheInvoiceFrame.setLinesArray(lines);
            _TheInvoiceFrame.getlineTable().setModel(DrawTable);
            _TheInvoiceFrame.getCustNameLbl().setText(InvoiceHeader.getCustomer());
            _TheInvoiceFrame.getInvNumLbl().setText("" + InvoiceHeader.getNum());
            _TheInvoiceFrame.getInvTotalIbl().setText("" + InvoiceHeader.getItemTotal());
            _TheInvoiceFrame.getDateLbl().setText(invoiceFrame.dateFormat.format(InvoiceHeader.getDate()));
        }
    }

    


}

    

