
package salesInvGen.model;

public class Item {
    private String item;
    private double price;
    private int count;
    private InvoiceComponents inv;

    public Item() {
    }

    public Item(String item, double price, int count, InvoiceComponents inv) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.inv = inv;
    }

    public double getRowSum() {
        return price * count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Row{" + "num=" + inv.getNum() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

    public InvoiceComponents getInv() {
        return inv;
    }
    
    public String getCSVFile() {
        return inv.getNum() + "," + item + "," + price + "," + count;
    }
    
}
