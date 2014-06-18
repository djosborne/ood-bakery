import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import bakery.Item;
import bakery.Order;
import bakery.customer.Customer;
import bakery.customer.CustomerRoll;
import bakery.inventory.Inventory;
import bakery.order.OrderList;

public class Bakery {
    private Inventory inv;
    private CustomerRoll custRoll;
    private OrderList orderList;

    private Scanner inputScanner = new Scanner(System.in);

    Bakery(Inventory inv, CustomerRoll custRoll, OrderList orderList) {
        this.inv = inv;
        this.custRoll = custRoll;
        this.orderList = orderList;
    }

    private Inventory getInventory() {
        return inv;
    }

    private CustomerRoll getCustomerRoll() {
        return custRoll;
    }

    private OrderList getOrderList() {
        return orderList;
    }

    void setCustomerRoll(CustomerRoll cr) {
        this.custRoll = cr;
    }

    boolean isRegisteredCustomer(String lastName, String address,
        String city, String state, Integer zipCode) {
        return getCustomerRoll().isReturningCustomer(lastName, address, city,
            state, zipCode);
    }

    boolean isRegisteredCustomer(Integer customerID) {
        return getCustomerRoll().isReturningCustomer(customerID);
    }

    boolean isInInventory(Integer itemID) {
        return getInventory().containsItem(itemID);
    }

    boolean isInInventory(String bakeryItemName, String bakeryItemCategory) {
        return getInventory()
            .containsItem(bakeryItemName, bakeryItemCategory);
    }

    // provided ID
    Bakery registerNewCustomer(Integer ID, String lastName, String address,
        String city, String state, Integer zipCode) {
        return new Bakery(getInventory(), getCustomerRoll().addNewCustomer(
            ID, lastName, address, city, state, zipCode), getOrderList());
    }

    // generate ID
    Bakery registerNewCustomer(String lastName, String address, String city,
        String state, Integer zipCode) {
        return new Bakery(getInventory(), getCustomerRoll().addNewCustomer(
            lastName, address, city, state, zipCode), getOrderList());
    }

    // Bakery getCustomerByLastName(String lastName) {
    // return new Bakery(getInventory(),
    // getCustomerRoll().getCustomersByLastName(lastName),
    // getOrderList());
    // }

    CustomerRoll getCustomerByLastName(String lastName) {
        return getCustomerRoll().getCustomersByLastName(lastName);
    }

    // generate ID
    Bakery removeCustomer(Integer customerID) {
        return new Bakery(getInventory(), getCustomerRoll().removeCustomer(
            customerID), getOrderList());
    }

    public Bakery addToInventory(Integer itemID, String itemName,
        String category, double itemPrice) {
        return new Bakery(getInventory().addToStock(itemID, itemName,
            category, itemPrice), getCustomerRoll(), getOrderList());
    }

    public Bakery addToInventory(String itemName, String category,
        double itemPrice) {
        return new Bakery(getInventory().addToStock(itemName, category,
            itemPrice), getCustomerRoll(), getOrderList());
    }

    public Bakery removeFromInventory(Integer itemID) {
        return new Bakery(getInventory().removeFromStock(itemID),
            getCustomerRoll(), getOrderList());
    }

    public Bakery performTransaction(Integer orderID, Integer customerID,
        Integer itemID, Integer quantity, double loyaltyAtTimeOfOrder,
        double discountUsedOnOrder, boolean paid, Date orderDate,
        Date pickupDate) {

        Item item = getInventory().getItem(itemID);

        return new Bakery(getInventory(), getCustomerRoll(), getOrderList()
            .addToOrderList(customerID, orderID, paid, orderDate, pickupDate,
                item, quantity, loyaltyAtTimeOfOrder, discountUsedOnOrder));
    }

