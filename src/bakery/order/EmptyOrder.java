package bakery.order;
import java.util.ArrayList;
import java.util.Date;

import bakery.Order;

/** Empty Order List Class
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public class EmptyOrder extends OrderList {
    /** Empty Constructor */
    EmptyOrder() {
        /** Empty Constructor */
    }
   
    /** Creates an OrderInventory class.
     * @param ord
     *         order
     * @return new order entry
     */
    public OrderList addToOrderList(Order ord) {
        return new EntryOrder(ord, this);
    }
    
    /** add to order list
     * @param orders the order list
     * @return returns new order list with new order
     */
    public OrderList addToOrderList(OrderList orders) {
        return orders;
    }
    
    /** remove item from list.
     * @param orderID
     *             the order ID
     * @return Order List
     */
    public OrderList removeOrdersWithID(Integer orderID) {
        return this;
    }
    
    /** See whether the list is empty
     * @return true 
     */
    public boolean isEmpty() {
        return true;
    }
    
    /** Returns the size of the map.
     * @return size of empty map 0 
     */
    public int size() {
        return 0;
    }
    
    /** Whether the map contains item.
     * @param item
     *         item in inventory
     * @return false
     */
    public boolean containsOrder(Order item) {
        return false;
    }
    
    /** Checks if the list contains order.
     * @param iD
     *             order ID
     * @return true or false if it contains the order
     */
    public boolean containsOrder(Integer iD) {
        return false;
    }
    
    /** Get the orders by order ID
     * @param orderID
     *             order ID
     * @return all orders with the same order ID
     */
    public OrderList getOrdersByOrderID(Integer orderID) {
        return this;
    }
    
    /** Get the orders by customer ID
     * @param customerID
     *             order ID
     * @return all orders with the same customer ID
     */
    public OrderList getOrdersByCustomerID(Integer customerID) {
        return this;
    }
    
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    public boolean equals(Object o) {
        if (o instanceof OrderList) {
            OrderList m1 = (OrderList) o;
            return m1.isEmpty();
        }
        return false;
    }
    
    /** returns the hashcode of the map.
     * @return HashCode for empty map
     */
    public int hashCode() { 
        return 1;
    }

    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             An array list
     * @return Array list with ks
     */
    public ArrayList<Order> getAllOrders(ArrayList<Order> x) {
        return x;
    }
    
    /** Overriding toString method
     * @return string
     */
    public String toString() {
        return "";
    }

    /** Get the total of the order
     * @param orderID the order ID
     * @return return the total
     */
    public double getOrderTotal(Integer orderID) {
        return 0;
    }

    /** Get the orders by date
     * @param dPickUpDate
     *             the pick up date
     * @return all orders with the same pick up date ID
     */
    public OrderList getOrdersPlacedOn(Date dPickupDate) {
        return this;
    }

    /** Get the orders by date
     * @param dPickUpDate
     *             the pick up date
     * @return all orders with the same pick up date ID
     */
    public OrderList getOrdersWithPickupDate(Date dPickupDate) {
        return this;
    }

    /** Order List with new status
     * @param newPaidStatus change the paid status
     * @param newPickupDate change the pick up date
     * @return order list
     */
    public OrderList withNewStatus(boolean newPaidStatus, Date newPickupDate) {
        return this;
    }

    /** Get one order by order ID
     * @param orderID
     *             order ID
     * @return one order with the order ID
     */
    public Order getOneOrderWithID(Integer orderID) {
        throw new RuntimeException("No order exists with that order ID!");
    }
    
}
