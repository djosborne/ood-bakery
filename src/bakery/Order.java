package bakery;

import bakery.inventory.Inventory;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {
    private Integer customerID;
	private Integer orderID;
	private boolean paid;
	private Date orderDate;
	private Date pickUpDate;
	private Item item;
	private Integer quantity;
	private double loyaltyAtTimeOfOrder;
	private double discountUsedOnOrder;
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
	public Order(Integer orderID, int itemID, Integer quantity, Integer customerID, double loyaltyAtTimeOfOrder,  double discountUsedOnOrder, boolean paid, Date pickUpDate) {
		this.orderID = orderID;
	    this.customerID = customerID;
	    this.orderID = orderID; // TODO: generate
		this.paid = paid;
		this.orderDate = new Date();
		this.pickUpDate = pickUpDate;
		this.item = item;
		this.quantity = quantity;
		this.discountUsedOnOrder = discountUsedOnOrder;
		this.loyaltyAtTimeOfOrder = loyaltyAtTimeOfOrder;
	}
	
	   public Order(Integer orderID, Item item, Integer quantity, int customerID, double loyaltyAtTimeOfOrder, double discountUsedOnOrder, boolean paid, Date orderDate, Date pickUpDate) {
	       this.orderID = orderID;
	       this.customerID = customerID; 
	       this.orderID = orderID; // TODO: generate
	        this.paid = paid;
	        this.orderDate = orderDate;
	        this.pickUpDate = pickUpDate;
	        this.item = item;
	        this.quantity = quantity;
	        this.discountUsedOnOrder = discountUsedOnOrder;
	        this.loyaltyAtTimeOfOrder = loyaltyAtTimeOfOrder;
	    }
	   
	   public double getLoyaltyAtTimeOfOrder() {
	       return loyaltyAtTimeOfOrder;
	   }
	   public double getDiscountUsedOnOrder() {
	       return discountUsedOnOrder;
	   }
	
	public Item getItem() {
		return item;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public double getTotal() {
		return getQuantity() * getItem().getPrice();
	}
	
	public Integer getCustomerID() {
	    return customerID;
	}
	
	/**
	 * get order ID
	 * 
	 * @return orderID
	 */
	public Integer getOrderID() {
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

	/**
	 * Get the order total due after discount
	 * 
	 * @return order price
	 */
	public double getTotalDue() {
		return getTotal() - getDiscountUsedOnOrder();
	}

	/**
	 * Overriding toString() method
	 */
	public String toString() {
		
		return "[" + orderID + ", " + paid + ", " + orderDate + ", "
				+ pickUpDate + ", " + item + ", " + getTotal() + ", " + discountUsedOnOrder
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
