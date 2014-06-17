package bakery.inventory;
import java.util.ArrayList;

import bakery.Item;


public class EmptyInventory extends Inventory {
    /** Empty Constructor */
    EmptyInventory() {
        /** Empty Constructor */
    }
    
    public Inventory addToStock(int itemID, String itemName, String category, double itemPrice) {
        Item it = new Item(itemID, itemName, category, itemPrice);
        return new ItemInventory(it, this);
    }
    
    public Inventory addToStock(String itemName, String category, double itemPrice) {
        Item it = new Item(getNextAvailableID(), itemName, category, itemPrice);
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
    
    public Inventory removeFromStock(Integer itemID) {
    	return this;
    }
    
    /** See whether a map is empty
     * @return true 
     */
    public boolean isEmpty() {
        return true;
    }
    
    /** Returns the size of the map.
     * @return size of empty map 0 
     */
    public int size() {
        return 0;
    }
    
    /** Whether the map contains item.
     * @param item
     *         item in inventory
     * @return false
     */
    public boolean containsItem(Item item) {
        return false;
    }
    
    public boolean containsItem(Integer ID) {
    	return false;
    }
    
	public Item getItem(Integer ID) {
		throw new RuntimeException("No item with such ID exists");
	}
    
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    @SuppressWarnings("unchecked")
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
     * @return Array list with ks
     */
    public ArrayList getArrayKeys(ArrayList<Item> x) {
        return x;
    }
    
    public String toString() {
        return "";
    }

	public boolean containsItem(String bakeryItemName, String bakeryItemCategory) {
		return false;
	}


}
