package sig.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InvoiceHeaderDialog extends JDialog {
    private final JTextField _NameField;
    private final JTextField _THeDateField;
    private final JLabel _customerNameLabel;
    private final JLabel _DateLabel;
    private final JButton _SaveButton;
    private final JButton _CancelButton;

    public InvoiceHeaderDialog(invoiceFrame frame) {
        _customerNameLabel = new JLabel("Customer Name:");
        _NameField = new JTextField(20);
        _DateLabel = new JLabel(" Date:");
        _THeDateField = new JTextField(20);
        _SaveButton = new JButton("Save");
        _CancelButton = new JButton("Cancel");
        
        _SaveButton.setActionCommand("newInvoiceSave");
        _CancelButton.setActionCommand("newInvoiceCancel");
        
        _SaveButton.addActionListener((ActionListener) frame.getActionHandler());
        _CancelButton.addActionListener((ActionListener) frame.getActionHandler());
        setLayout(new GridLayout(3, 2));
        
        add(_DateLabel);
        add(_THeDateField);
        add(_customerNameLabel);
        add(_NameField);
        add(_SaveButton);
        add(_CancelButton);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return _NameField;
    }

    public JTextField getDateField() {
        return _THeDateField;
    }
    
}
