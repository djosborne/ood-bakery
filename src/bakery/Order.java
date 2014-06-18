package bakery;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Order {
    private Integer customerID;
	private Integer orderID;
	private boolean paid;
	private Date orderDate;
	private Date pickUpDate;
	private Item item;
	private Integer quantity;
	private double loyaltyAtTimeOfOrder;
	private double availableDiscount;
	private double discountUsedOnOrder;
    private double total;
	
	   public Order(Integer orderID, Item item, double total, Integer quantity, int customerID, double loyaltyAtTimeOfOrder, double  availableDiscount, double discountUsedOnOrder, boolean paid, Date orderDate, Date pickUpDate) {
	       this.orderID = orderID;
	       this.customerID = customerID; 
	       this.orderID = orderID; // TODO: generate
	       this.paid = paid;
	       this.orderDate = orderDate;
	       this.pickUpDate = pickUpDate;
	       this.item = item;
	       this.quantity = quantity;
	       this.availableDiscount = availableDiscount;
	       this.discountUsedOnOrder = discountUsedOnOrder;
	        
	       this.loyaltyAtTimeOfOrder = loyaltyAtTimeOfOrder;
	       this.total = total;
	        
	    }
	   
	   public double getLoyaltyAtTimeOfOrder() {
	       return loyaltyAtTimeOfOrder;
	   }
	   public double getDiscountUsedOnOrder() {
	       return discountUsedOnOrder;
	   }
	   
	   public double getAvailableDiscount() {
	       return availableDiscount;
	   }
	
	public Item getItem() {
		return item;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public double getTotal() {
		return total;
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
	    
	    SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");
	    String sOrderDate = dFormatter.format(getOrderDate());
	    String sPickupDate = dFormatter.format(getPickUpDate());
	    
		return "[" + orderID + ", " + paid + ", " + sOrderDate + ", "
				+ sPickupDate + ", " + item + ", " + getTotal() + ", " + discountUsedOnOrder
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
