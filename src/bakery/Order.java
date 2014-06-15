package bakery;

import bakery.inventory.Inventory;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {
    private int customerID;
	private int orderID;
	private boolean paid;
	private Date orderDate;
	private Date pickUpDate;
	private Item item;
	private int quantity;
//	private double total;
	private double discount; // TODO do we need this?
//	private double totalDue;

	/**
	 * Construct a new order with given parameters
	 * 
	 * @param orderID
	 *            order ID
	 * @param orderName
	 *            order name
	 * @param category
	 *            order category
	 * @param order
	 *            Price Price for order
	 */
	public Order(Item item, Integer quantity, int customerID, boolean paid, Date pickUpDate) {
		this.customerID = customerID;
	    this.orderID = 0; // TODO: generate
		this.paid = paid;
		this.orderDate = new Date();
		this.pickUpDate = pickUpDate;
		this.item = item;
		this.quantity = quantity;
	}
	
	   public Order(Item item, Integer quantity, int customerID, boolean paid, Date orderDate, Date pickUpDate) {
	       this.customerID = customerID; 
	       this.orderID = 0; // TODO: generate
	        this.paid = paid;
	        this.orderDate = orderDate;
	        this.pickUpDate = pickUpDate;
	        this.item = item;
	        this.quantity = quantity;
	    }
	
	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double getTotal() {
		return getQuantity() * getItem().getPrice();
	}
	
	public int getCustomerID() {
	    return customerID;
	}
	
	/**
	 * get order ID
	 * 
	 * @return orderID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Get order Name
	 * 
	 * @return orderName
	 */
	public boolean paid() {
		return paid;
	}

	/**
	 * get the order category
	 * 
	 * @return order category
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * Get the order price
	 * 
	 * @return order price
	 */
	public Date getPickUpDate() {
		return pickUpDate;
	}

	public double getDiscount() {
		return discount;
	}

	/**
	 * Get the order total due after discount
	 * 
	 * @return order price
	 */
	public double getTotalDue() {
		return getTotal() - getDiscount();
	}

	/**
	 * Overriding toString() method
	 */
	public String toString() {
		
		return "[" + orderID + ", " + paid + ", " + orderDate + ", "
				+ pickUpDate + ", " + item + ", " + getTotal() + ", " + discount
				+ ", " + getTotalDue() + " ]";
	}

	/**
	 * Overriding equals method from object
	 */
	public boolean equals(Object o) {
		if (o instanceof Order) {
			Order that = (Order) o;
			return this.getOrderID() == that.getOrderID();
		} else {
			return false;
		}
	}

	/**
	 * @return hashCode for this
	 */
	public int hashCode() {
		return orderID;
	}
}
