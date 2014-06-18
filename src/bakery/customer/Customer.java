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
     * Construct a new customer with given parameters
     * 
     * @param customerID
     *            customer's ID
     * @param lastName
     *            customer's last name
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
    
    public Customer(Integer customerID, String lastName, String address,
        String city, String state, int zipCode, double discountPts, double loyaltyPts) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        
        this.availableDiscountPoints = discountPts;
        this.loyaltyPoints = loyaltyPts;
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
    
    public double getDiscountPoints() {
        return availableDiscountPoints;
    }
    
    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }
    
    public Customer setPoints(double discountPts, double loyaltyPts) {
        return new Customer(getCustomerID(), getLastName(), getAddress(), getCity(), getState(), getZipCode(), discountPts, loyaltyPts);
    }
  

    /**
     * String representation of this customer
     * 
     * @return String representation of this customer
     */
    public String toString() {
        String sZipCode = String.format("%05d", zipCode);
        return "[" + customerID + "] \t[" + lastName + "]\t[" + address + "]\t[" + city + "]\t[" + sZipCode + "] [" + getDiscountPoints() + "] [" + getLoyaltyPoints() + "]";
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
