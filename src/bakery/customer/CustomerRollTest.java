package bakery.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jesus Cheng
 * @author Dan Osborne
 * @version 1.0
 * Test for inventory
 */
public class CustomerRollTest {

    private CustomerRoll custR0;
    private CustomerRoll custR1;
    private CustomerRoll custR2;
    private CustomerRoll custR3;
    private CustomerRoll custR4;
    
    /**
     * Set up for test
     */
    @Before
    public void setUp() {
        custR0 = CustomerRoll.emptyRoll();
        custR1 = custR0.addNewCustomer("one", "street1", 
            "city1", "st1", 12345);
        custR2 = custR1.addNewCustomer("two", "street2", 
            "city2", "st2", 54321);
        custR3 = custR2.addNewCustomer("three", "street3", 
            "city3", "st3", 34251);
        custR4 = custR3.addNewCustomer("four", "street4", 
            "city4", "st4", 34214);
    }

    /**
     * Testing print out
     */
    @Test
    public void testPrint() {
        System.out.println(custR0.numCustomers());
        System.out.println(custR4.toString());
        System.out.println(custR3.removeCustomer(1).toString());
        assertTrue(custR0.isEmpty());
        assertFalse(custR2.isEmpty());
    }
    
    /**
     * Testing is empty
     */
    @Test
    public void testIsEmpty() {
        assertTrue(custR0.isEmpty());
        assertFalse(custR2.isEmpty());
        assertFalse(custR3.isEmpty());
    }
    
    /**
     * testing num customers
     */
    @Test
    public void testNumCustomers() {
        assertSame(custR0.numCustomers(), 0);
        assertSame(custR1.numCustomers(), 1);
        assertSame(custR2.numCustomers(), 2);
        assertSame(custR3.numCustomers(), 3);
        assertSame(custR3.removeCustomer(1).numCustomers(), 2);
    }
    
    /**
     * Testing is returning customer
     */
    @Test
    public void testIsReturningCustomer() {
        assertTrue(custR1.isReturningCustomer("one", 
            "street1", "city1", "st1", 12345));
        assertTrue(custR2.isReturningCustomer("one", 
            "street1", "city1", "st1", 12345));
        assertTrue(custR4.isReturningCustomer("four", 
            "street4", "city4", "st4", 34214));
    }
    
    
}
