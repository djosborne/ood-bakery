package bakery.order;
import java.util.ArrayList;
import java.util.Date;

import bakery.Item;
import bakery.Order;

public abstract class OrderList implements Iterable<Order> {
     // Static Methods
    /** Returns a new Empty map.
     * @return new EmptyInventory
     */
    public static OrderList emptyOrder() {
        return new EmptyOrder();
    }
    
    // Dynamic Methods
    
    /** Adds item to inventory
     * @param itemID item ID
     * @param itemName Name of the item
     * @param category Order category
     * @param itemPrice item price
     * @return Inventory
     */
    public OrderList addToOrderList(int customerID, Integer orderID, boolean paid, Date orderDate, Date pickUpDate, Item item, Integer quantity, double loyaltyAtTimeOfOrder, double discountUsedOnOrder) {
        Order newOrder = new Order(orderID, item, quantity, customerID, loyaltyAtTimeOfOrder, discountUsedOnOrder, paid, orderDate, pickUpDate);
        return new EntryOrder(newOrder, this); 
    }
    
    public Integer getAvailableOrderID() {
        return 1;
    }
    /** Adds item to inventory
     * @param item
     *             item name
     * @return Inventory
     */
    public abstract OrderList addToOrderList(Order order);
    
    /** remove item from inventory.
     * @param item
     *             item name
     * @return Inventory
     */
    public abstract OrderList removeFromOrderList(Order order);
    
    
    /** Checks if a inventory is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the inventory.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the inventory contains item.
     * @param item
     *             item name
     * @return true or false if it contains the item
     */
    public abstract boolean containsOrder(Order order);
    
    public abstract boolean containsOrder(Integer orderID);
    
    public abstract OrderList getOrdersByOrderID(Integer orderID);
    
    public abstract OrderList getOrdersByCustomerID(Integer customerID);
    
    // Dynamic Methods
    /** override toString method from object.
     * @return a string 
     */
//    public String toString() {
//        return ("{...[There are " + this.size() + 
//            " unique key(s) mapped to " + "value(s) in this Inventory]...}");
//    }
    public abstract String toString();
    
    
    public abstract Order getOrder(Integer orderID);
    
    public abstract double getOrderTotal(Integer orderID);
    
    
    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             Array List x
     * @return Array List
     */
    public abstract ArrayList getArrayKeys(ArrayList<Order> x);

    /** Iterator.
     * @return new MyIterator 
     */
    public MyIterator iterator() {
        return new MyIterator(this.getArrayKeys(new ArrayList<Order>()));
    }
}
