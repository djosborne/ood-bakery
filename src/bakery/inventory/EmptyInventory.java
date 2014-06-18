package bakery.inventory;
import java.util.ArrayList;

import bakery.Item;

/** Empty Inventory Class
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public class EmptyInventory extends Inventory {
    /** Empty Constructor */
    EmptyInventory() {
        /** Empty Constructor */
    }
    
    /** Add item to the inventory
     * @param itemID This is the item ID
     * @param itemName This is the item Name
     * @param category This is the item category
     * @param itemPrice This is the item Prices
     * @return new Inventory with added item
     */
    public Inventory addToStock(int itemID, String itemName, 
        String category, double itemPrice) {
        Item it = new Item(itemID, itemName, category, itemPrice);
        return new ItemInventory(it, this);
    }
    
    /** Add item to the inventory.
     * @param itemName This is the item Name
     * @param category This is the item category
     * @param itemPrice This is the item Prices
     * @return new Inventory with added item
     */
    public Inventory addToStock(String itemName, 
        String category, double itemPrice) {
        Item it = new Item(getNextAvailableID(), itemName, 
            category, itemPrice);
        return new ItemInventory(it, this);
    }
    
    /** Creates an ItemInventory class.
     * @param item
     *         item in inventory
     * @return new Inventory
     */
    public Inventory addToStock(Item item) {
        return new ItemInventory(item, this);
    }
    
    /** Removes item from stock class.
     * @param item
     *         item in inventory
     * @return new Inventory
     */
    public Inventory removeFromStock(Item item) {
        return this;
    }
    
    /** Removes item from stock class.
     * @param itemID
     *         item ID in inventory
     * @return new Inventory
     */
    public Inventory removeFromStock(Integer itemID) {
        return this;
    }
    
    /** See whether a inventory is empty.
     * @return true 
     */
    public boolean isEmpty() {
        return true;
    }
    
    /** Returns the size of the inventory.
     * @return size of empty map 0 
     */
    public int size() {
        return 0;
    }
    
    /** Whether the inventory contains item.
     * @param item
     *         item in inventory
     * @return false
     */
    public boolean containsItem(Item item) {
        return false;
    }
    
    /** Whether the inventory contains item.
     * @param iD
     *         item ID in inventory
     * @return false
     */
    public boolean containsItem(Integer iD) {
        return false;
    }
    
    /** Get Item from inventory using the item ID.
     * @param iD
     *         item ID in inventory
     * @return an exception because it is empty
     */
    public Item getItem(Integer iD) {
        throw new RuntimeException("No item with such ID exists");
    }
    
    /** Get price using item ID.
     * @param itemID this is the item ID
     * @return the price of the item
     */
    public double getPrice(Integer itemID) {
        throw new RuntimeException("No item with such ID exists");
    }
    
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    public boolean equals(Object o) {
        if (o instanceof Inventory) {
            Inventory m1 = (Inventory) o;
            return m1.isEmpty();
        }
        return false;
    }
    
    /** returns the hashcode of the map.
     * @return HashCode for empty map
     */
    public int hashCode() { 
        return 1;
    }

    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             An array list
     * @return Array list with items
     */
    public ArrayList<Item> getArrayKeys(ArrayList<Item> x) {
        return x;
    }
    
    /**
     * @return returns nothing because it is empty.
     */
    public String toString() {
        return "";
    }

    /** Checks if the inventory contains item.
     * @param bakeryItemName is the item name
     * @param bakeryItemCategory is the category of the item
     * @return true or false if it contains the item
     */
    public boolean containsItem(String bakeryItemName, 
        String bakeryItemCategory) {
        return false;
    }
}
