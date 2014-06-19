import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import bakery.Item;
import bakery.customer.CustomerRoll;
import bakery.inventory.Inventory;
import bakery.order.OrderList;

/**
 * @author Jesus Cheng
 * @author Dan Osborne
 * @version 1.0 Test for Bakery
 */
public class BakeryTest {

    private Bakery emptyBakery;
    private Bakery fullBakery;
    private Bakery loadBakery;

    /**
     * Setup for test
     */
    @Before
    public void setUp() {
        emptyBakery = new Bakery(Inventory.emptyInventory(), CustomerRoll
            .emptyRoll(), OrderList.emptyOrder());

        fullBakery = new Bakery(Inventory.emptyInventory().addToStock(
            new Item(0, "Apples", "Fruit", 1.00)), CustomerRoll.emptyRoll()
            .addNewCustomer("TEST", "TEST", "TEST", "TEST", 11111), OrderList
            .emptyOrder());

        ArrayList<Integer> itemIds = new ArrayList<Integer>();
        ArrayList<Integer> itemQuantities = new ArrayList<Integer>();
        itemIds.add(0);
        itemQuantities.add(10);
        fullBakery = fullBakery.performTransaction(1, itemIds,
            itemQuantities, 0, true, new Date());

        Scanner inventoryScanner = new Scanner("bakeryItems.txt");
        Scanner orderScanner = new Scanner("orders.txt");

        loadBakery = emptyBakery.load(inventoryScanner, orderScanner);

    }

    /**
     * Test bakery customer functions
     */
    @Test
    public void testBakeryCustomers() {
        assertFalse("empty bakery is missing person", emptyBakery
            .isRegisteredCustomer(0));
        assertTrue("Full bakery has person", fullBakery
            .isRegisteredCustomer(1));
    }

    /**
     * test bakery inventory functions
     */
    @Test
    public void testBakeryInventory() {
        assertFalse("empty bakery is missing item", emptyBakery
            .isInInventory(0));
        assertTrue("Full bakery has item", fullBakery.isInInventory(0));
    }
}
