package bakery.order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import bakery.Item;
import bakery.Order;

/** Order List Class
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public abstract class OrderList implements Iterable<Order> {
     // Static Methods
    /** Returns a new Empty map.
     * @return new EmptyInventory
     */
    public static OrderList emptyOrder() {
        return new EmptyOrder();
    }
    
    // Dynamic Methods
    
    /** Adds item to the list
     * @param customerID customer ID
     * @param orderID order ID
     * @param total Order total
     * @param paid whether it is paid or not
     * @param orderDate the order date
     * @param pickUpDate the pick up date
     * @param item the item in the order
     * @param quantity the quantity of the item
     * @param loyaltyAtTimeOfOrder rewards points at time of order
     * @param availableDiscount the available discount
     * @param discountUsedOnOrder the discount used in the order
     */
    public OrderList addToOrderList(Integer customerID, Integer orderID, 
        double total, boolean paid, Date orderDate, Date pickUpDate, 
            Item item, Integer quantity, double loyaltyAtTimeOfOrder, 
                double  availableDiscount, double discountUsedOnOrder) {
        Order newOrder = new Order(orderID, item, total, quantity, 
            customerID, loyaltyAtTimeOfOrder,  availableDiscount, 
                discountUsedOnOrder, paid, orderDate, pickUpDate);
        
        return new EntryOrder(newOrder, this); 
    }
    
    /** add to order list
     * @param orders the order list
     * @return returns new order list with new order
     */
    public abstract OrderList addToOrderList(OrderList orders);
    
    private Integer lastUsedID = 1;
    
    /** gets the next available ID
     * @return available ID
     */
    public Integer getAvailableOrderID() {
        while (containsOrder(lastUsedID)) {
            lastUsedID++;
        }
        return lastUsedID;
    }
    
    /** Adds order to the list 
     * @param order
     *            order name
     * @return Order List
     */
    public abstract OrderList addToOrderList(Order order);
    
    /** remove item from list.
     * @param orderID
     *             the order ID
     * @return Order List
     */
    public abstract OrderList removeOrdersWithID(Integer orderID);
    
    
    /** Checks if a inventory is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the inventory.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the list contains order.
     * @param order
     *             order name
     * @return true or false if it contains the order
     */
    public abstract boolean containsOrder(Order order);
    
    /** Checks if the list contains order.
     * @param orderID
     *             order ID
     * @return true or false if it contains the order
     */
    public abstract boolean containsOrder(Integer orderID);
    
    /** Get the orders by order ID
     * @param orderID
     *             order ID
     * @return all orders with the same order ID
     */
    public abstract OrderList getOrdersByOrderID(Integer orderID);
    
    /** Get one order by order ID
     * @param orderID
     *             order ID
     * @return one order with the order ID
     */
    public abstract Order getOneOrderWithID(Integer orderID);
    
    /** Get the orders by customer ID
     * @param customerID
     *             order ID
     * @return all orders with the same customer ID
     */
    public abstract OrderList getOrdersByCustomerID(Integer customerID);
    
    /** Get the orders by date
     * @param dPickUpDate
     *             the pick up date
     * @return all orders with the same pick up date ID
     */
    public abstract OrderList getOrdersPlacedOn(Date dPickupDate);
    
    /** Get the orders by date
     * @param dPickUpDate
     *             the pick up date
     * @return all orders with the same pick up date ID
     */
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
    
    /** Get the total of the order
     * @param orderID the order ID
     * @return return the total
     */
    public abstract double getOrderTotal(Integer orderID);
    
    
    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             Array List x
     * @return Array List
     */
    public abstract ArrayList<Order> getAllOrders(ArrayList<Order> x);

    /** Iterator.
     * @return new MyIterator 
     */
    public MyIterator<Order> iterator() {
        ArrayList<Order> orders = this.getAllOrders(new ArrayList<Order>());
        Collections.sort(orders, new OrderComparator());
        return new MyIterator<Order> (orders);
    }
    
    /**
     * Order comparator
     * @author Jesus Cheng
     * @author Dan Osborne
     * @version 1.0
     */
    private class OrderComparator implements Comparator<Order> {
        /**
         * Standard string compare which complies with comparator
         * 
         * @param order1
         *            order1 to compare
         * @param order2
         *            order2 to compare
         * @return 0 if equal, 1 for all else
         */
        public int compare(Order order1, Order order2) {
            if (order1.getOrderDate().getTime() > 
                order2.getOrderDate().getTime()) {
                return 1;
            }
            else if (order1.getOrderDate().getTime() < 
                order2.getOrderDate().getTime()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    
    /** Order List with new status
     * @param newPaidStatus change the paid status
     * @param newPickupDate change the pick up date
     * @return order list
     */
    public abstract OrderList withNewStatus(boolean newPaidStatus, 
        Date newPickupDate);
}
