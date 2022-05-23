
package salesInvGen.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemsTableModel extends AbstractTableModel {

    private ArrayList<Item> items;
    private String[] cols = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public ItemsTableModel(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    
    
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int var) {
        return cols[var];
    }
    
    @Override
    public Object getValueAt(int rowPosition, int colPosition) {
        Item item = items.get(rowPosition);
        
        switch(colPosition) {
            case 0: return item.getInv().getNum();
            case 1: return item.getItem();
            case 2: return item.getPrice();
            case 3: return item.getCount();
            case 4: return item.getRowSum();
            default : return "";
        }
    }
    
}
