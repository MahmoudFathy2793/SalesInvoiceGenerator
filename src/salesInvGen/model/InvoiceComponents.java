
package salesInvGen.model;

import java.util.ArrayList;

public class InvoiceComponents {
    private int num;
    private String date;
    private String cst;
    private ArrayList<Item> items;
    
    public InvoiceComponents() {
    }

    public InvoiceComponents(int num, String date, String cst) {
        this.num = num;
        this.date = date;
        this.cst = cst;
    }

    public double getInvSum() {
        double sum = 0.0;
        for (Item row : getItems()) {
            sum += row.getRowSum();
        }
        return sum;
    }
    
    public ArrayList<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoice{" + "num=" + num + ", date=" + date + ", customer=" + cst + '}';
    }
    
    public String getCSVFile() {
        return num + "," + date + "," + cst;
    }
    
}
