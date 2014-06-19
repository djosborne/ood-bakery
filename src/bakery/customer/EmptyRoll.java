package bakery.customer;

/** EmptyRoll Class
 * @author Jesus Cheng
 * @author Daniel Osborne
 * @version 1.0
 */
public class EmptyRoll extends CustomerRoll {
    /**
     * @return Throws exceptions since Empty has no students
     */
    protected Customer getCustomer() {
        throw new RuntimeException("Can't get a Student from an Empty");
    }

    /**
     * @return Throws exceptions since Empty has no ClassRoll
     */
    protected CustomerRoll getClassRoll() {
        throw new RuntimeException("Can't get a ClassRoll from an Empty");
    }

    /**
     * @return true since Empty has no Students
     */
    protected boolean isEmpty() {
        return true;
    }

    /**
     * @return 0 since Empty has no Students
     */
    public int numCustomers() {
        return 0;
    }

    /**
     * @param crSuperSet
     *            Superset to be tested
     * @return true since the null set is always a subset of any superset
     */
    protected boolean isSubset(CustomerRoll crSuperSet) {
        return true;
    }



    /**
     * @param cr
     *            ClassRoll to be combined with instance
     * @return the passed in ClassRoll
     */
    protected CustomerRoll combineWith(CustomerRoll cr) {
        return cr;
    }

    /**
     * @param cr
     *            ClassRoll to set the rest of
     * @return the original cr
     */
    protected CustomerRoll setRest(CustomerRoll cr) {
        return cr;
    }

    /**
     * @param s
     *            Student to be dropped
     * @return this instance since it is empty and therefore does not include
     *         's'
     */
    protected CustomerRoll drop(Customer s) {
        return this;
    }

    /**
     * @param otherCR
     *            Another ClassRoll to compare with
     * @return always an empty roll, since an empty node has nothing in common
     *         with anything
     */
    protected CustomerRoll inCommonWith(CustomerRoll otherCR) {
        return CustomerRoll.emptyRoll();
    }



    /**
     * Checks if a customer exists with the provided ID
     * @param iD customer ID
     * @return false
     */
    public boolean isReturningCustomer(Integer iD) {
        return false;
    }
    
    /**
     * @param lastName customer last name
     * @param address address of customer
     * @param city city of customer
     * @param state state of customer
     * @param zipCode zip code of customer
     * @return false 
     */
    public boolean isReturningCustomer(String lastName, String address,
        String city, String state, Integer zipCode) {
        return false;
    }

    
    /**
     * toString helper
     * 
     * @return empty string
     */
    String toStringHelper() {
        return "";
    }
    
    /** get customer with ID
     * @param customerID get customer with ID
     * @return exception since customer doesn't exist
     */
    public Customer getCustomer(Integer customerID) {
        throw new RuntimeException("Customer doesn't exist with that ID");
    }

    /** remove customer with ID.
     * @param customerID customer ID
     * @return empty node since the customer has been removed
     */
    public CustomerRoll removeCustomer(Integer customerID) {
        return this;
    }
    
    /** get customer by last name
     * @param lname customer last name
     * @return empty node since no more customers with that last name exist
     */
    public CustomerRoll getCustomersByLastName(String lname) {
        return this;
    }

    /** get customer ID
     * @param lastName customer last name
     * @param address address of customer
     * @param city city of customer
     * @param state state of customer
     * @param zipCode zip code of customer
     * @return false 
     */
    public Integer getCustomerID(String lastName, String address,
        String city, String state, Integer zipCode) {
        throw new RuntimeException("That user does not exist");
    }

    /**
     * @param customerID ID of the target customer
     * @return runtime exception since that user doesn't exist
     */
    public double getDiscountPoints(Integer customerID) {
        throw new RuntimeException("That user does not exist");
    }

    /**
     * @param customerID id of the target customer
     * @param loyaltyAmt number of loyalty points awarded to the customer
     * @param newAvailableDiscount nnum of discount pts to the customer
     * @return runtimeexception since that user doesn't exist
     */
    public CustomerRoll setPoints(Integer customerID, 
        double loyaltyAmt, double newAvailableDiscount) {
        throw new RuntimeException("That user does not exist!");
    }
}