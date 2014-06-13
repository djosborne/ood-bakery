package bakery.customer;

/**
 * @author Daniel Osborne
 * @version 1.0
 */
public abstract class CustomerRoll {
    /**
     * Return True or False if ClassRoll has students
     * 
     * @return boolean
     */
    protected abstract boolean noCustomers();

    /**
     * Accessor method for ClassRoll Student
     * 
     * @return Student
     */
    protected abstract Customer getCustomer();

    /**
     * Number of customers in class roll
     * 
     * @return integer number of non-duplicate customers
     */
    protected abstract int numCustomers();

    /**
     * Returns true if customer is in class
     * 
     * @param s
     *            Student to check
     * @return T/F depending on enrollment status
     */
    protected abstract boolean isReturningCustomer(Customer s);

    /**
     * Returns true if TODO
     * 
     * @param crSuperSet
     * @return
     */
    protected abstract boolean isSubset(CustomerRoll crSuperSet);

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
     *            ClassRoll instance
     * @param s
     *            Student Instance
     * @return cr The passed in ClassRoll instance with the Student 's'
     */
    public static CustomerRoll enroll(CustomerRoll cr, Customer s) {
        return new Node(s, cr);
    }

    /**
     * @param cr
     *            ClassRoll instance
     * @return number of students in cr
     */
    public static int numStudents(CustomerRoll cr) {
        return cr.numCustomers();
    }

    /**
     * @param cr
     *            ClassRoll instance
     * @return T/F depending on whether cr's enrolled students is empty (true)
     *         or not (false)
     */
    public static boolean noStudents(CustomerRoll cr) {
        return cr.noCustomers();
    }

    /**
     * @param cr
     *            ClassRoll instance
     * @param s
     *            Student instance
     * @return T/F if ClassRoll contains Student
     */
    public static boolean inCustomerRoll(CustomerRoll cr, Customer s) {
        return cr.isReturningCustomer(s);
    }

    /**
     * @param crSuperSet
     *            The superset to be tested
     * @param crSubSet
     *            The subset to be tested
     * @return boolean True or False, if it is a subset or not
     */
    public static boolean isSubset(CustomerRoll crSubSet, CustomerRoll crSuperSet) {
        return crSubSet.isSubset(crSuperSet);
    }

    /**
     * @return str1 String containing number of students in class
     */
    public String toString() {
        String str1 = "There are " + CustomerRoll.numStudents(this);
        str1 += " registered customers.";
        return str1;
    }

    /**
     * Tests input to see if it equals the current ClassRoll
     * 
     * @param o
     *            Object to be compared with
     * @return T/F if object is equal
     */
    public boolean equals(Object o) {
        if (o instanceof CustomerRoll) {
            CustomerRoll that = (CustomerRoll) o;
            if (isSubset(that, this) && isSubset(this, that)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generate a hashcode of the ClassRoll
     * 
     * @return hashcode for this classroll
     */
    public int hashCode() {
        if (this.noCustomers()) {
            return 0;
        }
        else {
            return CustomerRoll.numStudents(this);
        }
    }
}
