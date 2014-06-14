package bakery;
import bakery.inventory.Inventory;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {
    private int orderID;
    private boolean paid;
    private Date orderDate;
    private Date pickUpDate;
    private Inventory inv;
    private double total;
    private double discount;
    private double totalDue;

    /**
     * Construct a new order with given parameters
     * @param orderID
     *            order ID
     * @param orderName
     *            order name
     * @param category
     *            order category
     * @param order Price
     *             Price for order
     */
    public Order(int orderID, boolean paid, Date pickUpDate, Inventory inv, double total, double discount) {
        this.orderID = orderID;
        this.paid = paid;
        this.orderDate = orderDate;
        this.pickUpDate = pickUpDate;
        this.inv = inv;
        this.total = total;
        this.discount = discount;
        this.totalDue = total - discount;
    }

    /**
     * get order ID
     * @return orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Get order Name
     * @return orderName
     */
    public boolean paid() {
        return paid;
    }

    /** get the order category
     * @return order category
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /** Get the order price
     * @return order price
     */
    public Date getPickUpDate() {
        return pickUpDate;
    }
    
    /** Get inventory
     * @return inventory inv
     */
    public Inventory getInv() {
        return inv;
    }
    
    /** Get the order total
     * @return order total
     */
    public double getTotal() {
        return total;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    /** Get the order total due after discount
     * @return order price
     */
    public double getTotalDue() {
        return totalDue;
    }
    
    /**
     * Overriding toString() method 
     */
    public String toString() {
        return "[" + orderID + ", " + paid + ", " + orderDate + ", " + pickUpDate + 
            ", " + inv + ", " + total + ", " + discount + ", " + totalDue +" ]";
    }

    /**
     * Overriding equals method from object
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
