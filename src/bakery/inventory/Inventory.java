package bakery.inventory;
import java.util.ArrayList;

public abstract class Inventory implements Iterable<Item> {
     // Static Methods
    /** Returns a new Empty map.
     * @return new EmptyInventory
     */
    public static Inventory emptyMap() {
        return new EmptyInventory();
    }
    
    // Dynamic Methods
    
    /** Adds key and value to a map.
     * @param item
     *             item name
     * @param quantity
     *             quantity of item
     * @return Inventory
     */
    public abstract Inventory addToStock(Item item, int quantity);

    /** Adds key and value to a map.
     * @param item
     *             item name
     * @param quantity
     *             quantity of item
     * @return Inventory
     */
    public abstract Inventory removeFromStock(Item item, int quantity);
    
    
    /** Checks if a map is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the map.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the map contains item.
     * @param item
     *             item name
     * @return true or false if it contains the item
     */
    public abstract boolean containsItem(Item item);
    
    /** Check if the map contains value.
     * @param quantity
     *             quantity of item
     * @return true or false if it contains 
     */
    public abstract boolean containsValue(int quantity);
    
    /** gets item from quantity
     * @param item
     *             item name
     * @return value in key
     */
    public abstract int getQuantity(Item item);
    
    
    // Dynamic Methods
    /** override toString method from object.
     * @return a string 
     */
    public String toString() {
        return ("{...[There are " + this.size() + 
            " unique key(s) mapped to " + "value(s) in this Inventory]...}");
    }
    
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
}