    public void save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);

            SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");

            fw.write("CustomerID\tLastName\tAddress\tCity\tState\tZipCode\tOrderID\tPaid?\tOrderDate\tPickupDate\tBakeryItemID\tBakeryItemName\tBakeryItemCategory\tQuantity\tPrice\tTotal\tDiscountUsedOnOrder\tTotalDue\tAvailableDiscout\tCurrentLoyalty\n");

            for (Order o : getOrderList()) {

                Integer customerID = o.getCustomerID();
                Customer customer = getCustomerRoll().getCustomer(customerID);

                fw.write(customerID.toString());
                fw.write("\t");
                fw.write(customer.getLastName());
                fw.write("\t");
                fw.write(customer.getAddress());
                fw.write("\t");
                fw.write(customer.getCity());
                fw.write("\t");
                fw.write(customer.getState());
                fw.write("\t");
                fw.write(customer.getZipCode().toString());
                fw.write("\t");
                fw.write(o.getOrderID().toString());
                fw.write("\t");
                fw.write(o.paid() ? "Yes" : "No");
                fw.write("\t");
                fw.write(dFormatter.format(o.getOrderDate()));
                fw.write("\t");
                fw.write(dFormatter.format(o.getPickUpDate()));
                fw.write("\t");
                fw.write(o.getItem().getItemID().toString());
                fw.write("\t");
                fw.write(o.getItem().getItemName());
                fw.write("\t");
                fw.write(o.getItem().getCategory());
                fw.write("\t");
                fw.write(o.getQuantity().toString());
                fw.write("\t");
                fw.write(Double.toString(o.getItem().getPrice()));
                fw.write("\t");

                /*
                 * get all orders with same ID from a user add all totals
                 * together pritn that total
                 */

                fw.write(Double.toString(getOrderList().getOrderTotal(
                    o.getOrderID())));
                fw.write("\t");
                fw.write(Double.toString(o.getDiscountUsedOnOrder()));
                fw.write("\t");

                fw.write(Double.toString(getOrderList().getOrderTotal(
                    o.getOrderID())
                    + o.getDiscountUsedOnOrder()));
                fw.write("\t");
                fw.write("0");
                fw.write("\t");
                fw.write(Double.toString(o.getLoyaltyAtTimeOfOrder()));
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print("ERROR");
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Bakery bakeryCtrl = new Bakery(Inventory.emptyInventory(),
            CustomerRoll.emptyRoll(), OrderList.emptyOrder());

        /**********************************************************************
         * Gather user input to load the Scanners for inventory/customers
         *********************************************************************/

        System.out.println("Welcome to Schmiddty's Bakery!");
        System.out.println("------------------------------");
        System.out.println("1.) to use CCS provided data.");
        System.out.println("2.) to use resulting data from last runthrough");
        System.out.println("3.) Provide a new dataset");
        System.out.print("Enter [1/2/3]: ");

        String userInput = "";

        Scanner inventoryScanner = new Scanner("");
        Scanner orderScanner = new Scanner("");
        boolean allSet = false;

        while (!allSet) {
            userInput = bakeryCtrl.inputScanner.next();
            System.out.println();
            if (userInput.equals("1")) {
                try {
                    File ordersFile = new File("orders.txt");
                    orderScanner = new Scanner(ordersFile);

                    File inventoryFile = new File("bakeryItems.txt");
                    inventoryScanner = new Scanner(inventoryFile);
                    allSet = true;
                }
                catch (Exception e) {
                    System.out
                        .println("[ERROR] Failed to open orders.txt or bakeryItems.txt.");
                    System.out
                        .println("Please ensure both files are in the correct location before trying again.");
                    System.out.println("------------------------------");
                    System.out.println("1.) Use CCS provided data.");
                    System.out
                        .println("2.) Use resulting data from last runthrough");
                    System.out.println("3.) Provide a new dataset");
                    System.out.print("Enter [1/2/3]: ");
                }
            }
            else if (userInput.equals("2")) {
                try {
                    File ordersFile = new File("ordersSave.txt");
                    orderScanner = new Scanner(ordersFile);

                    File inventoryFile = new File("bakeryItemsSave.txt");
                    inventoryScanner = new Scanner(inventoryFile);
                    allSet = true;
                }
                catch (Exception e) {
                    System.out
                        .println("Failed to open ordersSave.txt or bakeryItemsSave.txt.");
                    System.out
                        .println("Please do not select this option if a previous session has not been run.");
                    System.out.println("------------------------------");
                    System.out.println("1.) Use CCS provided data.");
                    System.out
                        .println("2.) Use resulting data from last runthrough");
                    System.out.println("3.) Provide a new dataset");
                    System.out.print("Enter [1/2/3]: ");
                }

            }
            else if (userInput.equals("3")) {

                System.out.println("------------------------------");
                try {
                    System.out.print("Orders Filename: ");
                    userInput = bakeryCtrl.inputScanner.next();
                    File ordersFile = new File(userInput);
                    orderScanner = new Scanner(ordersFile);

                    System.out.print("Bakery Inventory Filename: ");
                    userInput = bakeryCtrl.inputScanner.next();
                    File inventoryFile = new File(userInput);
                    inventoryScanner = new Scanner(inventoryFile);
                    allSet = true;
                }
                catch (Exception e) {
                    System.out.println("Failed to open " + userInput);
                    System.out.println("------------------------------");
                    System.out.println("1.) Use CCS provided data.");
                    System.out
                        .println("2.) Use resulting data from last runthrough");
                    System.out.println("3.) Provide a new dataset");
                    System.out.print("Enter [1/2/3]: ");
                }
            }
            else {
                System.out.println("------------------------------");
                System.out.println("Invalid Selection.");
                System.out.print("Please choose 1, 2, or 3: ");
            }
        }

        /**********************************************************************
         * Move items from scanner into data objects.
         *********************************************************************/
        // skip the headers
        inventoryScanner.nextLine();

        // read actual data
        while (inventoryScanner.hasNext()) {
            String line = inventoryScanner.nextLine();
            String entries[] = line.split("\t");

            bakeryCtrl = bakeryCtrl.addToInventory(Integer
                .parseInt(entries[0]), entries[1], entries[2], Double
                .parseDouble(entries[3]));
        }

        System.out.print("Loading...");

        // skip the headers
        orderScanner.nextLine();

        // read the actual data
        while (orderScanner.hasNext()) {
            String line = orderScanner.nextLine();
            String entries[] = line.split("\t");

            Integer customerID = Integer.valueOf(entries[0]);
            String lastName = entries[1];
            String address = entries[2];
            String city = entries[3];
            String state = entries[4];
            Integer zipCode = Integer.valueOf(entries[5]);
            Integer orderID = Integer.valueOf(entries[6]);
            boolean paid = entries[7].equalsIgnoreCase("Yes") ? true : false;
            String sOrderDate = entries[8];
            String sPickupDate = entries[9];
            Integer bakeryItemID = Integer.valueOf(entries[10]);
            String bakeryItemName = entries[11];
            String bakeryItemCategory = entries[12];
            Integer quantity = Integer.valueOf(entries[13]);
            double price = Double.valueOf(entries[14]);
            double total = Double.valueOf(entries[15]);
            double discountUsedOnOrder = Double.valueOf(entries[16]);
            double totalDue = Double.valueOf(entries[17]);
            double availableDiscount = Double.valueOf(entries[18]);
            double currentLoyalty = Double.valueOf(entries[19]);

            // Register the customer if necessary
            if (!bakeryCtrl.isRegisteredCustomer(customerID)) {
                bakeryCtrl = bakeryCtrl.registerNewCustomer(customerID,
                    lastName, address, city, state, zipCode);
            }

            // Register the item if necessary
            if (!bakeryCtrl.isInInventory(bakeryItemID)) {
                bakeryCtrl = bakeryCtrl.addToInventory(bakeryItemID,
                    bakeryItemName, bakeryItemCategory, price);
            }

            // Register the order
            SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");
            Date dPickupDate = null;
            Date dOrderDate = null;
            try {
                dPickupDate = dFormatter.parse(sPickupDate);
                dOrderDate = dFormatter.parse(sOrderDate);
            }
            catch (Exception e) {
            }

            bakeryCtrl = bakeryCtrl.performTransaction(orderID, customerID,
                bakeryItemID, quantity, currentLoyalty, discountUsedOnOrder,
                paid, dOrderDate, dPickupDate);
        }

        inventoryScanner.close();
        orderScanner.close();

        /**********************************************************************
         * Run remaining GUI
         *********************************************************************/
        boolean admin = false;
        System.out.println("...loaded!");
        System.out.println("------------------------------");
        System.out.println("1.) Cashier Interface");
        System.out.println("2.) Owner Interface");
        System.out.print("Enter [1/2]: ");
        userInput = bakeryCtrl.inputScanner.next();
        System.out.println();

        if (userInput.equals("2")) {
            admin = true;
        }

        boolean quit = false;
        while (!quit) {
            System.out.println("------------------------------");
            // orders
            System.out.println("ORDERS");
            System.out.println("1.) Add New Order"); /*- need output reciept customer info, order info, order total */
            System.out.println("2.) View Existing Orders"); /*- by pickup date, by order date, by product, by paid status */
            System.out.println("3.) Update Existing Order");

            // customers
            System.out.println();
            System.out.println("CUSTOMERS");
            System.out.println("4.) Add New Customer"); // admin only
            System.out.println("5.) View Existing Customer Information"); /*- loyalty status, contact info, all orders */
            System.out.println("6.) Update Existing Customer Info");

            // inventory
            System.out.println();
            System.out.println("INVENTORY");
            System.out.println("7.) Add Inventory Item");
            System.out.println("8.) View All Items in Inventory");
            System.out.println("9.) Update Inventory Items");

            System.out.println("10.) Save and Quit");
            System.out.print("Enter [1/2/3/4/5/6/7/8/9/10]: ");

            userInput = bakeryCtrl.inputScanner.next();
            if (userInput.equals("1")) {
                bakeryCtrl = bakeryCtrl.addNewOrder();
            }
            else if (userInput.equals("2")) {
                bakeryCtrl.viewExistingOrders();
            }
            else if (userInput.equals("3")) {
                // updateExistingOrders();
            }
            else if (userInput.equals("4")) {
                bakeryCtrl = bakeryCtrl.addNewCustomer();
            }
            else if (userInput.equals("5")) {
                bakeryCtrl.viewExistingCustomers();
            }
            else if (userInput.equals("6")) {
                bakeryCtrl = bakeryCtrl.updateExistingCustomer();
            }
            else if (userInput.equals("7")) {
                bakeryCtrl = bakeryCtrl.addInventoryItem();
            }
            else if (userInput.equals("8")) {
                bakeryCtrl.viewExistingInventory();
            }
            else if (userInput.equals("9")) {
                bakeryCtrl = bakeryCtrl.updateInventoryItems();
            }
            else if (userInput.equals("10")) {
                quit = true;
            }
            else {
                System.out.println("[ERROR] Invalid input.");
            }
        }

        bakeryCtrl.save("ordersSave.csv");
    }

    Bakery addNewOrder() {
        /**
         * if (user doesn't exist) make user get the userID from (LastName
         * Address City State ZipCode) get current rewards balance, save for
         * later (preRewardsBalance)
         * 
         * 
         * 
         * generate a new order For each item scan ID prompt quantity end
         * 
         * getTotal()
         * 
         * print preRewardsBalance, available discount
         * 
         * if they have >0 available discount, ask how much they want to apply
         * prompt for DiscountUsedOnOrder getTotalDue() = getttotal - discount
         * 
         * add points to customer's rewards (from totalDue)
         * 
         * 
         * Pay now or bill you? Generate order date (today) Prompt for pickup
         * date
         * 
         * 
         * getBakeryItemID(BakeryItemName)
         * 
         * perform transaction (userID,
         * 
         */
        Bakery modifiedBakery = this;

        System.out.println("Please enter the following customer info:");

        System.out.print("Last Name: ");
        String lastName = inputScanner.next();
        System.out.println();

        System.out.print("Address: ");
        String address = inputScanner.next();
        System.out.println();

        System.out.print("City: ");
        String city = inputScanner.next();
        System.out.println();

        System.out.print("State: ");
        String state = inputScanner.next();
        System.out.println();

        Integer zipCode = 0;
        boolean validZip = false;
        while (!validZip) {
            System.out.print("Zip Code: ");
            String sZipCode = inputScanner.next();
            zipCode = Integer.valueOf(sZipCode);
            validZip = true;

            try {
                zipCode = Integer.valueOf(sZipCode);
            }
            catch (Exception e) {
                System.out.println("Invalid zip code.");
            }
        }
        System.out.println();

        // Register Customer if need be
        if (!modifiedBakery.isRegisteredCustomer(lastName, address, city,
            state, zipCode)) {
            modifiedBakery = modifiedBakery.registerNewCustomer(lastName,
                address, city, state, zipCode);
        }

        // Get their customer ID
        Integer customerID = modifiedBakery.getCustomerRoll().getCustomerID(
            lastName, address, city, state, zipCode);

        double preOrderRewardsBalance = modifiedBakery.getCustomerRoll()
            .getRewardsPoints(customerID);

        double total = 0;

        boolean notDoneOrdering = true;
        ArrayList<Integer> itemIDs = new ArrayList<Integer>();
        ArrayList<Integer> itemQuantities = new ArrayList<Integer>();

        while (notDoneOrdering) {
            // Gather the items ordered
            System.out.println("Enter an Item ID, or type 'DONE': ");
            Integer itemID;
            String userInput = inputScanner.next();

            // check if they quit
            if (userInput.equals("DONE")) {
                notDoneOrdering = false;
                continue;
            }

            try {
                itemID = Integer.valueOf(userInput);
                getInventory().getItem(itemID);
            }
            catch (Exception e) {
                System.out.println("[ERROR] Invalid Item ID");
                continue;
            }

            if (itemIDs.contains(itemID)) {
                System.out.println("[ERROR] You already added that item.");
                continue;
            }

            System.out.println("How many "
                + getInventory().getItem(itemID).getItemName() + ": ");
            userInput = inputScanner.next();
            Integer itemQuantity = null;
            try {
                itemQuantity = Integer.valueOf(userInput);
            }
            catch (Exception e) {
                System.out.println("[ERROR] Invalid Quantity");
                continue;
            }

            itemIDs.add(itemID);
            itemQuantities.add(itemQuantity);

            total += getInventory().getPrice(itemID) * (double) itemQuantity;
        }

        System.out.println("The total for your order is: " + total);
        System.out.println("Your current available rewards points is: "
            + preOrderRewardsBalance);

        // Get when they are paying
        boolean paid = false;

        boolean validInput = false;
        while (!validInput) {
            System.out.println("-------------");
            System.out.println("1.) Pay Now");
            System.out.println("2.) Pay Later");
            System.out.print("Select [1/2]: ");
            String userInput = inputScanner.next();
            try {
                Integer selection = Integer.valueOf(userInput);
                if (selection.equals(1)) {
                    paid = true;
                    validInput = true;
                }
                else if (selection.equals(2)) {
                    paid = false;
                    validInput = true;
                }
                else {
                    System.out.println("[ERROR] Invalid Input");
                }
            }
            catch (Exception e) {
                System.out.println("[ERROR] Invalid Input");
            }
        }

        // Get the discountUsedOnOrder from customer input
        double discountUsedOnOrder = 0;
        if (preOrderRewardsBalance > 0) {
            validInput = false;
            while (!validInput) {
                System.out
                    .println("How many points would you like to apply to this order (or 0 if none): ");
                String sPointsUsed = inputScanner.next();
                try {
                    discountUsedOnOrder = Double.valueOf(sPointsUsed);
                    if (discountUsedOnOrder < preOrderRewardsBalance
                        && discountUsedOnOrder >= 0) {
                        validInput = true;
                    }
                }
                catch (Exception e) {
                    System.out.println("Not a valid number!");
                }
            }
        }
        discountUsedOnOrder *= -1;

        // Get pickup date
        validInput = false;
        Date dOrderDate = new Date();
        Date dPickupDate = null;
        while (!validInput) {
            // get user input
            System.out.println("Please Submit a pickup date (mm/dd/yyyy): ");
            String userInput = inputScanner.next();
            
            // convert to date object
            SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");
            try {
                dPickupDate = dFormatter.parse(userInput);
                validInput = true;
            }
            catch (Exception e) {
                System.out.println("[ERROR] Invalid input.");
            }
        }

        Integer thisOrderID = getOrderList().getAvailableOrderID();
        for (int i = 0; i < itemIDs.size(); i++) {
            modifiedBakery = modifiedBakery.performTransaction(thisOrderID, customerID, itemIDs.get(i), itemQuantities.get(i), 0.0, discountUsedOnOrder, paid, dOrderDate, dPickupDate);            
        }
        
        return modifiedBakery;

    }

    void viewExistingCustomers() {
        boolean quit = false;
        while (!quit) {
            System.out.println("------------");
            System.out
                .println("1.) Print All Registered Customer Information");
            System.out.println("2.) Print Customer by ID");
            System.out.println("3.) Print All Customers by Last Name");
            System.out.println("4.) Go Back");
            System.out.print("Select [1/2/3/4]: ");
            String userInput = inputScanner.next();

            if (userInput.equals("1")) {
                System.out.println(getCustomerRoll().toString());
                quit = true;
            }
            else if (userInput.equals("2")) {
                System.out.println("------------");
                System.out.print("User ID: ");
                String idInput = inputScanner.next();
                Integer customerID = 0;

                try {
                    customerID = Integer.valueOf(idInput);
                }
                catch (Exception e) {
                    System.out.println("[ERROR] Not a valid input");
                    continue;
                }

                if (isRegisteredCustomer(customerID)) {
                    System.out.println(getCustomerRoll().getCustomer(
                        customerID));
                    System.out.println(getOrderList().getOrdersByCustomerID(
                        customerID));
                    quit = true;
                }
                else {
                    System.out
                        .println("[ERROR] No customer exists with that ID");
                }

            }
            else if (userInput.equals("3")) {
                System.out.println("------------");
                System.out.print("Last Name: ");
                String lastName = inputScanner.next();
                System.out
                    .println(getCustomerByLastName(lastName).toString());
            }
            else if (userInput.equals("4")) {
                quit = true;
            }

            else {
                System.out.println("[ERROR] Invalid input.");
            }
        }
    }

    public Bakery addNewCustomer() {
        System.out.println("Please enter the following customer info:");

        System.out.print("Last Name: ");
        String lastName = inputScanner.next();
        System.out.println();

        System.out.print("Address: ");
        String address = inputScanner.next();
        System.out.println();

        System.out.print("City: ");
        String city = inputScanner.next();
        System.out.println();

        System.out.print("State: ");
        String state = inputScanner.next();
        System.out.println();

        Integer zipCode = 0;
        boolean validZip = false;
        while (!validZip) {
            System.out.print("Zip Code: ");
            String sZipCode = inputScanner.next();
            zipCode = Integer.valueOf(sZipCode);
            validZip = true;

            try {
                zipCode = Integer.valueOf(sZipCode);
            }
            catch (Exception e) {
                System.out.println("Invalid zip code.");
            }
        }
        System.out.println();

        if (!isRegisteredCustomer(lastName, address, city, state, zipCode)) {
            return registerNewCustomer(lastName, address, city, state,
                zipCode);
        }
        else {
            System.out.println("That customer already exists!");
            return this;
        }
    }

    // CHANGE addToInventory
    public Bakery addInventoryItem() {
        System.out.println("Please enter the following Item info:");

        System.out.print("Item Name: ");
        String itemName = inputScanner.next();
        System.out.println();

        System.out.print("Item Category: ");
        String itemCategory = inputScanner.next();
        System.out.println();

        System.out.print("Item Price: ");
        String sItemPrice = inputScanner.next();
        double itemPrice = Double.valueOf(sItemPrice);
        System.out.println();

        if (!isInInventory(itemName, itemCategory)) {
            return addToInventory(itemName, itemCategory, itemPrice);
        }
        else {
            throw new RuntimeException("That inventory item already exists!");
        }
    }

    void viewExistingInventory() {
        System.out.println(getInventory().toString());
    }

    void viewExistingOrders() {
        boolean quit = false;
        while (!quit) {
            System.out.println("------------");
            System.out.println("1.) Print All Orders");
            System.out.println("2.) Print Orders by Specific Customer");
            System.out.println("3.) Print Orders with Specific Order Date");
            System.out.println("4.) Print Orders with Specific Pickup Date");
            System.out.print("Select [1/2/3/4]: ");
            String userInput = inputScanner.next();

            if (userInput.equals("1")) {
                System.out.println(getOrderList().toString());
                quit = true;
            }
            else if (userInput.equals("2")) {
                System.out.println("------------");
                System.out.print("User ID: ");
                String idInput = inputScanner.next();
                Integer customerID = 0;

                try {
                    customerID = Integer.valueOf(idInput);
                }
                catch (Exception e) {
                    System.out.println("[ERROR] Not a valid input");
                    continue;
                }

                if (isRegisteredCustomer(customerID)) {
                    System.out.println(getCustomerRoll().getCustomer(
                        customerID));
                    System.out.println(getOrderList().getOrdersByCustomerID(
                        customerID));
                    quit = true;
                }
                else {
                    System.out
                        .println("[ERROR] No customer exists with that ID");
                }

            }
            else if (userInput.equals("3")) {
                System.out.println("------------");
                System.out.print("");
            }

            else {
                // TODO: invalid entry
            }
        }
    }

    public Bakery updateInventoryItems() {
        // Print entire inventory
        System.out.println(getInventory().toString());

        // Get item ID to be updated - ensure its valid
        boolean validInt = false;
        Integer itemID = -1;
        while (!validInt) {
            System.out.println("Please input Item ID to be updated");

            System.out.print("Item ID: ");
            String sItemID = inputScanner.next();
            try {
                itemID = Integer.valueOf(sItemID);
            }
            catch (Exception e) {
                System.out.println("[ERROR] Not a valid input");
                continue;
            }
            validInt = true;
        }

        if (isInInventory(itemID)) {
            System.out.println("Please enter the following Item info:");

            System.out.print("Item Name: ");
            String itemName = inputScanner.next();
            System.out.println();

            System.out.print("Item Category: ");
            String itemCategory = inputScanner.next();
            System.out.println();

            boolean validDub = false;
            double itemPrice = 0.0;
            while (!validDub) {
                System.out.print("Item Price: ");
                String sItemPrice = inputScanner.next();
                try {
                    itemPrice = Double.valueOf(sItemPrice);
                }
                catch (Exception e) {
                    System.out.println("[ERROR] Not a valid input");
                    continue;
                }
                validDub = true;
            }
            System.out.println();

            return removeFromInventory(itemID).addToInventory(itemID,
                itemName, itemCategory, itemPrice);
        }
        else {
            throw new RuntimeException("That inventory item does not exist!");
        }
    }

    public Bakery updateExistingCustomer() {
        System.out.println(getCustomerRoll().toString());

        System.out.println("Please input User ID to be updated");

        System.out.print("User ID: ");
        String sCustomerID = inputScanner.next();
        Integer customerID = Integer.valueOf(sCustomerID);

        if (isRegisteredCustomer(customerID)) {
            System.out.println("Please enter the following customer info:");

            System.out.print("Last Name: ");
            String lastName = inputScanner.next();
            System.out.println();

            System.out.print("Address: ");
            String address = inputScanner.next();
            System.out.println();

            System.out.print("City: ");
            String city = inputScanner.next();
            System.out.println();

            System.out.print("State: ");
            String state = inputScanner.next();
            System.out.println();

            Integer zipCode = 0;
            boolean validZip = false;
            while (!validZip) {
                System.out.print("Zip Code: ");
                String sZipCode = inputScanner.next();
                zipCode = Integer.valueOf(sZipCode);

                try {
                    zipCode = Integer.valueOf(sZipCode);
                }
                catch (Exception e) {
                    System.out.println("Invalid zip code.");
                    continue;
                }
                validZip = true;
            }
            System.out.println();

            return removeCustomer(customerID).registerNewCustomer(customerID,
                lastName, address, city, state, zipCode);
        }
        else {
            throw new RuntimeException("That user does not exist!");
        }
    }
}
