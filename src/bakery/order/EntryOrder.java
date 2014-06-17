package bakery.order;
import java.util.ArrayList;
import java.util.Date;

import bakery.Item;
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
    
    /** Adds order to inventory
     * @param orderID order ID
     * @param orderName Name of the order
     * @param category Order category
     * @param orderPrice order price
     * @return Inventory
     */
    public OrderList addToOrderList(int customerID, Integer orderID, boolean paid, Date orderDate, Date pickUpDate, Item item, Integer quantity, double loyaltyAtTimeOfOrder, double discountUsedOnOrder) {
    	Order ord = new Order(orderID, item, quantity, customerID, loyaltyAtTimeOfOrder, discountUsedOnOrder, paid, orderDate, pickUpDate);
        return this.addToOrderList(ord);
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
    
    /** Removes order from stock class.
     * @param order
     *             order name
     * @param quantity
     *             quantity of order
     * @return new Inventory
     */
    public OrderList removeFromOrderList(Order order) {
        if (this.order0.equals(order)) {
            return new EntryOrder(order, m0);
        }
        else {
            return this.m0.removeFromOrderList(order);
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
    	if (order0.getOrderID() == ID) {
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
    
    public Order getOrder(int ID) {
    	if (getOrder().getOrderID() == ID) {
    		return getOrder();
    	}
    	else {
    		return getRest().getOrder(ID);
    	}
    }
    
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    @SuppressWarnings("unchecked")
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
    public ArrayList getArrayKeys(ArrayList x) {
        if (!m0.containsOrder(order0)) {
            x.add(this.order0);
            return (m0.getArrayKeys(x));
        }
        else {
            return (m0.getArrayKeys(x));
        }
    }
   
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } 
        else {
            return this.getOrder().toString() + "\n" + m0.toString();
        }
    }

	@Override
	public Order getOrder(Integer ID) {
		if (getOrder().getOrderID()== ID) {
			return getOrder();
		}
		else {
			return getRest().getOrder(ID);
		}
	}
}
