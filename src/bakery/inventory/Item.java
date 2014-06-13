package bakery.inventory;

public class Item {
    private int itemID;
    private String itemName;
    private String category;
    private int itemPrice;

    /**
     * Construct a new default 
     */
    public Item() {
        itemID = 0;
        itemName = "Bread";
        category = "Bread";
        itemPrice = 1;
    }

    /**
     * Construct a new item with given parameters
     * 
     * @param itemID
     *            item ID
     * @param itemName
     *            item name
     * @param category
     *            item category
     * @param item Price
     *                 Price for item
     */
    public Item(int itemID, String itemName, String category, int itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.itemPrice = itemPrice;
    }

    /**
     * Accessor method for itemID
     * 
     * @return itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Accessor method for itemName
     * 
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
    public int getPrice() {
        return itemPrice;
    }
    
    /**
     * Overriding toString() method 
     */
    public String toString() {
        return "[" + itemID + ": " + itemName + " "
                + category + "]";
    }

    /**
     * Overriding equals method from object
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
