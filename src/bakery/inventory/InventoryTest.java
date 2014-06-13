package bakery.inventory;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
    private final String apple = "Apple";
    private final String pear = "Pear";
    private final String peach = "Peach";
    private final String orange = "Orange";
    private final String melon = "Melon";
    private final String fruit = "Fruit";
    
	private Item item1 = new Item(1, apple, fruit, 5);
	private Item item2 = new Item(2, pear, fruit, 2);
	private Item item3 = new Item(3, melon, fruit, 3);
	private Item item4 = new Item(4, orange, fruit, 7);
	
	private Inventory inv1;
	
	@Before
	public void setUp() {
		inv1 = Inventory.emptyInventory();
		inv1 = inv1.addToStock(item1).addToStock(item2).addToStock(item3);
	}

	@Test
	public void test() {
		//assertSame(inv1.size(), 3);
		System.out.println(inv1.size());
	}

}
