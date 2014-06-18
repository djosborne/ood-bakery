package bakery.order;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bakery.Item;
import bakery.inventory.Inventory;

public class OrderListTest {

    private OrderList ord0;
    private OrderList ord1;
    private OrderList ord2;
    
    private final String apple = "Apple";
    private final String pear = "Pear";
    private final String orange = "Orange";
    private final String melon = "Melon";
    private final String fruit = "Fruit";
    
    private Item item1 = new Item(1, apple, fruit, 1);
    private Item item2 = new Item(2, pear, fruit, 2);
    private Item item3 = new Item(3, melon, fruit, 3);
    private Item item4 = new Item(4, orange, fruit, 4);
    
	@Before
	public void setUp() {
        ord0 = OrderList.emptyOrder();
	}

    
    @Test
    public void testPrint() {
        System.out.println(ord0.size());
        
    }
    
	@Test
	public void testSize() {
		assertSame(ord0.size(), 0);
	}

}
