package bakery.customer;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public abstract class CustomerRoll {

    private Integer lastUsedID = 1;

    private Integer getNextAvailableID() {
        while (isReturningCustomer(lastUsedID)) {
            lastUsedID++;
        }

        return lastUsedID;
    }

    public abstract Customer getCustomer(Integer customerID);

    public abstract Integer getCustomerID(String lastName, String address,
        String city, String state, Integer zipCode);

    // public abstract double getDiscountPoints(Integer customerID);

    public abstract CustomerRoll getCustomersByLastName(String lname);

    public abstract CustomerRoll setPoints(Integer customerID,
        double newAvailableDiscount, double loyaltyAmt);

    /**
     * Return True or False if CustomerRoll has customers
     * 
     * @return boolean
     */
    protected abstract boolean isEmpty();

    /**
     * Accessor method for CustomerRoll Customer
     * 
     * @return Customer
     */
    protected abstract Customer getCustomer();

    /**
     * Number of customers in customer roll
     * 
     * @return integer number of non-duplicate customers
     */
    public abstract int numCustomers();

    /**
     * Checks if a customer with the provided information is already registered
     * in the roll
     * 
     * @param lastName
     *            Customer's last name
     * @param address
     *            Customer's address
     * @param city
     *            Customer's city
     * @param state
     *            Customer's state
     * @param zipCode
     *            Integer representing the zip code of the customer
     * @return True if customer is enrolled, false otherwise
     */
    abstract public boolean isReturningCustomer(String lastName,
        String address, String city, String state, Integer zipCode);

    abstract public boolean isReturningCustomer(Integer ID);

    public CustomerRoll addNewCustomer(String lastName, String address,
        String city, String state, Integer zipCode) {
        if (isReturningCustomer(lastName, address, city, state, zipCode)) {
            throw new RuntimeException(
                "Tried to add a new customer who already exists!");
        }
        else {
            Customer c = new Customer(getNextAvailableID(), lastName,
                address, city, state, zipCode);
            return new Node(c, this);
        }
    }

    public abstract CustomerRoll removeCustomer(Integer customerID);

    public CustomerRoll addNewCustomer(Integer ID, String lastName,
        String address, String city, String state, Integer zipCode) {
        if (isReturningCustomer(ID)) {
            throw new RuntimeException(
                "That ID is already used by a customer!");
        }
        else {
            Customer c = new Customer(ID, lastName, address, city, state,
                zipCode);
            return new Node(c, this);
        }
    }

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
     *            CustomerRoll instance
     * @return number of customers in cr
     */
    public static int numCustomers(CustomerRoll cr) {
        return cr.numCustomers();
    }

    /**
     * @param cr
     *            CustomerRoll instance
     * @return T/F depending on whether cr's enrolled customers is empty (true)
     *         or not (false)
     */
    public static boolean isEmpty(CustomerRoll cr) {
        return cr.isEmpty();
    }

    /**
     * Creates a string representation of the CustomerRoll, including all
     * customers enrolled.
     * 
     * @return str1 String seperated by newlines with all customer info.
     */
    public String toString() {
        String str1 = "There are " + CustomerRoll.numCustomers(this);
        str1 += " registered customers." + "\n";
        str1 += "[CustomerID] [Last Name] [Address] [City] [Zip Code] [Loyalty Points] [Discount Points] \n";
        str1 += toStringHelper();
        return str1;
    }

    /**
     * Helper helper for the toString method
     * 
     * @return String containing all customer info seperated by spaces, with
     *         headers
     */
    abstract String toStringHelper();

    /**
     * Tests input to see if it equals the current CustomerRoll
     * 
     * TODO: Need to fix this
     * 
     * @param o
     *            Object to be compared with
     * @return T/F if object is equal
     */
    public boolean equals(Object o) {
        if (o instanceof CustomerRoll) {
            CustomerRoll that = (CustomerRoll) o;

        }
        return false;
    }

    /**
     * Generate a hashcode of the CustomerRoll
     * 
     * @return hashcode for this CustomerRoll
     */
    public int hashCode() {
        if (this.isEmpty()) {
            return 0;
        }
        else {
            return CustomerRoll.numCustomers(this);
        }
    }
}
