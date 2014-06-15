package bakery.customer;


/**
 * Class to store customer data
 * 
 * @author Daniel Osborne
 * @version 6.12.14
 */
public class Customer {
    /** customer's ID */
    private Integer customerID;

    /** customer's last name */
    private String lastName;

    /** customer's address */
    private String address;

    /** customer's city */
    private String city;

    /** customer's state */
    private String state;
    
    /** customer's zipCode */
    private Integer zipCode;

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
        String city, String state, int zipCode) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;

        this.isRewardsMember = false;
    }
    
    public Integer getCustomerID() {
        return customerID;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
    
    public Integer getZipCode() {
        return zipCode;
    }
    /**
     * String representation of this customer
     * 
     * @return String representation of this customer
     */
    public String toString() {
        return "[" + customerID + "] \t[" + lastName + "]\t[" + address + "]\t[" + city + "]\t[" + zipCode + "]";
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
