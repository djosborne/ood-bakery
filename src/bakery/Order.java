package bakery;

public class Order {
    private int orderID;
    private boolean paid;
    private String orderDate;
    private String pickUpDate;
    
    private String itemName;
    private String category;
    private double itemPrice;

    /**
     * Construct a new item with given parameters
     * @param orderID
     *            item ID
     * @param itemName
     *            item name
     * @param category
     *            item category
     * @param item Price
     *             Price for item
     */
    public Order(int orderID, String itemName, String category, double itemPrice) {
        this.orderID = orderID;
        this.itemName = itemName;
        this.category = category;
        this.itemPrice = itemPrice;
    }

    /**
     * get item ID
     * @return orderID
     */
    public int getorderID() {
        return orderID;
    }

    /**
     * Get item Name
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /** get the item category
     * @return item category
     */
    public String getCategory() {
        return category;
    }

    /** Get the item price
     * @return item price
     */
    public double getPrice() {
        return itemPrice;
    }
    
    /**
     * Overriding toString() method 
     */
    public String toString() {
        return "[" + orderID + ", " + itemName + ", "
                + category + ", " + itemPrice + "]";
    }

//    /**
//     * Overriding equals method from object
//     */
//    public boolean equals(Object o) {
//        if (o instanceof Item) {
//            Item that = (Item) o;
//            return this.getorderID() == that.getorderID();
//        }
//        else {
//            return false;
//        }
//    }

    /**
     * @return hashCode for this
     */
    public int hashCode() {
        return orderID;
    }
}
