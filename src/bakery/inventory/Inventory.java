package bakery.inventory;
import java.util.ArrayList;

import bakery.Item;

/** Inventory Class
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 * @param <Item> the inventory takes an Item object
 */
public abstract class Inventory implements Iterable<Item> {
     // Static Methods
    /** Returns a new Empty inventory.
     * @return new EmptyInventory
     */
    public static Inventory emptyInventory() {
        return new EmptyInventory();
    }
    
    // Dynamic Methods
    
    /** Adds item to inventory.
     * @param itemID item ID
     * @param itemName Name of the item
     * @param category Item category
     * @param itemPrice item price
     * @return Inventory
     */
    public abstract Inventory addToStock(int itemID, 
        String itemName, String category, double itemPrice);
    
    /** Add to stock using item name, category and price.
     * @param itemName this is the item name
     * @param category this is the item category
     * @param itemPrice this is the item price
     * @return a new inventory
     */
    public abstract Inventory addToStock(String itemName, 
        String category, double itemPrice);
    
    /** Adds item to inventory.
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
    
    /** remove item from inventory.
     * @param itemID
     *             item ID
     * @return Inventory
     */
    public abstract Inventory removeFromStock(Integer itemID);
    
    /** Checks if a inventory is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the inventory.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the inventory contains item.
     * @param item name
     *             item name
     * @return true or false if it contains the item
     */
    public abstract boolean containsItem(Item item);
    
    /** Checks if the inventory contains item.
     * @param ID
     *             item ID
     * @return true or false if it contains the item
     */
    public abstract boolean containsItem(Integer ID);
    
    /** Checks if the inventory contains item.
     * @param bakeryItemName is the item name
     * @param bakeryItemCategory is the category of the item
     * @return true or false if it contains the item
     */
    public abstract boolean containsItem(String bakeryItemName, 
        String bakeryItemCategory);
    
    /** override toString method from object.
     * @return a string 
     */
    public abstract String toString();
    
    /** get the item in the inventory using the item ID.
     * @param ID is the item ID
     * @return an item from its ID
     */
    public abstract Item getItem(Integer ID);
    
    /** Get price using item ID.
     * @param itemID this is the item ID
     * @return the price of the item
     */
    public abstract double getPrice(Integer itemID);
    
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
    
    private Integer lastUsedID = 1;
    
    /** Get an available ID number.
     * @return available ID
     */
    protected Integer getNextAvailableID() {
        while (containsItem(lastUsedID)) {
            lastUsedID++;
        }
        return lastUsedID;
    }

}
