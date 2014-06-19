package bakery.customer;

import java.util.ArrayList;

/**
 * Node Entry which extends the CustomerRoll and contains a Customer's data.
 * 
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public class Node extends CustomerRoll {

    /** holds details of the current customer */
    private Customer c;

    /** Remaining customers */
    private CustomerRoll rest;

    /**************************************************************************
     * Getters, Setters, and Constructors
     *************************************************************************/

    /**
     * Constructor
     * 
     * @param c
     *            Customer to be initialized in the CustomerRoll
     * @param rest
     *            CustomerRoll to be passed into rest
     */
    Node(Customer c, CustomerRoll rest) {
        this.c = c;
        this.rest = rest;
    }

    /**
     * Checks if the customer roll is empty
     * 
     * @return false
     */
    protected boolean isEmpty() {
        return false;
    }

    /**
     * Getter method for the customer stored in this node
     * 
     * @return this node's customer
     */
    protected Customer getCustomer() {
        return c;
    }

    /**
     * Getter method for the remaining customers stored in the roll
     * 
     * @return this node's rest
     */
    protected CustomerRoll getCustomerRoll() {
        return rest;
    }

    /**************************************************************************
     * Functions to search if customers already exist in the roll
     *************************************************************************/

    /**
     * Searches if customer exists with the provided ID
     * 
     * @param customerID
     *            ID to search for a matching customer of
     * @return true if customer exists with that ID, false otherwise
     */
    public boolean isReturningCustomer(Integer customerID) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return true;
        }
        else {
            return getCustomerRoll().isReturningCustomer(customerID);
        }
    }

    /**
     * Searches if customer exists with the provided information
     * 
     * @param lastName
     *            Last Name of the customer
     * @param address
     *            Address of the customer
     * @param city
     *            City of the customer
     * @param state
     *            State of the customer
     * @param zipCode
     *            Integer representation of the customer's zip code
     * 
     * @return true if customer exists with that ID, false otherwise
     */
    public boolean isReturningCustomer(String lastName, String address,
        String city, String state, Integer zipCode) {
        Customer cus = getCustomer();
        if (cus.getLastName().equals(lastName)
            && cus.getAddress().equals(address) && cus.getCity().equals(city)
            && cus.getState().equals(state)
            && cus.getZipCode().equals(zipCode)) {
            return true;
        }
        else {
            return this.getCustomerRoll().isReturningCustomer(lastName,
                address, city, state, zipCode);
        }
    }

    /**
     * Checks the number of customers stored in this customer roll
     * 
     * @return number of customeres
     */
    public int numCustomers() {
        return 1 + getCustomerRoll().numCustomers();
    }

    /*********************************************
     * 8 Order Functions
     **********************************************/

    /**
     * Helper function for toString so the original can print header information
     * above all customer info. This function prints a row for each customer and
     * dumps their information in that row
     * 
     * @return String containing all customer information
     */
    String toStringHelper() {
        return getCustomer().toString() + "\n"
            + getCustomerRoll().toStringHelper();
    }

    /**
     * Getter method for a customer with the given ID. PRECONDITION: User with
     * the given ID must exist!
     * 
     * @param customerID
     *            ID of the customer to return
     * 
     * @return customer, assuming it exists in the roll
     */
    public Customer getCustomer(Integer customerID) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return getCustomer();
        }
        else {
            return getCustomerRoll().getCustomer(customerID);
        }
    }

    /**
     * Getter method for all customers with the given last name. PRECONDITION:
     * At least 1 user with the last name must exist!
     * 
     * @param lname
     *            Last Name to search for
     * 
     * @return customerRoll containing all customers with provided last name,
     *         assuming at least 1 exists
     */
    public CustomerRoll getCustomersByLastName(String lname) {
        if (c.getLastName().equals(lname)) {
            return new Node(c, rest.getCustomersByLastName(lname));
        }
        else {
            return rest.getCustomersByLastName(lname);
        }
    }

    /**
     * Removes the customer with the input ID from the customerRoll
     * PRECONDITION: User with provided customerID must exist in the
     * customerRoll
     * 
     * @param customerID
     *            ID of the customer to remove
     * @return a new customer roll without that customer in it
     */
    public CustomerRoll removeCustomer(Integer customerID) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return rest.removeCustomer(customerID);
        }
        else {
            return new Node(getCustomer(), rest.removeCustomer(customerID));
        }
    }

    /**
     * Searches for the ID of a customer who matches the provided information
     * PRECONDITION: A user must already exist in the CR who matches this
     * information
     * 
     * @param lastName
     *            Last Name of the customer
     * @param address
     *            Address of the customer
     * @param city
     *            City of the customer
     * @param zipCode
     *            Integer representation of the zip code of the customer
     * @param state
     *            customer state
     * 
     * @return the ID of a customer who matches the information exactly.
     */
    public Integer getCustomerID(String lastName, String address,
        String city, String state, Integer zipCode) {
        Customer cus = getCustomer();
        if (cus.getLastName().equals(lastName)
            && cus.getAddress().equals(address) && cus.getCity().equals(city)
            && cus.getState().equals(state)
            && cus.getZipCode().equals(zipCode)) {
            return cus.getCustomerID();
        }
        else {
            return this.getCustomerRoll().getCustomerID(lastName, address,
                city, state, zipCode);
        }
    }

    /**
     * Function which returns a new, identical CustomerRoll except the customer
     * with the provided ID has had its point numbers modified PRECONDITION: A
     * customer must exist with the provided customerID
     * 
     * @param customerID
     *            ID of the customer to modify
     * @param newAvailableDiscount
     *            new amoutn of discountpoints for the customer
     * @param newLoyaltyAmt
     *            the new amount of loyaltypoints for the customer
     * @return customer roll
     */
    public CustomerRoll setPoints(Integer customerID,
        double newAvailableDiscount, double newLoyaltyAmt) {
        if (getCustomer().getCustomerID().equals(customerID)) {
            return new Node(getCustomer().setPoints(newAvailableDiscount,
                newLoyaltyAmt), getCustomerRoll());
        }
        else {
            return new Node(getCustomer(), rest.setPoints(customerID,
                newAvailableDiscount, newLoyaltyAmt));
        }
    }

    /**
     * Adds all customers into an arraylist
     * 
     * @param customers
     *            Arraylist to add to
     * 
     * @return arraylist containing all customers
     */
    ArrayList<Customer> getAllCustomers(ArrayList<Customer> customers) {
        customers.add(getCustomer());
        return getCustomerRoll().getAllCustomers(customers);
    }
}
