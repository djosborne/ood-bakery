package bakery.customer;

import java.util.ArrayList;

import bakery.Order;
import bakery.order.EntryOrder;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public class Node extends CustomerRoll {

    private Customer c;
    private double loyaltyBalance = 0;
    
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
    
    private Node(Customer c, CustomerRoll rest, double loyaltyEarned) {
        this.c = c;
        this.rest = rest;
        this.loyaltyBalance = loyaltyEarned;
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
    
    public CustomerRoll removeCustomer(Integer customerID) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return rest.removeCustomer(customerID);
        } else {
            return new Node(getCustomer(), rest.removeCustomer(customerID));
        }
    }
    
    public CustomerRoll getCustomersByLastName(String lname) {
        if (c.getLastName().equals(lname)) {
            return new Node(c, rest.getCustomersByLastName(lname));
        }
        else {
            return rest.getCustomersByLastName(lname);
        }
    }

    
    public Integer getCustomerID(String lastName, String address,
        String city, String state, Integer zipCode) {
        Customer c = getCustomer();
        if (c.getLastName().equals(lastName)
            && c.getAddress().equals(address) && c.getCity().equals(city)
            && c.getState().equals(state) && c.getZipCode().equals(zipCode)) {
            return c.getCustomerID();
        }
        else {
            return this.getCustomerRoll().getCustomerID(lastName, address, city, state, zipCode);
        }
    }
    
    
    public double getRewardsPoints(Integer customerID) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return loyaltyBalance;
        }
        else {
            return getCustomerRoll().getRewardsPoints(customerID);
        }
    }

    
    public CustomerRoll setLoyalty(Integer customerID, double loyaltyAmt) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return new Node(getCustomer(), getCustomerRoll(),loyaltyAmt);
        }
        else {
            return new Node(getCustomer(), rest.setLoyalty(customerID, loyaltyAmt), getCurrentLoyalty());
        }
    }
    
    public CustomerRoll addLoyalty(Integer customerID, double loyaltyAmt) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return new Node(getCustomer(), getCustomerRoll(),getCurrentLoyalty() + loyaltyAmt);
        }
        else {
            return new Node(getCustomer(), rest.setLoyalty(customerID, loyaltyAmt), getCurrentLoyalty());
        }
    }
    
    
    private double getCurrentLoyalty() {
        return loyaltyBalance;
    }
    
}
