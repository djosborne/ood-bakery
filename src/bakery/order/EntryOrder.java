package bakery.order;
import java.util.ArrayList;
import java.util.Date;

import bakery.Order;

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
    
    OrderList getRest() {
    	return m0;
    }
    
//    /** Adds order to inventory
//     * @param orderID order ID
//     * @param orderName Name of the order
//     * @param category Order category
//     * @param orderPrice order price
//     * @return Inventory
//     */
//    public OrderList addToOrderList(int customerID, Integer orderID, boolean paid, Date orderDate, Date pickUpDate, Item item, Integer quantity, double loyaltyAtTimeOfOrder, double discountUsedOnOrder) {
//    	Order ord = new Order(orderID, item, quantity, customerID, loyaltyAtTimeOfOrder, discountUsedOnOrder, paid, orderDate, pickUpDate);
//        return this.addToOrderList(ord);
//    }
    
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
    
    public OrderList addToOrderList(OrderList orders) {
            return new EntryOrder(getOrder(), getRest().addToOrderList(orders));
    }
    
    /** Removes order from stock class.
     * Assumes
     * @param order
     *             order name
     * @param quantity
     *             quantity of order
     * @return new Inventory
     */
    public OrderList removeOrdersWithID(Integer orderID) {
        if (getOrder().getOrderID().equals(orderID)) {
            return getRest().removeOrdersWithID(orderID);
        }
        else {
            return new EntryOrder(getOrder(), getRest().removeOrdersWithID(orderID));
        }
    }
    
    /** Checks if map is empty.
     * @return whether a map is empty
     */
    public boolean isEmpty() {
        return false;
    }
    
    /** Returns the size of the map
     * @return the size of map
     */
    public int size() {
        if (m0.containsOrder(order0)) {
            return m0.size();
        }
        else {
            return (1 + m0.size());
        }
    }
    
    /** Checks if contains key.
     * @param order
     *             order name
     * @return whether the map contains order
     */
    public boolean containsOrder(Order order) {
        if (order.equals(order0)) {
            return true;
        } 
        else {
            return m0.containsOrder(order);
        }
    }
    
    public boolean containsOrder(Integer ID) {
    	if (order0.getOrderID().equals(ID)) {
    		return true;
    	}
    	else {
    		return m0.containsOrder(ID);
    	}
    }
    
    /** Get the value from key.
     * @param k
     *             get Value from K
     * @return the value from the key
     */
    private Order getOrder() {
        return this.order0;
    }
    
    
    public OrderList getOrdersByOrderID(Integer orderID) {
    	if (order0.getOrderID().equals(orderID)) {
    		return new EntryOrder(order0, getRest().getOrdersByOrderID(orderID));
    	}
    	else {
    		return getRest().getOrdersByOrderID(orderID);
    	}
	}
    
    
    public OrderList getOrdersByCustomerID(Integer customerID) {
        if (order0.getCustomerID().equals(customerID)) {
            return new EntryOrder(order0, getRest().getOrdersByCustomerID(customerID));
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
    public ArrayList<Order> getAllOrders(ArrayList<Order>x) {
        x.add(getOrder());    
        return getRest().getAllOrders(x);
    }
   
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } 
        else {
            return this.getOrder().toString() + "\n" + m0.toString();
        }
    }

    
    public double getOrderTotal(Integer orderID) {
        if (getOrder().getOrderID().equals(orderID)) {
            return getOrder().getTotal() + getRest().getOrderTotal(orderID);
        }
        
        else {
            return getRest().getOrderTotal(orderID);
        }
    }
    
    public OrderList getOrdersPlacedOn(Date dPickupDate) {
        if ((getOrder().getOrderDate().getTime() - dPickupDate.getTime()) <= (1000* 60* 60 * 24) && 
            (getOrder().getOrderDate().getTime() - dPickupDate.getTime()) >= 0) {
            return new EntryOrder(order0, getRest().getOrdersPlacedOn(dPickupDate));
        }
        else {
            return getRest().getOrdersPlacedOn(dPickupDate);
        }       
    }
    
    public OrderList getOrdersWithPickupDate(Date dPickupDate) {
        if ((getOrder().getPickUpDate().getTime() - dPickupDate.getTime()) <= (1000* 60* 60 * 24) && 
            (getOrder().getPickUpDate().getTime() - dPickupDate.getTime()) >= 0) {
            return new EntryOrder(order0, getRest().getOrdersWithPickupDate(dPickupDate));
        }
        else {
            return getRest().getOrdersWithPickupDate(dPickupDate);
        }       
    }
    
    public OrderList withNewStatus(boolean newPaidStatus, Date newPickupDate) {
        Order o = getOrder();
        return new EntryOrder(new Order(o.getOrderID(), o.getItem(), o.getTotal(), o.getQuantity(), o.getCustomerID(), o.getLoyaltyAtTimeOfOrder(), o.getAvailableDiscount(), o.getDiscountUsedOnOrder(), newPaidStatus, o.getOrderDate(), newPickupDate), getRest().withNewStatus(newPaidStatus, newPickupDate));
    }

    public Order getOneOrderWithID(Integer orderID) {
        if (getOrder().getOrderID().equals(orderID)) {
            return getOrder();
        }
        else {
            return getRest().getOneOrderWithID(orderID);
        }
    }
}
