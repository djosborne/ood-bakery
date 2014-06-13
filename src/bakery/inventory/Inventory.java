package bakery.inventory;
import java.util.ArrayList;

public abstract class Inventory implements Iterable<String> {
	 // Static Methods
    /** Returns a new Empty map.
     * @param <K> 
     *             Key of type K
     * @param <V>
     *             Value of type V
     * @return new EmptyInventory
     */
    public static Inventory emptyMap() {
        return new EmptyInventory();
    }
    
    // Dynamic Methods
    
    /** Adds key and value to a map.
     * @param k
     *             Key of type K
     * @param v
     *             Value of type V
     * @return Inventory
     */
    public abstract Inventory addToStock(String item, int quantity);

    /** Adds key and value to a map.
     * @param k
     *             Key of type K
     * @param v
     *             Value of type V
     * @return Inventory
     */
    public abstract Inventory removeFromStock(String item, int quantity);
    
    
    /** Checks if a map is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the map.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the map contains key.
     * @param k
     *             Key of type K
     * @return true or false if it contains k
     */
    public abstract boolean containsKey(String item);
    
    /** Check if the map contains value.
     * @param v
     *             Value of type V
     * @return true or false if it contains v
     */
    public abstract boolean containsValue(int quantity);
    
    /** gets value from key.
     * @param k
     *             Key of type K
     * @return value in key
     */
    public abstract int getQuantity(String item);
    
    
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
    public abstract ArrayList getArrayKeys(ArrayList<String> x);

    /** Iterator.
     * @return new MyIterator 
     */
    public MyIterator iterator() {
        return new MyIterator(this.getArrayKeys(new ArrayList<String>()));
    }
}
