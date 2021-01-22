import java.util.ArrayList;

public class VendingMachine {

    public VendingMachine() {
        this.stock = new ArrayList<>();
        this.currentBalance =100;
    }
    ArrayList<Item> stock;
    private double currentBalance;

    public ArrayList<Item> getStock() {
        return stock;
    }

    public double getInsertedAmount() {
        return insertedAmount;
    }

    private double insertedAmount;

    public boolean isEmpty() {
        if(stock.isEmpty())
        return true;
        else
        return false;
    }

    public int size() {
        return stock.size();
    }

    public void addItem(Item item) {
     stock.add(item);
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void withdrawBalance(double v) {
        this.currentBalance -= v;
    }

    public void depositBalance(double v) {
        this.currentBalance +=v;
    }

    public ArrayList<Item> getStockItems() {

        return stock;
    }

    public void insertAmount(double v) {
        this.insertedAmount =v;
    }

    public double checkAmount() {
        return this.insertedAmount;
    }

    public Item purchaseItem(String a1) throws Exception{
        for (Item item : stock) {
            if (this.insertedAmount < item.getItemPrice()) {
                throw new InsufficientFunds("You have Insufficient Funds");
            } else {
                if (item.getItemCode().equalsIgnoreCase(a1)) {
                    this.insertedAmount -=item.getItemPrice();
                    this.currentBalance += item.getItemPrice();
                    this.stock.remove(item);
                    return item;
                } else {

                    throw new NonExistingItem("No such Item with this Code");
                }
            }
        }
               return null;
    }


    public double cancelAndRefund() {
        return this.insertedAmount;
    }
}

