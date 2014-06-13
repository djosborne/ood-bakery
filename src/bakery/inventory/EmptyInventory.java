package bakery.inventory;
import java.util.ArrayList;


public class EmptyInventory extends Inventory {
    /** Empty Constructor */
    EmptyInventory() {
        /** Empty Constructor */
    }
    
    /** Creates an ItemInventory class.
     * @param k
     *         Key of type K
     * @param v
     *         Value of type v
     * @return new Inventory
     */
    public Inventory addToStock(Item item, int quantity) {
        return new ItemInventory(item, quantity, this);
    }
    
    /** Removes item from stock class.
     * @param k
     *         Key of type K
     * @param v
     *         Value of type v
     * @return new Inventory
     */
    public Inventory removeFromStock(Item item, int quantity) {
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
    
    /** Whether the map contains k.
     * @param k
     *         Key of type K
     * @return false
     */
    public boolean containsItem(Item item) {
        return false;
    }
    
    /** Whether the map contains v.
     * @param v
     *         Value of type v
     * @return false
     */
    public boolean containsValue(int quantity) {
        return false;
    }
    
    /** Throw exception.
     * @param k
     *             Key of type K
     * @return exception
     */
    public int getQuantity(Item item) {
        throw new RuntimeException("Empty Inventory.");
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
    public ArrayList getArrayKeys(ArrayList x) {
        return x;
    }
}
