package bakery.order;
import java.util.ArrayList;
import java.util.Date;

import bakery.Item;
import bakery.Order;
import bakery.Order;

public class EmptyOrder extends OrderList {
    /** Empty Constructor */
    EmptyOrder() {
        /** Empty Constructor */
    }
    
//    public OrderList addToOrderList(int customerID, Integer orderID, boolean paid, Date orderDate, Date pickUpDate, Item item, Integer quantity, double loyaltyAtTimeOfOrder, double discountUsedOnOrder) {
//        Order ord = new Order(orderID, item, quantity, customerID, loyaltyAtTimeOfOrder, discountUsedOnOrder, paid, orderDate, pickUpDate);
//        return new EntryOrder(ord, this);
//    }
    
    /** Creates an OrderInventory class.
     * @param item
     *         item in inventory
     * @return new Inventory
     */
    public OrderList addToOrderList(Order ord) {
        return new EntryOrder(ord, this);
    }
    
    /** Removes item from stock class.
     * @param item
     *         item in inventory
     * @return new Inventory
     */
    public OrderList removeFromOrderList(Order item) {
        return this;
    }
    
    /** See whether a map is empty
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
    
    public boolean containsOrder(Integer ID) {
    	return false;
    }
    
	public Order getOrder(Integer orderID) {
		throw new RuntimeException("No item with such ID exists");
	}
    
    public OrderList getOrdersByOrderID(Integer orderID) {
		return this;
	}
    
    public OrderList getOrdersByCustomerID(Integer customerID) {
        return this;
    }
	
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    @SuppressWarnings("unchecked")
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
    public ArrayList getAllOrders(ArrayList x) {
        return x;
    }
    
    public String toString() {
        return "";
    }

    public double getOrderTotal(Integer orderID) {
        return 0;
    }

    public OrderList getOrdersPlacedOn(Date dPickupDate) {
        return this;
    }

    public OrderList getOrdersWithPickupDate(Date dPickupDate) {
        return this;
    }
    
}
