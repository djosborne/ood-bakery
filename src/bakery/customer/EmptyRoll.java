package bakery.customer;
/**
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
    protected boolean noCustomers() {
        return true;
    }

    /**
     * @return 0 since Empty has no Students
     */
    protected int numCustomers() {
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
     * @param s
     *            student to be tested if in class
     * @return false since there are no students in Empty's class
     */
    protected boolean isReturningCustomer(Customer s) {
        return false;
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
}