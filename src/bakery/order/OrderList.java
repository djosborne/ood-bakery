package bakery.order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public OrderList addToOrderList(int customerID, Integer orderID, boolean paid, Date orderDate, Date pickUpDate, Item item, Integer quantity, double loyaltyAtTimeOfOrder, double  availableDiscount, double discountUsedOnOrder) {
        Order newOrder = new Order(orderID, item, quantity, customerID, loyaltyAtTimeOfOrder,  availableDiscount, discountUsedOnOrder, paid, orderDate, pickUpDate);
        return new EntryOrder(newOrder, this); 
    }
    
    private Integer lastUsedID = 1;
    public Integer getAvailableOrderID() {
        while (containsOrder(lastUsedID)) {
            lastUsedID++;
        }
        return lastUsedID;
    }
    
    /** Adds item to inventory
     * @param item
     *             item name
     * @return Inventory
     */
    public abstract OrderList addToOrderList(Order order);
    
    // TODO: FIX THIS OR REMOVE IT (IT OUTPUTS ONLY THE ORDER)
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
    
    public abstract OrderList getOrdersPlacedOn(Date dPickupDate);
    
    public abstract OrderList getOrdersWithPickupDate(Date dPickupDate);
    
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
    public abstract ArrayList<Order> getArrayKeys(ArrayList<Order> x);

    /** Iterator.
     * @return new MyIterator 
     */
    public MyIterator iterator() {
        ArrayList<Order> orders = this.getArrayKeys(new ArrayList<Order>());
        Collections.sort(orders, new OrderComparator());
        return new MyIterator(orders);
    }
    
    
    private class OrderComparator implements Comparator<Order> {
        /**
         * Standard string compare which complies with comparator
         * 
         * @param key1
         *            Key1 to compare
         * @param key2
         *            Other key to compare
         * @return 0 if equal, 1 for all else
         */
        public int compare(Order order1, Order order2) {
            if (order1.getOrderDate().getTime() > order2.getOrderDate().getTime()) {
                return 1;
            }
            else if (order1.getOrderDate().getTime() < order2.getOrderDate().getTime()) {
                return -1;
            }
            else return 0;
        }
    }
}
