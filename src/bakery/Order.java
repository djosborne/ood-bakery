package bakery;

import java.util.Date;

import java.text.SimpleDateFormat;
/** Order class
 * @author Jesus Cheng
 * @author Dan Osborne
 * @version 1.0
 */
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
    
    /**
     * Order Class
     * @param orderID order ID
     * @param item item name
     * @param total total
     * @param quantity quantity of itema
     * @param customerID customer ID
     * @param loyaltyAtTimeOfOrder loyalty At Time Of Order
     * @param availableDiscount available discunt
     * @param discountUsedOnOrder discount Used On Order
     * @param paid is it paid?
     * @param orderDate order date
     * @param pickUpDate pick up date
     */
    public Order(Integer orderID, Item item, double total,
        Integer quantity, int customerID, double loyaltyAtTimeOfOrder, 
            double  availableDiscount, double discountUsedOnOrder, 
                boolean paid, Date orderDate, Date pickUpDate) {
        this.orderID = orderID;
        this.customerID = customerID; 
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
    
    /** get Loyalty At Time Of Order
     * @return LoyaltyAtTimeOfOrder
     */
    public double getLoyaltyAtTimeOfOrder() {
        return loyaltyAtTimeOfOrder;
    }
    
    /**
     * discount used
     * @return discountUsedOnOrder
     */
    public double getDiscountUsedOnOrder() {
        return discountUsedOnOrder;
    }
    
    /**
     * get available discount
     * @return availableDiscount
     */
    public double getAvailableDiscount() {
        return availableDiscount;
    }
    
    /**
     * Get item
     * @return item
     */
    public Item getItem() {
        return item;
    }

    /**
     * get quantity
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }
    
    /**
     * get total
     * @return total
     */
    public double getTotal() {
        return total;
    }
    
    /**
     * get customer ID
     * @return customerID
     */
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
     * @return string
     */
    public String toString() {
        
        SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");
        String sOrderDate = dFormatter.format(getOrderDate());
        String sPickupDate = dFormatter.format(getPickUpDate());
        
        return "[" + orderID + ", " + paid + ", " + sOrderDate + ", "
                + sPickupDate + ", " + item + ", " 
                    + getTotal() + ", " + discountUsedOnOrder
                        + ", " + getTotalDue() + " ]";
    }

    /**
     * Overriding equals method from object
     * @param o object to compare
     * @return true or false is equal
     */
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order that = (Order) o;
            return this.getOrderID() == that.getOrderID();
        } 
        else {
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
