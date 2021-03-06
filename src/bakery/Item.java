package bakery;

/** Item class
 * @author Jesus Cheng
 * @author Dan Osborne
 * @version 1.0
 */
public class Item {
    private Integer itemID;
    private String itemName;
    private String category;
    private double itemPrice;

    /**
     * Construct a new item with given parameters
     * @param itemID
     *            item ID
     * @param itemName
     *            item name
     * @param category
     *            item category
     * @param itemPrice
     *             Price for item
     */
    public Item(int itemID, String itemName, 
        String category, double itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.itemPrice = itemPrice;
    }

    /**
     * get item ID
     * @return itemID
     */
    public Integer getItemID() {
        return itemID;
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
     * @return string
     */
    public String toString() {
        return "[" + itemID + ", " + itemName + ", "
                + category + ", " + itemPrice + "]";
    }

    /**
     * Overriding equals method from object
     * @param o object to be compared
     * @return true or false is equal
     */
    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item that = (Item) o;
            return this.getItemID() == that.getItemID();
        }
        else {
            return false;
        }
    }

    /**
     * @return hashCode for this
     */
    public int hashCode() {
        return itemID;
    }
}
