package bakery.order;
import java.util.ArrayList;
import java.util.Date;

import bakery.Order;

/** Empty Order List Class
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public class EntryOrder extends OrderList {
    /** this is the previous map */
    private OrderList m0;
    
    /** this is the order to be added to the map */
    private Order order0;

    /** Constructor
     * @param m0 the prev map
     * @param order0 this is order to be added
     */
    EntryOrder(Order order0, OrderList m0) {
        this.order0 = order0;
        this.m0 = m0;
    }
    
    /** Get rest of the list
     * @return rest of the order
     */
    OrderList getRest() {
        return m0;
    }
    
    /** Adds order to inventory
     * @param order
     *             order name
     * @return Inventory
     */
    public OrderList addToOrderList(Order order) {
        if (this.order0.equals(order)) {
            return new EntryOrder(order, m0);
        }
        else {
            return new EntryOrder(order, this);
        }
    }
    
    /** add to order list
     * @param orders the order list
     * @return returns new order list with new order
     */
    public OrderList addToOrderList(OrderList orders) {
        return new EntryOrder(getOrder(), getRest().addToOrderList(orders));
    }
    
    /** remove item from list.
     * @param orderID
     *             the order ID
     * @return Order List
     */
    public OrderList removeOrdersWithID(Integer orderID) {
        if (getOrder().getOrderID().equals(orderID)) {
            return getRest().removeOrdersWithID(orderID);
        }
        else {
            return new EntryOrder(getOrder(), 
                getRest().removeOrdersWithID(orderID));
        }
    }
    
    /** See whether the list is empty
     * @return false 
     */
    public boolean isEmpty() {
        return false;
    }
    
    /** Returns the size of the list
     * @return the size of list
     */
    public int size() {
        if (m0.containsOrder(order0)) {
            return m0.size();
        }
        else {
            return (1 + m0.size());
        }
    }
    
    /** Whether the map contains order.
     * @param order
     *         order in list
     * @return true or false
     */
    public boolean containsOrder(Order order) {
        if (order.equals(order0)) {
            return true;
        } 
        else {
            return m0.containsOrder(order);
        }
    }
    
    /** Checks if the list contains order.
     * @param iD
     *             order ID
     * @return true or false if it contains the order
     */
    public boolean containsOrder(Integer iD) {
        if (order0.getOrderID().equals(iD)) {
            return true;
        }
        else {
            return m0.containsOrder(iD);
        }
    }
    
    /** Get order
     * @return the order
     */
    private Order getOrder() {
        return this.order0;
    }
    
    /** Get the orders by order ID
     * @param orderID
     *             order ID
     * @return all orders with the same order ID
     */
    public OrderList getOrdersByOrderID(Integer orderID) {
        if (order0.getOrderID().equals(orderID)) {
            return new EntryOrder(order0, 
                getRest().getOrdersByOrderID(orderID));
        }
        else {
            return getRest().getOrdersByOrderID(orderID);
        }
    }
    
    /** Get the orders by customer ID
     * @param customerID
     *             order ID
     * @return all orders with the same customer ID
     */
    public OrderList getOrdersByCustomerID(Integer customerID) {
        if (order0.getCustomerID().equals(customerID)) {
            return new EntryOrder(order0, 
                getRest().getOrdersByCustomerID(customerID));
        }
        else {
            return getRest().getOrdersByCustomerID(customerID);
        }
    }
    
    
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    public boolean equals(Object o) {
        if (o instanceof OrderList) {
            OrderList m2 = ((OrderList) o);
            if (this.size() == m2.size()) {
                for (Order key : this) {
                    if (!m2.containsOrder(key)) {
                        return false;
                    }
                } 
                return true;
            }    
        }
        return false;
    }
    
    /** This returns the hashcode.
     * @return hashcode for map
     */
    public int hashCode() {
        return 2;
    }
    
    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             Array List
     * @return An Array list
     */
    public ArrayList<Order> getAllOrders(ArrayList<Order> x) {
        x.add(getOrder());    
        return getRest().getAllOrders(x);
    }
   
    /** Override toString method
     * @return string
     */
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } 
        else {
            return this.getOrder().toString() + "\n" + m0.toString();
        }
    }

    /** Get the total of the order
     * @param orderID the order ID
     * @return return the total
     */
    public double getOrderTotal(Integer orderID) {
        if (getOrder().getOrderID().equals(orderID)) {
            return getOrder().getTotal() + getRest().getOrderTotal(orderID);
        }
        
        else {
            return getRest().getOrderTotal(orderID);
        }
    }
    
    /** Get the orders by date
     * @param dPickupDate
     *             the pick up date
     * @return all orders with the same pick up date ID
     */
    public OrderList getOrdersPlacedOn(Date dPickupDate) {
        if ((getOrder().getOrderDate().getTime() - 
                dPickupDate.getTime()) <= (1000 * 60 * 60 * 24) && 
                    (getOrder().getOrderDate().getTime() - 
                        dPickupDate.getTime()) >= 0) {
            return new EntryOrder(order0, 
                getRest().getOrdersPlacedOn(dPickupDate));
        }
        else {
            return getRest().getOrdersPlacedOn(dPickupDate);
        }       
    }
    
    /** Get the orders by date
     * @param dPickupDate
     *             the pick up date
     * @return all orders with the same pick up date ID
     */
    public OrderList getOrdersWithPickupDate(Date dPickupDate) {
        if ((getOrder().getPickUpDate().getTime() - 
                dPickupDate.getTime()) <= (1000 * 60 * 60 * 24) && 
                    (getOrder().getPickUpDate().getTime() - 
                        dPickupDate.getTime()) >= 0) {
            return new EntryOrder(order0, 
                getRest().getOrdersWithPickupDate(dPickupDate));
        }
        else {
            return getRest().getOrdersWithPickupDate(dPickupDate);
        }       
    }
    
    /** Order List with new status
     * @param newPaidStatus change the paid status
     * @param newPickupDate change the pick up date
     * @return order list
     */
    public OrderList withNewStatus(boolean newPaidStatus, Date newPickupDate) {
        Order o = getOrder();
        return new EntryOrder(new Order(o.getOrderID(), o.getItem(), 
            o.getTotal(), o.getQuantity(), o.getCustomerID(), 
                o.getLoyaltyAtTimeOfOrder(), o.getAvailableDiscount(), 
                    o.getDiscountUsedOnOrder(), newPaidStatus, 
                        o.getOrderDate(), newPickupDate), 
                            getRest().withNewStatus(newPaidStatus, 
                                newPickupDate));
    }

    /** Get one order by order ID
     * @param orderID
     *             order ID
     * @return one order with the order ID
     */
    public Order getOneOrderWithID(Integer orderID) {
        if (getOrder().getOrderID().equals(orderID)) {
            return getOrder();
        }
        else {
            return getRest().getOneOrderWithID(orderID);
        }
    }
}
