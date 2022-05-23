
package salesInvGen.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvoiceItems extends JDialog{
    private JTextField itemNameTextField;
    private JTextField itemCountTextField;
    private JTextField itemPriceTextField;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton okButton;
    private JButton cancelButton;
    
    public InvoiceItems(InvoiceGUI invFrame) {
        itemNameTextField = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        
        itemCountTextField = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        
        itemPriceTextField = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createRowApproved");
        cancelButton.setActionCommand("cancelRow");
        
        okButton.addActionListener(invFrame.getCont());
        cancelButton.addActionListener(invFrame.getCont());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel);
        add(itemNameTextField);
        add(itemCountLabel);
        add(itemCountTextField);
        add(itemPriceLabel);
        add(itemPriceTextField);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getItemNameTextField() {
        return itemNameTextField;
    }

    public JTextField getItemCountTextField() {
        return itemCountTextField;
    }

    public JTextField getItemPriceTextField() {
        return itemPriceTextField;
    }
}
