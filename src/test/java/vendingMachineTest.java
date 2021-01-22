import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class vendingMachineTest {
    public VendingMachine vendingMachine;

    @Before
    public void init(){
        vendingMachine = new VendingMachine();
    }
    /*Given there are no items in stock
    When I check the vending machine
    Then I should see it is empty*/

    @Test
    public void testEmptyVendingMachine(){
    assertTrue(vendingMachine.isEmpty());
    }

   /*Given there are no items in stock
     When I add some items
     Then I should see them in the vending machine*/

    @Test
    public void testAddItemstoVendingMachine(){
    vendingMachine.addItem(new Item("Chips",2.99, "A1"));
    vendingMachine.addItem(new Item("Sprite",1.99,"A2"));
    assertFalse(vendingMachine.isEmpty());
    assertEquals(2,vendingMachine.size());
    }

    /*Given there are some balance
    When I check the balance of the vending machine
    Then I should see the current balance*/

    @Test
    public void testCheckandReturnBalance(){
    assertEquals(100.00,vendingMachine.getCurrentBalance(),0.001);
    }

    /*When I withdraw money from the vending machine
    Then I should see the balance decrease*/

    @Test
    public void testWithdrawBalance(){
    vendingMachine.withdrawBalance(50.00);
    assertEquals(50.00,vendingMachine.getCurrentBalance(),0.001);
    }

    /*When I deposit money to the vending machine
    Then I should see the balance increase*/

    @Test
    public void testDepositBalance(){
        vendingMachine.depositBalance(50.00);
        assertEquals(150.00,vendingMachine.getCurrentBalance(),0.001);
    }

    /*Given there are items in stock
     When I check what is available
     Then I should see the name, price and code of the items*/

    @Test
    public void checkStockItems(){
        Item Chips = new Item("Chips",2.99, "A1");
        Item Sprite = new Item("Sprite",1.99,"A2");
        vendingMachine.addItem(Chips);
        vendingMachine.addItem(Sprite);
        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(Chips);
        expectedItems.add(Sprite);
        assertEquals(expectedItems,vendingMachine.getStockItems());
    }

    /*Given I have inserted some money into the vending machine
    When I check amount inserted
    Then I should see the amount */

    @Test
    public void checkInsertedAmount() {

        vendingMachine.insertAmount(5.00);
        assertEquals(5.00, vendingMachine.checkAmount(),0.001);
    }

    /*Given I have inserted enough money for an item
     When I enter the item code
     Then I get the item
     And the balance of the vending machine goes up by that amount
     And the item is removed from the stock */

     @Test
     public void testPurchaseItem() throws Exception {
         Item Chips = new Item("Chips",2.99, "A1");
         Item Sprite = new Item("Sprite",1.99,"A2");
         vendingMachine.addItem(Chips);
         vendingMachine.addItem(Sprite);
         vendingMachine.insertAmount(10.00);
         vendingMachine.purchaseItem("A1");
         assertEquals(1,vendingMachine.size());
         assertEquals(102.99,vendingMachine.getCurrentBalance(),0.001);
     }

    /* Given I have inserted some amount of money
    When I enter a non-existent item code
    Then the vending machine shows no such item*/

     @Test(expected = NonExistingItem.class)
    public void testNonExistingItemCodePurchase() throws Exception {
         Item Chips = new Item("Chips",2.99, "A1");
         Item Sprite = new Item("Sprite",1.99,"A2");
         vendingMachine.addItem(Chips);
         vendingMachine.addItem(Sprite);
         vendingMachine.insertAmount(10.00);
         Item purchaseItem = vendingMachine.purchaseItem("Z1");
     }

     /*Given I have inserted some amount of money
    When I enter an item code of an item that costs more that I have inserted
    Then the vending machine shows insufficient fund*/

        @Test(expected = InsufficientFunds.class)
        public void testInsufficientFunds() throws Exception {
        Item Chips = new Item("Chips",2.99, "A1");
        Item Sprite = new Item("Sprite",1.99,"A2");
        vendingMachine.addItem(Chips);
        vendingMachine.addItem(Sprite);
        vendingMachine.insertAmount(1.00);
        vendingMachine.purchaseItem("A1");
    }

    /*Given I have inserted some amount of money
     When I enter an item code of an item that costs less that I have inserted
     Then I get the item and the change */

    @Test
    public void successfulPurchaseReturnsChange() throws Exception{
        Item Chips = new Item("Chips",2.99, "A1");
        Item Sprite = new Item("Sprite",1.99,"A2");
        vendingMachine.addItem(Chips);
        vendingMachine.addItem(Sprite);
        vendingMachine.insertAmount(5.00);
        vendingMachine.purchaseItem("A1");
        assertEquals(1,vendingMachine.size());
        assertEquals(2.01,vendingMachine.getInsertedAmount(),0.001);

    }

    /*Given I have inserted some amount of money
    When I press the cancel button
    Then I get the full amount refunded */

    @Test
    public void insertedMoneyCancelandRedund(){
    Item Chips = new Item("Chips",2.99, "A1");
    Item Sprite = new Item("Sprite",1.99,"A2");
        vendingMachine.addItem(Chips);
        vendingMachine.addItem(Sprite);
        vendingMachine.insertAmount(5.00);
        double refundAmount = vendingMachine.cancelAndRefund();
        assertEquals(5.00,refundAmount,0.001);
    }

}
