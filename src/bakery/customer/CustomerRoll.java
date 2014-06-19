package bakery.customer;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public abstract class CustomerRoll {

    /** Last Used ID from customers in this roll */
    private Integer lastUsedID = 1;

    /**
     * Starting at the lastUsedID, this function returns the nex ID which is not
     * being used by a customer
     * 
     * @return Next open ID for a new customer to initialize with
     */
    private Integer getNextAvailableID() {
        while (isReturningCustomer(lastUsedID)) {
            lastUsedID++;
        }

        return lastUsedID;
    }

    /**
     * Searches through the customer roll for a customer which matches the
     * provided ID and returns it
     * 
     * @param customerID
     *            ID of the customer to find
     * @return The Customer which matches the provided ID
     */
    public abstract Customer getCustomer(Integer customerID);

    /**
     * Given all other pieces of information of a customer except ID, returns a
     * matching customer, or throws exception if none exists
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
     *            Integer represenation of the zip code of the customer
     * @return The customer which matches this information
     */
    public abstract Integer getCustomerID(String lastName, String address,
        String city, String state, Integer zipCode);

    /**
     * Searches for all customers who match the provided last name. May be more
     * than one
     * 
     * @param lname
     *            Last name of the customer to search for
     * @return All customers who match this last name
     */
    public abstract CustomerRoll getCustomersByLastName(String lname);

    /**
     * Creates a new customer roll identical to the one it is dynamic ally
     * called on, except the user whose ID is provided will have his points set
     * to the provided values
     * 
     * @param customerID
     *            ID of customer whose points will be modified
     * @param newAvailableDiscount
     *            New available Discount Points the customer will have set
     * @param loyaltyAmt
     *            New loyalty amount the customer will have set
     * @return Identical CustomRoll with modified user points
     */
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

    /**
     * Function to check if the customer exists in the customerRoll
     * 
     * @param ID
     *            ID of the customer to search for
     * @return True if they already exist, False otherwise
     */
    abstract public boolean isReturningCustomer(Integer ID);

    /**
     * Adds new customer to the customer roll, provided they are not already enrolled
     * @param lastName Last name of the new customer
     * @param address Address of the new customer
     * @param city City of the new customer 
     * @param state State of the new customer
     * @param zipCode Zip code of the new customer
     * @return CustomerRoll with the customer added
     */
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

    /**
     * Removes the customer with the given ID from the customerRoll
     * @param customerID ID of the customer to be removed
     * @return New Customerroll without the specified customer in it
     */
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
