package bakery.inventory;
import java.util.ArrayList;

import bakery.Item;

public abstract class Inventory implements Iterable<Item> {
     // Static Methods
    /** Returns a new Empty map.
     * @return new EmptyInventory
     */
    public static Inventory emptyInventory() {
        return new EmptyInventory();
    }
    
    // Dynamic Methods
    
    /** Adds item to inventory
     * @param itemID item ID
     * @param itemName Name of the item
     * @param category Item category
     * @param itemPrice item price
     * @return Inventory
     */
    public abstract Inventory addToStock(int itemID, String itemName, String category, double itemPrice);
    
    public abstract Inventory addToStock(String itemName, String category, double itemPrice);
    /** Adds item to inventory
     * @param item
     *             item name
     * @return Inventory
     */
    public abstract Inventory addToStock(Item item);
    
    /** remove item from inventory.
     * @param item
     *             item name
     * @return Inventory
     */
    public abstract Inventory removeFromStock(Item item);
    
    
    /** Checks if a inventory is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the inventory.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the inventory contains item.
     * @param item
     *             item name
     * @return true or false if it contains the item
     */
    public abstract boolean containsItem(Item item);
    
    public abstract boolean containsItem(Integer ID);
    
    public abstract boolean containsItem(String bakeryItemName, String bakeryItemCategory);
    
    // Dynamic Methods
    /** override toString method from object.
     * @return a string 
     */
//    public String toString() {
//        return ("{...[There are " + this.size() + 
//            " unique key(s) mapped to " + "value(s) in this Inventory]...}");
//    }
    public abstract String toString();
    
    
    public abstract Item getItem(Integer ID);
    
    
    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             Array List x
     * @return Array List
     */
    public abstract ArrayList getArrayKeys(ArrayList<Item> x);

    /** Iterator.
     * @return new MyIterator 
     */
    public MyIterator iterator() {
        return new MyIterator(this.getArrayKeys(new ArrayList<Item>()));
    }
    
	private Integer lastUsedID = 0;
	
	protected Integer getNextAvailableID() {
		while (containsItem(lastUsedID)) {
			lastUsedID++;
		}
		return lastUsedID;
	}

}
