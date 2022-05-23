
package salesInvGen.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceDetailsTableModel extends AbstractTableModel {
    private ArrayList<InvoiceComponents> inv;
    private String[] cols = {"No.", "Date", "Customer", "Total"};
    
    public InvoiceDetailsTableModel(ArrayList<InvoiceComponents> inv) {
        this.inv = inv;
    }
    
    @Override
    public int getRowCount() {
        return inv.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }
    
    @Override
    public Object getValueAt(int linePosition, int columnPosition) {
        InvoiceComponents invoice = inv.get(linePosition);
        
        switch (columnPosition) {
            case 0: return invoice.getNum();
            case 1: return invoice.getDate();
            case 2: return invoice.getCst();
            case 3: return invoice.getInvSum();
            default : return "";
        }
    }
}
