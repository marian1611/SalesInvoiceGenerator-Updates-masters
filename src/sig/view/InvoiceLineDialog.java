
package sig.view;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

    public class InvoiceLineDialog extends JDialog{
    private final JTextField _itemNameTextField;
    private final JTextField _itemCountTextField;
    private final JTextField _itemPriceTextField;
    private final JLabel _itemNameLabel;
    private final JLabel _itemCountLabel;
    private final JLabel _itemPriceLabel;
    private JButton _SaveChangesbutton;
    private JButton _Cancelbutton;
    
    public InvoiceLineDialog(invoiceFrame frame) {
        _itemNameTextField = new JTextField(20);
        _itemNameLabel = new JLabel("Item Name");
        
        _itemCountTextField = new JTextField(20);
        _itemCountLabel = new JLabel("Item Count");
        
        _itemPriceTextField = new JTextField(20);
        _itemPriceLabel = new JLabel("Item Price");
        
        _SaveChangesbutton = new JButton("Save");
         _Cancelbutton = new JButton("Cancel");
        
        _SaveChangesbutton.setActionCommand("newLineSave");
        _Cancelbutton.setActionCommand("newLineCancel");
        
        _SaveChangesbutton.addActionListener((ActionListener) frame.getActionHandler());
        _Cancelbutton.addActionListener((ActionListener) frame.getActionHandler());
        setLayout(new GridLayout(4, 2));
        
        add(_itemNameLabel);
        add(_itemNameTextField);
        add(_itemCountLabel);
        add(_itemCountTextField);
        add(_itemPriceLabel);
        add(_itemPriceTextField);
        add(_SaveChangesbutton);
        add(_Cancelbutton);
        
        pack();
    }

    public JTextField getItemNameField() {
        return _itemNameTextField;
    }

    public JTextField getItemCountField() {
        return _itemCountTextField;
    }

    public JTextField getItemPriceField() {
        return _itemPriceTextField;
    }
}

    

