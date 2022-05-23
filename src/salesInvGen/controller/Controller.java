package salesInvGen.controller;

import salesInvGen.model.InvoiceComponents;
import salesInvGen.model.InvoiceDetailsTableModel;
import salesInvGen.model.Item;
import salesInvGen.model.ItemsTableModel;
import salesInvGen.view.InvoiceDetails;
import salesInvGen.view.InvoiceGUI;
import salesInvGen.view.InvoiceItems;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener {

    private InvoiceGUI invFrame;
    private InvoiceDetails invDetails;
    private InvoiceItems itemDetails;

    public Controller(InvoiceGUI invFrame) {
        this.invFrame = invFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actCommand = e.getActionCommand();
        System.out.println("Action: " + actCommand);
        switch (actCommand) {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "cancelInvoice":
                cancelInvoice();
                break;
            case "createInvoiceApproved":
                createInvoiceApproved();
                break;
            case "createRowApproved":
                createRowApproved();
                break;
            case "cancelRow":
                cancelRow();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int chosenPosition = invFrame.getInvTable().getSelectedRow();
        if (chosenPosition != -1) {
            System.out.println("You have chosen row: " + chosenPosition);
            InvoiceComponents presentInvoice = invFrame.getNewInvoices().get(chosenPosition);
            invFrame.getInvNumLabel().setText("" + presentInvoice.getNum());
            invFrame.getInvDateLabel().setText(presentInvoice.getDate());
            invFrame.getCstNameLabel().setText(presentInvoice.getCst());
            invFrame.getInvTotalLabel().setText("" + presentInvoice.getInvSum());
            ItemsTableModel rowsTableModel = new ItemsTableModel(presentInvoice.getItems());
            invFrame.getItemTable().setModel(rowsTableModel);
            rowsTableModel.fireTableDataChanged();
        }
    }

    private void loadFile() {
        JFileChooser javaFC = new JFileChooser();
        try {
            int outcome = javaFC.showOpenDialog(invFrame);
            if (outcome == JFileChooser.APPROVE_OPTION) {
                File javaHeaderFile = javaFC.getSelectedFile();
                Path JavaHeaderPath = Paths.get(javaHeaderFile.getAbsolutePath());
                List<String> javaHeaderRows = Files.readAllLines(JavaHeaderPath);
                System.out.println("Invoices have shown");
                ArrayList<InvoiceComponents> invArray = new ArrayList<>();
                for (String headerRow : javaHeaderRows) {
                    String[] headerParts = headerRow.split(",");
                    int invNum = Integer.parseInt(headerParts[0]);
                    String invDate = headerParts[1];
                    String cstName = headerParts[2];

                    InvoiceComponents invoice = new InvoiceComponents(invNum, invDate, cstName);
                    invArray.add(invoice);
                }
                outcome = javaFC.showOpenDialog(invFrame);
                if (outcome == JFileChooser.APPROVE_OPTION) {
                    File rowFile = javaFC.getSelectedFile();
                    Path rowPath = Paths.get(rowFile.getAbsolutePath());
                    List<String> rowRows = Files.readAllLines(rowPath);
                    System.out.println("Items have shown");
                    for (String rowRow : rowRows) {
                        String[] rowParts = rowRow.split(",");
                        int invNum = Integer.parseInt(rowParts[0]);
                        String itemName = rowParts[1];
                        double itemPrice = Double.parseDouble(rowParts[2]);
                        int count = Integer.parseInt(rowParts[3]);
                        InvoiceComponents inv = null;
                        for (InvoiceComponents invoice : invArray) {
                            if (invoice.getNum() == invNum) {
                                inv = invoice;
                                break;
                            }
                        }

                        Item row = new Item(itemName, itemPrice, count, inv);
                        inv.getItems().add(row);
                    }
                    
                }
                invFrame.setNewInvoices(invArray);
                InvoiceDetailsTableModel invTableModel = new InvoiceDetailsTableModel(invArray);
                invFrame.setInvoicesTableModel(invTableModel);
                invFrame.getInvTable().setModel(invTableModel);
                invFrame.getInvoicesTableModel().fireTableDataChanged();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveFile() {
        ArrayList<InvoiceComponents> inv = invFrame.getNewInvoices();
        String hFile = "";
        String lFile = "";
        for (InvoiceComponents invoice : inv) {
            String invoiceCSVFile = invoice.getCSVFile();
            hFile += invoiceCSVFile;
            hFile += "\n";

            for (Item row : invoice.getItems()) {
                String rowCSV = row.getCSVFile();
                lFile += rowCSV;
                lFile += "\n";
            }
        }
        try {
            JFileChooser JavaFC = new JFileChooser();
            int outcome = JavaFC.showSaveDialog(invFrame);
            if (outcome == JFileChooser.APPROVE_OPTION) {
                File hf = JavaFC.getSelectedFile();
                FileWriter fw = new FileWriter(hf);
                fw.write(hFile);
                fw.flush();
                fw.close();
                outcome = JavaFC.showSaveDialog(invFrame);
                if (outcome == JFileChooser.APPROVE_OPTION) {
                    File rowFile = JavaFC.getSelectedFile();
                    FileWriter rowFileWriter = new FileWriter(rowFile);
                    rowFileWriter.write(lFile);
                    rowFileWriter.flush();
                    rowFileWriter.close();
                }
            }
        } catch (Exception ex) {

        }
    }

    private void createNewInvoice() {
        invDetails = new InvoiceDetails(invFrame);
        invDetails.setVisible(true);
    }

    private void deleteInvoice() {
        int chosenRow = invFrame.getInvTable().getSelectedRow();
        if (chosenRow != -1) {
            invFrame.getNewInvoices().remove(chosenRow);
            invFrame.getInvoicesTableModel().fireTableDataChanged();
        }
    }

    private void createNewItem() {
        itemDetails = new InvoiceItems(invFrame);
        itemDetails.setVisible(true);
    }

    private void deleteItem() {
        int chosenRow = invFrame.getItemTable().getSelectedRow();

        if (chosenRow != -1) {
            ItemsTableModel rowsTableModel = (ItemsTableModel) invFrame.getItemTable().getModel();
            rowsTableModel.getItems().remove(chosenRow);
            rowsTableModel.fireTableDataChanged();
            invFrame.getInvoicesTableModel().fireTableDataChanged();
        }
    }

    private void cancelInvoice() {
        invDetails.setVisible(false);
        invDetails.dispose();
        invDetails = null;
    }

    private void createInvoiceApproved() {
        String date = invDetails.getInvoiceDateTextField().getText();
        String cst = invDetails.getCustomerNameTextField().getText();
        int number = invFrame.getNextInvNum();

        InvoiceComponents inv = new InvoiceComponents(number, date, cst);
        invFrame.getNewInvoices().add(inv);
        invFrame.getInvoicesTableModel().fireTableDataChanged();
        invDetails.setVisible(false);
        invDetails.dispose();
        invDetails = null;
    }

    private void createRowApproved() {
        String oneItem = itemDetails.getItemNameTextField().getText();
        String countString = itemDetails.getItemCountTextField().getText();
        String priceString = itemDetails.getItemPriceTextField().getText();
        int xCount = Integer.parseInt(countString);
        double xPrice = Double.parseDouble(priceString);
        int chosenInvoice = invFrame.getInvTable().getSelectedRow();
        if (chosenInvoice != -1) {
            InvoiceComponents invoice = invFrame.getNewInvoices().get(chosenInvoice);
            Item row = new Item(oneItem, xPrice, xCount, invoice);
            invoice.getItems().add(row);
            ItemsTableModel rowsTableModel = (ItemsTableModel) invFrame.getItemTable().getModel();
            rowsTableModel.fireTableDataChanged();
            invFrame.getInvoicesTableModel().fireTableDataChanged();
        }
        itemDetails.setVisible(false);
        itemDetails.dispose();
        itemDetails = null;
    }

    private void cancelRow() {
        itemDetails.setVisible(false);
        itemDetails.dispose();
        itemDetails = null;
    }

}
