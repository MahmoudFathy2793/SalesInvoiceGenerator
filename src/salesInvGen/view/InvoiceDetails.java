
package salesInvGen.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvoiceDetails extends JDialog {
    private JTextField customerNameTextField;
    private JTextField invoiceDateTextField;
    private JLabel customerNameLabel;
    private JLabel invoiceDateLabel;
    private JButton okButton;
    private JButton cancelButton;

    public InvoiceDetails(InvoiceGUI invFrame) {
        customerNameLabel = new JLabel("Customer Name:");
        customerNameTextField = new JTextField(20);
        invoiceDateLabel = new JLabel("Invoice Date:");
        invoiceDateTextField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createInvoiceApproved");
        cancelButton.setActionCommand("cancelInvoice");
        
        okButton.addActionListener(invFrame.getCont());
        cancelButton.addActionListener(invFrame.getCont());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLabel);
        add(invoiceDateTextField);
        add(customerNameLabel);
        add(customerNameTextField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getCustomerNameTextField() {
        return customerNameTextField;
    }

    public JTextField getInvoiceDateTextField() {
        return invoiceDateTextField;
    }
    
}
