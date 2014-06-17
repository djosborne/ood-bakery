package bakery.customer;

import java.util.ArrayList;

import bakery.Order;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public class Node extends CustomerRoll {

    private Customer c;
    
    private CustomerRoll rest;
    
	/**************************************************************************
	 * Getters, Setters, and Constructors
	 *************************************************************************/

    /**
     * Constructor
     * 
     * @param c
     *            Student to be initialized in the classroll
     * @param rest
     *            ClassRoll to be passed into rest
     */
    public Node(Customer c, CustomerRoll rest) {
        this.c = c;
        this.rest = rest;
    }
    
    private Node(Customer c, CustomerRoll rest, ArrayList<Order> orders) {
    	this.c = c;
    	this.rest = rest;
    }

    /**
     * @return false
     */
    protected boolean isEmpty() {
        return false;
    }

    /**
     * @return this node's customer
     */
    protected Customer getCustomer() {
        return c;
    }

    /**
     * @return this node's rest
     */
    protected CustomerRoll getCustomerRoll() {
        return rest;
    }

    protected boolean isSubset(CustomerRoll crSuperSet) {
    	return true;
//        if (CustomerRoll.inCustomerRoll(crSuperSet, getCustomer())) {
//            return getCustomerRoll().isSubset(crSuperSet);
//        }
//        else {
//            return false;
//        }
    }
	

	/**************************************************************************
	 * Functions to search if customers already exist in the roll
	 *************************************************************************/
	public boolean isReturningCustomer(Integer ID) {
		if (getCustomer().getCustomerID().equals(ID)) {
			return true;
		}
		else {
			return getCustomerRoll().isReturningCustomer(ID);
		}
	}
	
    /**
     * Dynamic method to check if student is in ClassRoll
     * 
     * @param s1
     *            Student to be checked if in class
     * @return T/F if student is in class
     */
    public boolean isReturningCustomer(String lastName, String address,
        String city, String state, Integer zipCode) {
        Customer c = getCustomer();
        if (c.getLastName().equals(lastName)
            && c.getAddress().equals(address) && c.getCity().equals(city)
            && c.getState().equals(state) && c.getZipCode().equals(zipCode)) {
            return true;
        }
        else {
            return this.getCustomerRoll().isReturningCustomer(lastName, address, city, state, zipCode);
        }
    }
    
    

	public int numCustomers() {
		return 1 + getCustomerRoll().numCustomers();
	}

	/*********************************************8
	 * Order Functions
	 **********************************************/
	
	
    String toStringHelper() {
        return getCustomer().toString() + "\n" + getCustomerRoll().toStringHelper();
    }

    public Customer getCustomer(Integer customerID) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return getCustomer();
        }
        else {
            return getCustomerRoll().getCustomer(customerID);
        }
    }
}
