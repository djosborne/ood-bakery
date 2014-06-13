package bakery.customer;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public class Node extends CustomerRoll {

    private Customer c;
    private CustomerRoll rest;

    /**
     * Constructor
     * 
     * @param s
     *            Student to be initialized in the classroll
     * @param cr
     *            ClassRoll to be passed into rest
     */
    public Node(Customer s, CustomerRoll cr) {
        this.c = s;
        this.rest = cr;
    }

    /**
     * @return false
     */
    protected boolean noCustomers() {
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

    /**
     * Gets the number of non-duplicate Students in ClassRoll
     * 
     * @return number of students in CR
     */
    protected int numCustomers() {
        if (!CustomerRoll.inCustomerRoll(this.getCustomerRoll(), this.getCustomer())) {
            return 1 + this.getCustomerRoll().numCustomers();
        }
        else {
            return this.getCustomerRoll().numCustomers();
        }
    }
    
    
    protected boolean isSubset(CustomerRoll crSuperSet) {
        if (CustomerRoll.inCustomerRoll(crSuperSet, getCustomer())) {
            return getCustomerRoll().isSubset(crSuperSet);
        }
        else {
            return false;
        }
    }

    /**
     * Dynamic method to check if student is in ClassRoll
     * 
     * @param s1
     *            Student to be checked if in class
     * @return T/F if student is in class
     */
    protected boolean isReturningCustomer(Customer s1) {
        if (s1.equals(this.getCustomer())) {
            return true;
        }
        else {
            return this.getCustomerRoll().isReturningCustomer(s1);
        }
    }
}
