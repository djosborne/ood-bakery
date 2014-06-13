package bakery.inventory;
import java.util.ArrayList;

public class ItemInventory extends Inventory {
    /** this is the previous map */
    private Inventory m0;
    
    /** this is the item to be added to the map */
    private Item item0;
    
    /** this is the quantity to be added */
    private int quantity0;
    
    /** Constructor
     * @param m0 the prev map
     * @param item0 this is item to be added
     * @param quantity0 this is the quantity to be added
     */
    ItemInventory(Item item0, int quantity0, Inventory m0) {
        this.item0 = item0;
        this.quantity0 = quantity0;
        this.m0 = m0;
    }
    
    /** ItemInventory a new key and value to a map.
     * @param item
     *             item name
     * @param quantity
     *             quantity of item
     * @return a new map
     */
    public Inventory addToStock(Item item, int quantity) {
        if (this.item0.equals(item)) {
            return new ItemInventory(item, quantity + quantity0, m0);
        }
        else {
            return this.m0.addToStock(item, quantity);
        }
    }
    
    /** Removes item from stock class.
     * @param item
     *             item name
     * @param quantity
     *             quantity of item
     * @return new Inventory
     */
    public Inventory removeFromStock(Item item, int quantity) {
        if (this.item0.equals(item)) {
            return new ItemInventory(item, quantity0 - quantity, m0);
        }
        else {
            return this.m0.removeFromStock(item, quantity);
        }
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
        if (m0.containsItem(item0)) {
            return m0.size();
        }
        else {
            return (1 + m0.size());
        }
    }
    
    /** Checks if contains key.
     * @param item
     *             item name
     * @return whether the map contains item
     */
    public boolean containsItem(Item item) {
        if (item.equals(item0)) {
            return true;
        } 
        else {
            return m0.containsItem(item);
        }
    }
    
    /** Checks if contains value.
     * @param quantity
     *             quantity of item
     * @return whether the map contains value v
     */
    public boolean containsValue(int quantity) {
        for (Item item : this) {
            if (this.getQuantity(item) == quantity) {
                return true;
            }
        }
        return false;
    }

    
    /** Get the value from key.
     * @param item
     *             item name
     * @return the value from the key
     */
    public int getQuantity(Item item) {
        if (item.equals(item0)) {
            return quantity0;
        } 
        else {
            return m0.getQuantity(item);
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
            Inventory m2 = ((Inventory) o);
            if (this.size() == m2.size()) {
                for (Item key : this) {
                    if (!m2.containsItem(key) || 
                       !(this.getQuantity(key) == m2.getQuantity(key))) {
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
        return 2;
    }
    
    /** Get all the keys from Inventory and put them into an array list.
     * @param x
     *             Array List
     * @return An Array list
     */
    public ArrayList getArrayKeys(ArrayList x) {
        if (!m0.containsItem(item0)) {
            x.add(this.item0);
            return (m0.getArrayKeys(x));
        }
        else {
            return (m0.getArrayKeys(x));
        }
    }
}
