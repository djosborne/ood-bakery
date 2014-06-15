package bakery.customer;


import java.util.ArrayList;
import java.util.Date;

import bakery.Order;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public abstract class CustomerRoll {
	
	private Integer lastUsedID = 0;
	private Integer lastUsedOrderID = 0;
	
	private Integer getNextAvailableID() {
		while (isReturningCustomer(lastUsedID)) {
			lastUsedID++;
		}
		
		return lastUsedID;
	}
	
	private Integer getNextAvailableOrderID() {
		while (isExistingOrder(lastUsedOrderID)) {
			lastUsedOrderID++;
		}
		
		return lastUsedOrderID;
	}
	
	public abstract Customer getCustomer(Integer customerID);
	
    /**
     * Return True or False if ClassRoll has students
     * 
     * @return boolean
     */
    protected abstract boolean isEmpty();

    /**
     * Accessor method for ClassRoll Student TODO: Add a parameter to get
     * customer by
     * 
     * @return Student
     */
    protected abstract Customer getCustomer();

    /**
     * Number of customers in class roll
     * 
     * @return integer number of non-duplicate customers
     */
    public abstract int numCustomers();

    /**
     * Returns true if customer is in class
     * 
     * @param s
     *            Student to check
     * @return T/F depending on enrollment status
     */
    abstract public boolean isReturningCustomer(String lastName, String address,
        String city, String state, Integer zipCode);
    
    abstract public boolean isReturningCustomer(Integer ID);
    
    public CustomerRoll addNewCustomer(String lastName, String address,
            String city, String state, Integer zipCode) {
    	if (isReturningCustomer(lastName, address, city, state, zipCode)) {
    		throw new RuntimeException("Tried to add a new customer who already exists!");
    	}
    	else {
    		Customer c = new Customer(getNextAvailableID(), lastName, address, city, state, zipCode);
    		return new Node(c, this);
    	}
    	
    }
    
    
    
    public CustomerRoll addNewCustomer(Integer ID, String lastName, String address,
            String city, String state, Integer zipCode) {
    	if (isReturningCustomer(ID)) {
    		throw new RuntimeException("That ID is already used by a customer!");
    	}
    	else {
    		Customer c = new Customer(ID, lastName, address, city, state, zipCode);
    		return new Node(c, this);
    	}
    }
    

    /**
     * Returns true if TODO
     * 
     * @param crSuperSet
     * @return
     */
    protected abstract boolean isSubset(CustomerRoll crSuperSet);

    /**
     * Generate empty customerRoll
     * 
     * @return Empty CustomerRoll
     */
    public static CustomerRoll emptyRoll() {
        return new EmptyRoll();
    }


    /**
     * @param cr
     *            ClassRoll instance
     * @return number of students in cr
     */
    public static int numCustomers(CustomerRoll cr) {
        return cr.numCustomers();
    }

    /**
     * @param cr
     *            ClassRoll instance
     * @return T/F depending on whether cr's enrolled students is empty (true)
     *         or not (false)
     */
    public static boolean isEmpty(CustomerRoll cr) {
        return cr.isEmpty();
    }


    /**
     * @param crSuperSet
     *            The superset to be tested
     * @param crSubSet
     *            The subset to be tested
     * @return boolean True or False, if it is a subset or not
     */
    public static boolean isSubset(CustomerRoll crSubSet,
        CustomerRoll crSuperSet) {
        return crSubSet.isSubset(crSuperSet);
    }
    
    

    /**
     * @return str1 String containing number of students in class
     */
    public String toString() {
        String str1 = "There are " + CustomerRoll.numCustomers(this);
        str1 += " registered customers." + "\n" + toStringHelper();
        return str1;
    }
    
    abstract String toStringHelper();

    /**
     * Tests input to see if it equals the current ClassRoll
     * 
     * @param o
     *            Object to be compared with
     * @return T/F if object is equal
     */
    public boolean equals(Object o) {
        if (o instanceof CustomerRoll) {
            CustomerRoll that = (CustomerRoll) o;
            if (isSubset(that, this) && isSubset(this, that)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generate a hashcode of the ClassRoll
     * 
     * @return hashcode for this classroll
     */
    public int hashCode() {
        if (this.isEmpty()) {
            return 0;
        }
        else {
            return CustomerRoll.numCustomers(this);
        }
    }
    
    
    /******************************
     * Order Functions
     */
    
    abstract boolean isExistingOrder(Integer lastUsedOrderID);
    
    abstract public CustomerRoll addOrder(Integer customerID, Order o);
    
    abstract public ArrayList<Order> getAllOrders();
    
    abstract public ArrayList<Order> getOrdersWithOrderId(Integer orderID);
   
    
//    abstract public CustomerRoll performTransaction(Integer orderID, Integer customerID, Integer itemID, boolean paid, Date pickupDate);
}

