package bakery.inventory;
import java.util.ArrayList;

public class ItemInventory<K, V> extends Inventory<K, V> {
    /** this is the previous map */
    private Inventory<K, V> m0;
    
    /** this is the key to be added to the map */
    private K k0;
    
    /** this is the value to be added */
    private V v0;
    
    /** Constructor
     * @param m0 the prev map
     * @param k0 this is key to be added
     * @param v0 this is the value to be added
     */
    ItemInventory(K k0, V v0, Inventory<K, V> m0) {
        this.k0 = k0;
        this.v0 = v0;
        this.m0 = m0;
    }
    
    /** ItemInventory a new key and value to a map.
     * @param k
     *             Key of type K
     * @param v 
     *             Value of type v
     * @return a new map
     */
    public Inventory<K, V> addToStock(K k, V v) {
        return new ItemInventory<K, V>(k, v, this);
    }
    
    /** Checks if map is empty.
     * @return whether a map is empty
     */
    public boolean isEmpty() {
        return false;
    }
    
    /** Returns the size of the map
     * @return the size of map
     */
    public int size() {
        if (m0.containsKey(k0)) {
            return m0.size();
        }
        else {
            return (1 + m0.size());
        }
    }
    
    /** Checks if contains key.
     * @param k
     *             Key of type K
     * @return whether the map contains key k
     */
    public boolean containsKey(K k) {
        if (k.equals(k0)) {
            return true;
        } 
        else {
            return m0.containsKey(k);
        }
    }
    
    /** Checks if contains value.
     * @param v
     *             Value of type v
     * @return whether the map contains value v
     */
    public boolean containsValue(V v) {
        for (K k : this) {
            if (this.getQuantity(k).equals(v)) {
                return true;
            }
        }
        return false;
    }

    
    /** Get the value from key.
     * @param k
     *             get Value from K
     * @return the value from the key
     */
    public V getQuantity(K k) {
        if (k.equals(k0)) {
            return v0;
        } 
        else {
            return m0.getQuantity(k);
        }
    }    
    
    /** Equal Operator.
     * @return boolean whether they are equal or not
     * @param o 
     *             is the object that we want to compare
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof Inventory) {
            Inventory<K, V> m2 = ((Inventory<K, V>) o);
            if (this.size() == m2.size()) {
                for (K key : this) {
                    if (!m2.containsKey(key) || 
                        !this.getQuantity(key).equals(m2.getQuantity(key))) {
                        return false;
                    }
                } 
                return true;
            }    
        }
        return false;
    }
    
    /** This returns the hashcode.
     * @return hashcode for map
     */
    public int hashCode() {
        ArrayList<K> a = this.getArrayKeys(new ArrayList<K>());
        int x = 0;
        for (K k : a) {
            x = x + k.hashCode() * 3 + 11;
        }
        return this.size() * 13 * x + 47;
    }
    
    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             Array List
     * @return An Array list
     */
    public ArrayList<K> getArrayKeys(ArrayList<K> x) {
        if (!m0.containsKey(k0)) {
            x.add(this.k0);
            return (m0.getArrayKeys(x));
        }
        else {
            return (m0.getArrayKeys(x));
        }
    }
}
