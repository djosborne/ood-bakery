package bakery.inventory;

import java.util.ArrayList;

import bakery.Item;

/** Item Inventory Class.
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public class ItemInventory extends Inventory {
    /** this is the previous inventory. */
    private Inventory m0;

    /** this is the item to be added to the inventory. */
    private Item item0;

    /** Constructor.
     * @param m0
     *            the prev inventory
     * @param item0
     *            this is item to be added
     */
    ItemInventory(Item item0, Inventory m0) {
        this.item0 = item0;
        this.m0 = m0;
    }

    /** returns the rest of the inventory.
     * @return return rest of the inventory
     */
    Inventory getRest() {
        return m0;
    }

    /**
     * Adds item to inventory.
     * 
     * @param itemID
     *            item ID
     * @param itemName
     *            Name of the item
     * @param category
     *            Item category
     * @param itemPrice
     *            item price
     * @return Inventory
     */
    public Inventory addToStock(int itemID, String itemName, String category,
            double itemPrice) {
        Item item = new Item(itemID, itemName, category, itemPrice);
        return this.addToStock(item);
    }

    /** Add item to the inventory.
     * @param itemName This is the item Name
     * @param category This is the item category
     * @param itemPrice This is the item Prices
     * @return new Inventory with added item
     */
    public Inventory addToStock(String itemName, String category,
            double itemPrice) {
        Item item = new Item(getNextAvailableID(), itemName, category,
                itemPrice);
        return this.addToStock(item);
    }

    /**
     * Adds item to inventory.
     * 
     * @param item
     *            item name
     * @return Inventory
     */
    public Inventory addToStock(Item item) {
        if (this.item0.equals(item)
                || this.item0.getItemID().equals(item.getItemID())) {
            throw new RuntimeException("That item already exists!");
        } 
        else {
            return new ItemInventory(getItem(), getRest().addToStock(item));
        }
    }

    /** remove item from inventory.
     * @param itemID
     *             item ID
     * @return Inventory
     */
    public Inventory removeFromStock(Integer itemID) {
        if (getItem().getItemID().equals(itemID)) {
            return getRest().removeFromStock(itemID);
        } 
        else {
            return new ItemInventory(getItem(), 
                getRest().removeFromStock(itemID));
        }
    }

    /**
     * Removes item from stock class.
     * 
     * @param item
     *            item name
     * @return new Inventory
     */
    public Inventory removeFromStock(Item item) {
        if (this.item0.equals(item)) {
            return new ItemInventory(item, m0);
        } 
        else {
            return this.m0.removeFromStock(item);
        }
    }

    /**
     * Checks if inventory is empty.
     * 
     * @return whether a map is empty
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the size of the inventory.
     * 
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

    /**
     * Checks if contains item.
     * 
     * @param item
     *            item name
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

    /** Checks if the inventory contains item.
     * @param iD
     *             item ID
     * @return true or false if it contains the item
     */
    public boolean containsItem(Integer iD) {
        if (item0.getItemID().equals(iD)) {
            return true;
        } 
        else {
            return m0.containsItem(iD);
        }
    }

    /**
     * Get the value from item.
     * @return the value from the key
     */
    private Item getItem() {
        return this.item0;
    }

    /** Get Item from inventory using the item ID
     * @param iD
     *         item ID in inventory
     * @return an exception because it is empty
     */
    public Item getItem(int iD) {
        if (getItem().getItemID() == iD) {
            return getItem();
        } 
        else {
            return getRest().getItem(iD);
        }
    }

    /**
     * Equal Operator.
     * 
     * @return boolean whether they are equal or not
     * @param o
     *            is the object that we want to compare
     */
    public boolean equals(Object o) {
        if (o instanceof Inventory) {
            Inventory m2 = ((Inventory) o);
            if (this.size() == m2.size()) {
                for (Item key : this) {
                    if (!m2.containsItem(key)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * This returns the hashcode.
     * 
     * @return hashcode for map
     */
    public int hashCode() {
        return 2;
    }

    /**
     * Get all the keys from Inventory and put them into an array list.
     * 
     * @param x
     *            Array List
     * @return An Array list
     */
    public ArrayList<Item> getArrayKeys(ArrayList<Item> x) {
        x.add(getItem());    
        return getRest().getArrayKeys(x);
    }

    /** Overriding to string method.
     * @return the list of items
     */
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } 
        else {
            return this.getItem().toString() + "\n" + m0.toString();
        }
    }

    /** Get Item from inventory using the item ID.
     * @param iD
     *         item ID in inventory
     * @return get item with item ID
     */
    public Item getItem(Integer iD) {
        if (getItem().getItemID().equals(iD)) {
            return getItem();
        } 
        else {
            return getRest().getItem(iD);
        }
    }
    
    /** Get price using item ID.
     * @param itemID this is the item ID
     * @return the price of the item
     */
    public double getPrice(Integer itemID) {
        if (getItem().getItemID().equals(itemID)) {
            return getItem().getPrice();
        }
        else {
            return getRest().getPrice(itemID);
        }
    }
    
    /** Checks if the inventory contains item.
     * @param bakeryItemName is the item name
     * @param bakeryItemCategory is the category of the item
     * @return true or false if it contains the item
     */
    public boolean containsItem(String bakeryItemName, 
        String bakeryItemCategory) {
        if (item0.getItemName().equals(bakeryItemName)
                && item0.getCategory().equals(bakeryItemCategory)) {
            return true;
        } 
        else {
            return m0.containsItem(bakeryItemName, bakeryItemCategory);
        }
    }

}
