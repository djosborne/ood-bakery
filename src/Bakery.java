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

public class Bakery {
    private Inventory inv;
    private CustomerRoll custRoll;
//    private OrderRoll orderRoll;

    private Scanner inputScanner = new Scanner(System.in);

    Bakery(Inventory inv, CustomerRoll custRoll) { // , OrderRoll orderRoll) {
        this.inv = inv;
        this.custRoll = custRoll;
//        this.orderRoll = orderRoll;
    }

    private Inventory getInventory() {
        return inv;
    }

    private CustomerRoll getCustomerRoll() {
        return custRoll;
    }

//    private OrderRoll getOrderRoll() {
//        return orderRoll;
//    }

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

    // provided ID
    void registerNewCustomer(Integer ID, String lastName, String address,
        String city, String state, Integer zipCode) {
        setCustomerRoll(getCustomerRoll().addNewCustomer(ID, lastName,
            address, city, state, zipCode));
    }

    // generate ID
    Bakery registerNewCustomer(String lastName, String address, String city,
        String state, Integer zipCode) {
        return new Bakery(getInventory(), getCustomerRoll().addNewCustomer(
            lastName, address, city, state, zipCode)); //, getOrderRoll());
    }

    public Bakery addToInventory(int itemID, String itemName,
        String category, double itemPrice) {
        return new Bakery(getInventory().addToStock(itemID, itemName,
            category, itemPrice), getCustomerRoll()); // , getOrderRoll());
    }

    // public Bakery performTransaction(int customerID, int orderID, int itemID,
    // boolean paid, Date pickupDate) {
    // Item purchasedItem = getInventory().getItem(itemID);
    // }

    public Bakery performTransaction(Integer orderID, int customerID,
        int itemID, int quantity, double loyaltyAtTimeOfOrder,
        double discountUsedOnOrder, boolean paid, Date orderDate,
        Date pickupDate) {

        return new Bakery(getInventory(), getCustomerRoll());// , getOrderRoll()
//            .addOrder(orderID, customerID, itemID, quantity,
//                loyaltyAtTimeOfOrder, discountUsedOnOrder, paid, orderDate,
//                pickupDate));
    }

    public void save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            ArrayList<Order> allOrders = getCustomerRoll().getAllOrders();

            SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");

            fw.write("CustomerID\tLastName\tAddress\tCity\tState\tZipCode\tOrderID\tPaid?\tOrderDate\tPickupDate\tBakeryItemID\tBakeryItemName\tBakeryItemCategory\tQuantity\tPrice\tTotal\tDiscountUsedOnOrder\tTotalDue\tAvailableDiscout\tCurrentLoyalty\n");

            for (Order o : allOrders) {

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

                double total = 0;
                for (Order customerOrder : getCustomerRoll()
                    .getOrdersWithOrderId(o.getOrderID())) {
                    total += customerOrder.getTotal();
                }
                /*
                 * get all orders with same ID from a user add all totals
                 * together pritn that total
                 */

                fw.write(Double.toString(total));
                fw.write("\t");
                fw.write(Double.toString(o.getDiscountUsedOnOrder()));
                fw.write("\t");

                fw.write(Double.toString(total
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
            CustomerRoll.emptyRoll());

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
                bakeryCtrl.registerNewCustomer(customerID, lastName, address,
                    city, state, zipCode);
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
             * if they have >0 available discount, ask how much they want to
             * apply prompt for DiscountUsedOnOrder getTotalDue() = getttotal -
             * discount
             * 
             * add points to customer's rewards (from totalDue)
             * 
             * 
             * Pay now or bill you? Generate order date (today) Prompt for
             * pickup date
             * 
             * 
             * getBakeryItemID(BakeryItemName)
             * 
             * perform transaction (userID,
             * 
             */
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
//                addNewOrder();
            }
            else if (userInput.equals("2")) {
//                viewExistingOrders();
            }
            else if (userInput.equals("3")) {
//                updateExistingOrders();
            }
            else if (userInput.equals("4")) {
                bakeryCtrl.addNewCustomer();
            }
            else if (userInput.equals("5")) {
//                viewExistingCustomers();
            }
            else if (userInput.equals("6")) {
//                updateExistingCustomer();
            }
            else if (userInput.equals("7")) {
//                addInventoryItem();
            }
            else if (userInput.equals("8")) {
//                viewInventoryItems();
            }
            else if (userInput.equals("9")) {
//                updateInventoryItems();
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
    
    public void addNewCustomer() {
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
        
        System.out.print("Zip Code: ");
        String sZipCode = inputScanner.next();
        Integer zipCode = Integer.valueOf(sZipCode);
        System.out.println();
        
        if (!isRegisteredCustomer(lastName, address, city, state, zipCode)) {
            registerNewCustomer(lastName, address,
                city, state, zipCode);
        }
    }
}
