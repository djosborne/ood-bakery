package bakery.inventory;
import java.util.ArrayList;

public abstract class Inventory<K, V> implements Iterable<K> {
	 // Static Methods
    /** Returns a new Empty map.
     * @param <K> 
     *             Key of type K
     * @param <V>
     *             Value of type V
     * @return new EmptyInventory
     */
    public static <K, V> Inventory<K, V> emptyMap() {
        return new EmptyInventory<K, V>();
    }
    
    // Dynamic Methods
    
    /** Adds key and value to a map.
     * @param k
     *             Key of type K
     * @param v
     *             Value of type V
     * @return Inventory
     */
    public abstract Inventory<K, V> addToStock(K k, V v);

    /** Adds key and value to a map.
     * @param k
     *             Key of type K
     * @param v
     *             Value of type V
     * @return Inventory
     */
    public abstract Inventory<K, V> RemoveFromStock(K k, V v);
    
    
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
    public abstract boolean containsKey(K k);
    
    /** Check if the map contains value.
     * @param v
     *             Value of type V
     * @return true or false if it contains v
     */
    public abstract boolean containsValue(V v);
    
    /** gets value from key.
     * @param k
     *             Key of type K
     * @return value in key
     */
    public abstract V getQuantity(K k);
    
    
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
    public abstract ArrayList<K> getArrayKeys(ArrayList<K> x);

    /** Iterator.
     * @return new MyIterator 
     */
    public MyIterator<K> iterator() {
        return new MyIterator<K>(this.getArrayKeys(new ArrayList<K>()));
    }
}
