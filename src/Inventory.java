import java.util.ArrayList;

public abstract class Inventory<K, V> implements Iterable<K> {
    /** Adds key and value to the tree and make the root black
     * @param k
     *             Key of type K
     * @param v
     *             Value of type V
     * @return KVTree
     */
    public Inventory<K, V> addToStock(K k, V v) {
        return this.addToStock2(k, v).makeBlack();
    }
    
    /** Checks is tree is empty.
     * @return true or false map is empty
     */
    public abstract boolean isEmpty();
    
    /** Returns the size of the tree.
     * @return size of map
     */
    public abstract int size();
    
    /** Checks if the tree contains key.
     * @param k
     *             Key of type K
     * @return true or false if it contains k
     */
    public abstract boolean containsKey(K k);
    
    /** Checks if the tree contains value.
     * @param v
     *             Value of type V
     * @return true or false if it contains v
     */
    public abstract boolean containsValue(V v);
    
    /** Gets value from key.
     * @param k
     *             Key of type K
     * @return value in key
     */
    public abstract V get(K k);
    
    // Dynamic Methods
    /** Returns to String.
     * @return a string 
     */
    public abstract String toString();
    
    /** Balance the tree that does not satisfy RB tree invariants.
     * @return balanced tree
     */
    protected abstract Inventory<K, V> balance();

    /** Checks if node is red.
     * @return whether the node is red
     */
    protected abstract boolean isRed();
    
    /** Get left node.
     * @return the left node
     */
    protected abstract Inventory<K, V> getL();
    
    /** Get right node.
     * @return the right node
     */
    protected abstract Inventory<K, V> getR();
    
    /** Get key from node.
     * @return the key from the node
     */
    protected abstract K getKey();
    
    /** Get value from node.
     * @return the value from the node
     */
    protected abstract V getValue();
    
    /** Make the root black
     * @return make the root of the tree black
     */
    protected abstract Inventory<K, V> makeBlack();
    
    /** Adds key and value to the tree.
     * @param k
     *             Key of type K
     * @param v
     *             Value of type V
     * @return KVTree
     */
    protected abstract Inventory<K, V> addToStock2(K k, V v);
}
