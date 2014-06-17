package bakery.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

// MyIterator
/**
 * @author Jesus Cheng
 * CS3500
 * @version 1.0
 * @param <K> 
 *             Key of type K
 */
public class MyIterator<K> implements Iterator<K> {
    /** Take the K list from map and make it into an array list. 
     * @param <K>
     *             Key of type K
     */
    private ArrayList<K> k;
    
    /** Index of array list. */
    private int n = 0; 
    
    /** Constructor. 
     * @param c 
     *             parameter for constructor
     * @param k
     *             type k
     */
    MyIterator(ArrayList<K> k, java.util.Comparator<? super K> c) {
        this.k = k;
        Collections.sort(k, c);
    }
    
    /** Constructor.
     * @param k
     *             Key of type K 
     */
    MyIterator(ArrayList<K> k) {
        this.k = k;
    }
    
    /** Checks if there is any element next to current.
     * @return whether the array list has more elements next
     */
    public boolean hasNext() {
        return (k.size() > n);
    }
    
    /** Goes to the next value.
     * @return the next elements in the iteration
     */    
    public K next() {
        if (this.hasNext()) {
            this.n++;
            return (k.get(n - 1));
        } 
        else {
            throw new NoSuchElementException(
                "No element next");
        }
    }
    
    /**
     * It remove the element but will throw an exception.
     */
    public void remove() {
        throw new UnsupportedOperationException(
            "Cannot remove");
    }
}