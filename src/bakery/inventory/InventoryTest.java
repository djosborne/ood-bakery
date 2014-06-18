package bakery.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bakery.Item;

public class InventoryTest {
    private final String apple = "Apple";
    private final String pear = "Pear";
    private final String peach = "Peach";
    private final String orange = "Orange";
    private final String melon = "Melon";
    private final String fruit = "Fruit";
    
    private Item item1 = new Item(1, apple, fruit, 1);
    private Item item2 = new Item(2, pear, fruit, 2);
    private Item item3 = new Item(3, melon, fruit, 3);
    private Item item4 = new Item(4, orange, fruit, 4);
    
    private Inventory inv0;
    private Inventory inv1;
    private Inventory inv2;

    
    @Before
    public void setUp() {
        inv0 = Inventory.emptyInventory();
        inv1 = Inventory.emptyInventory();
        inv1 = inv1.addToStock(item1).addToStock(item2).
            addToStock(item3).addToStock(item4);
        inv2 = inv0.addToStock(item1).addToStock(item2);
    }
    
    @Test
    public void testPrint() {
        System.out.println(inv1.size());
        System.out.println(inv1.getPrice(1));
        System.out.println(inv1.toString());
    }
    
    @Test
    public void testSize() {
        assertSame(inv0.size(), 0);
        assertSame(inv1.size(), 4);
        assertSame(inv2.size(), 2);
        assertTrue(inv0.isEmpty());
        assertFalse(inv1.isEmpty());
    }

    @Test
    public void testContainsItem() {
        assertFalse(inv0.containsItem(item1));
        assertTrue(inv1.containsItem(item2));
        assertTrue(inv2.containsItem(item2));
        assertTrue(inv2.containsItem(2));
        assertTrue(inv2.containsItem(pear, fruit));
    }

    @Test
    public void testGetItem() {
    	assertEquals(inv1.getItem(1), item1);
    	assertEquals(inv1.getItem(2), item2);
    	assertEquals(inv1.getItem(3), item3);
    }
}
