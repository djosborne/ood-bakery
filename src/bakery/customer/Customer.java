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

    private double loyaltyPoints;

    private double availableDiscountPoints;

    /**
     * Construct a new customer with given parameters. This function overloads
     * the ID Generation and uses the provided ID. Also sets all loyalty points
     * to 0
     * 
     * @param customerID
     *            customer's ID
     * @param lastName
     *            customer's last name
     * @param address
     *            customer's address
     * 
     * @param city
     *            Customer's city
     * @param state
     *            Customer's State
     * @param zipCode
     *            integer represenatation of the customer's zip code
     */
    public Customer(Integer customerID, String lastName, String address,
        String city, String state, int zipCode) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;

        this.availableDiscountPoints = 0;
        this.loyaltyPoints = 0;
    }

    /**
     * Construct a new customer with given parameters. This function overloads
     * the ID Generation and uses the provided ID. to 0
     * 
     * @param customerID
     *            customer's ID
     * @param lastName
     *            customer's last name
     * @param address
     *            customer's address
     * 
     * @param city
     *            Customer's city
     * @param state
     *            Customer's State
     * @param zipCode
     *            integer represenatation of the customer's zip code
     * 
     * @param discountPts
     *            Number of discout points the customer has
     * 
     * @param loyaltyPts
     *            number of loyalty points the customer has
     */
    public Customer(Integer customerID, String lastName, String address,
        String city, String state, int zipCode, double discountPts,
        double loyaltyPts) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;

        this.availableDiscountPoints = discountPts;
        this.loyaltyPoints = loyaltyPts;
    }

    /**
     * Getter method for the customer's ID
     * @return Customer's ID
     */
    public Integer getCustomerID() {
        return customerID;
    }

    /**
     * Getter method for the customer's last name
     * @return Customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter method for the customer's address
     * @return Customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter method for the customer's city
     * @return Customer's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter method for the customer's State
     * @return Customer's state
     */
    public String getState() {
        return state;
    }

    /**
     * Getter method for the customer's zip code
     * @return customer's zip code
     */
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     * Getter method for the customer's discount points
     * @return Customer's discount points
     */
    public double getDiscountPoints() {
        return availableDiscountPoints;
    }

    /**
     * Getter method for the customer's loyalty points
     * @return Customer's loyalty points
     */
    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Customer Factory Creator which returns 
     * the same customer with new point values
     * @param discountPts Number of discount pts the new customer should have
     * @param loyaltyPts number of loyalty pts the new customer should have
     * @return New customer w same params as old, but with new point values
     */
    public Customer setPoints(double discountPts, double loyaltyPts) {
        return new Customer(getCustomerID(), getLastName(), getAddress(),
            getCity(), getState(), getZipCode(), discountPts, loyaltyPts);
    }

    /**
     * String representation of this customer
     * 
     * @return String representation of this customer
     */
    public String toString() {
        String sZipCode = String.format("%05d", zipCode);
        return "[" + customerID + "] \t[" + lastName + "]\t[" + address
            + "]\t[" + city + "]\t[" + sZipCode + "] [" + getDiscountPoints()
            + "] [" + getLoyaltyPoints() + "]";
    }

    /**
     * Equals method for customer
     * 
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
     * Hashcode generator for customer
     * 
     * @return hashCode for this
     */
    public int hashCode() {
        return getCustomerID();
    }
}
