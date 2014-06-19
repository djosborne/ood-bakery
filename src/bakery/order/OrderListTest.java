package bakery.order;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bakery.Item;
import bakery.Order;

/**
 * @author Jesus Cheng
 * @author Dan Osborne
 * @version 1.0
 * Test for Order List
 */
public class OrderListTest {

    private OrderList ord0;
    private OrderList ord1;
    private OrderList ord2;
    private OrderList ord3;
    private OrderList ord4;
    
    private Date date1 = new Date(01 / 01 / 2014);
    private Date date2 = new Date(02 / 02 / 2014);
    private Date date3 = new Date(03 / 03 / 2014);
    private Date date4 = new Date(04 / 04 / 2014);
    
    private final String apple = "Apple";
    private final String pear = "Pear";
    private final String orange = "Orange";
    private final String melon = "Melon";
    private final String fruit = "Fruit";
    
    private Item item1 = new Item(1, apple, fruit, 1);
    private Item item2 = new Item(2, pear, fruit, 2);
    private Item item3 = new Item(3, melon, fruit, 3);
    private Item item4 = new Item(4, orange, fruit, 4);
    

    private Order order1 = new Order(1, item1, 
        item1.getPrice() * 1, 1, 1, 1, 0, 0, true, date1, date1);
    private Order order2 = new Order(2, item2, 
        item2.getPrice() * 2, 2, 2, 2, 0, 0, true, date2, date2);
    private Order order3 = new Order(3, item3, 
        item3.getPrice() * 3, 3, 3, 3, 0, 0, true, date3, date3);
    private Order order4 = new Order(4, item4, 
        item4.getPrice() * 4, 4, 4, 4, 0, 0, true, date4, date4);
    private Order order5 = new Order(4, item2, 
        item2.getPrice() * 4, 4, 4, 4, 0, 0, true, date4, date4);
    
    /**
     * Set up the test
     */
    @Before
    public void setUp() {
        ord0 = OrderList.emptyOrder();
        ord1 = ord0.addToOrderList(order1);
        ord2 = ord1.addToOrderList(order2);
        ord3 = ord2.addToOrderList(order3);
        ord4 = ord3.addToOrderList(order4);
        ord4 = ord4.addToOrderList(order5);
    }

    
    /**
     * Test Print out
     */
    @Test
    public void testPrint() {
        System.out.println(ord0.size());
        System.out.println(ord4.toString());
        assertTrue(true);
        assertSame(ord0.size(), 0);
        // CHECK THIS
        //System.out.println(ord3.removeFromOrderList(order1).toString());
    }
    
    /**
     * Test is empty
     */
    @Test
    public void testIsEmpty() {
        assertTrue(ord0.isEmpty());
        assertFalse(ord2.isEmpty());
        assertFalse(ord3.isEmpty());
    }
    
    /**
     * Test size
     */
    @Test
    public void testSize() {
        assertSame(ord0.size(), 0);
        assertSame(ord1.size(), 1);
        assertSame(ord2.size(), 2);
        assertSame(ord3.size(), 3);
        //assertSame(ord3.removeFromOrderList(order1).size(), 2);
    }
    
    /**
     * Test ContainsOrder
     */
    @Test
    public void testContainsOrder() {
        assertFalse(ord0.containsOrder(1));
        assertTrue(ord1.containsOrder(1));
        assertTrue(ord4.containsOrder(2));
        assertTrue(ord4.containsOrder(order3));
        assertTrue(ord4.containsOrder(order4));
    }

    /**
     * testGetOrdersByOrderID
     */
    @Test
    public void testGetOrdersByOrderID() {
        System.out.println(ord2.getOrdersByOrderID(2).toString());
        //CHECK THIS
        System.out.println(ord4.getOrdersByOrderID(4).toString());
        assertTrue(true);
        assertSame(ord0.size(), 0);
    }
    
    /**
     * testGetOrdersByCustomerID
     */
    @Test
    public void testGetOrdersByCustomerID() {
        System.out.println(ord2.getOrdersByCustomerID(1).toString());
        System.out.println(ord4.getOrdersByCustomerID(4).toString());
        assertTrue(true);
        assertSame(ord0.size(), 0);
    }

}
