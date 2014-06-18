package bakery.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bakery.order.OrderList;

public class CustomerRollTest {

    private CustomerRoll custR0;
    private CustomerRoll custR1;
    private CustomerRoll custR2;
    private CustomerRoll custR3;
    private CustomerRoll custR4;
    
    Customer cust1 = new Customer(1, "one", "street1", "city1", "st1", 12345);
    Customer cust2 = new Customer(2, "two", "street2", "city2", "st2", 54321);
    Customer cust3 = new Customer(3, "three", "street3", "city3", "st3", 34251);
    Customer cust4 = new Customer(4, "four", "street4", "city4", "st4", 34214);
    
    
    @Before
    public void setUp() {
        custR0 = CustomerRoll.emptyRoll();
        custR1 = custR0.addNewCustomer("one", "street1", "city1", "st1", 12345);
        custR2 = custR1.addNewCustomer("two", "street2", "city2", "st2", 54321);
        custR3 = custR2.addNewCustomer("three", "street3", "city3", "st3", 34251);
        custR4 = custR3.addNewCustomer("four", "street4", "city4", "st4", 34214);
    }

    @Test
    public void testPrint() {
        System.out.println(custR0.numCustomers());
        System.out.println(custR4.toString());
        System.out.println(custR3.removeCustomer(1).toString());
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue(custR0.isEmpty());
        assertFalse(custR2.isEmpty());
        assertFalse(custR3.isEmpty());
    }
    
    @Test
    public void testNumCustomers() {
        assertSame(custR0.numCustomers(), 0);
        assertSame(custR1.numCustomers(), 1);
        assertSame(custR2.numCustomers(), 2);
        assertSame(custR3.numCustomers(), 3);
        assertSame(custR3.removeCustomer(1).numCustomers(), 2);
    }
    
    @Test
    public void testIsReturningCustomer() {
        assertTrue(custR1.isReturningCustomer("one", "street1", "city1", "st1", 12345));
        assertTrue(custR2.isReturningCustomer("one", "street1", "city1", "st1", 12345));
        assertTrue(custR4.isReturningCustomer("four", "street4", "city4", "st4", 34214));
    }
    
    
}
