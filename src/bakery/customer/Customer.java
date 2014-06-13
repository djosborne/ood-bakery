package bakery.customer;

/**
 * Class to store customer data
 * 
 * @author Daniel Osborne
 * @version 6.12.14
 */
public class Customer {
    /** customer's ID */
    private int customerID;

    /** customer's last name */
    private String lastName;

    /** customer's address */
    private String address;

    /** customer's city */
    private String city;

    /** customer's zipCode */
    private int zipCode;

    /** reward membership status */
    private boolean isRewardsMember;

    /**
     * Construct a new customer with given parameters
     * 
     * @param customerID
     *            customer's ID
     * @param lastName
     *            customer's last name
     */
    public Customer(int customerID, String lastName, String address,
        String city, int zipCode) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;

        this.isRewardsMember = false;
    }
    
    int getCustomerID() {
        return this.customerID;
    }
    

    /**
     * String representation of this customer
     * 
     * @return String representation of this customer
     */
    public String toString() {
        return customerID + ":" + lastName + ":" + address + ":" + city + ":" + zipCode;
    }

    /**
     * @param o
     *            comparison Object
     * @return whether this and o are equivalent
     */
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            Customer that = (Customer) o;
            return this.getCustomerID() == that.getCustomerID();
        }
        else {
            return false;
        }
    }

    /**
     * @return hashCode for this
     */
    public int hashCode() {
        return getCustomerID();
    }
}
