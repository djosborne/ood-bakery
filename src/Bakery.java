import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import bakery.customer.CustomerRoll;
import bakery.inventory.Inventory;

public class Bakery {
    private Inventory inv;
    private CustomerRoll custRoll;

    Bakery(Inventory inv, CustomerRoll custRoll) {
        this.inv = inv;
        this.custRoll = custRoll;
    }

    Inventory getInventory() {
        return inv;
    }

    CustomerRoll getCustomerRoll() {
        return custRoll;
    }
    
    void setCustomerRoll(CustomerRoll cr) {
    	this.custRoll = cr;
    }
    
    boolean isRegisteredCustomer(String lastName, String address, String city, String state, Integer zipCode) {
        return getCustomerRoll().isReturningCustomer(lastName, address, city, state, zipCode);
    }
    
    boolean isRegisteredCustomer(Integer ID) {
    	return getCustomerRoll().isReturningCustomer(ID);
    }
    
    boolean isInInventory(Integer ID) {
    	return getInventory().containsItem(ID);
    }
    
    // provided ID
    void registerNewCustomer(Integer ID, String lastName, String address, String city, String state, Integer zipCode) {
        setCustomerRoll(getCustomerRoll().addNewCustomer(ID, lastName, address, city, state, zipCode));
    }
    
    // generate ID
    void registerNewCustomer(String lastName, String address, String city, String state, Integer zipCode) {
    	setCustomerRoll(getCustomerRoll().addNewCustomer(lastName, address, city, state, zipCode));
    }

    public Bakery addToInventory(int itemID, String itemName,
        String category, double itemPrice) {
        return new Bakery(getInventory().addToStock(itemID, itemName,
            category, itemPrice), getCustomerRoll());
    }

//    public Bakery performTransaction(int customerID, int orderID, int itemID, boolean paid, Date pickupDate) {
//         Item purchasedItem = getInventory().getItem(itemID);               
//    }
    
    public Bakery performTransaction(int customerID, int orderID, int itemID, boolean paid, Date pickupDate) {
        Item purchasedItem = getInventory().getItem(itemID);               
   }
    

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        Bakery bakeryCtrl = new Bakery(Inventory.emptyInventory(),
            CustomerRoll.emptyRoll());

        /**********************************************************************
         * Gather user input to load the Scanners for inventory/customers
         *********************************************************************/

        Scanner inputScanner = new Scanner(System.in);
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
            userInput = inputScanner.next();
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
                    userInput = inputScanner.next();
                    File ordersFile = new File(userInput);
                    orderScanner = new Scanner(ordersFile);

                    System.out.print("Bakery Inventory Filename: ");
                    userInput = inputScanner.next();
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
            	bakeryCtrl.registerNewCustomer(customerID, lastName, address, city, state, zipCode);
            }
            
            // Register the item if necessary
            if (!bakeryCtrl.isInInventory(bakeryItemID)) {
            	bakeryCtrl.addToInventory(bakeryItemID, bakeryItemName, bakeryItemCategory, price);
            }
            
            // Register the order            
            SimpleDateFormat dFormatter = new SimpleDateFormat("MM/dd/yy");
            Date dPickupDate;
            Date dOrderDate;
            try {
            	dPickupDate = dFormatter.parse(sPickupDate);
            	dOrderDate = dFormatter.parse(sOrderDate);
            }
            catch (Exception e) {
            	
            }
            
            
            
            
             
             /** if (user doesn't exist)
             *     make user
             * get the userID from (LastName Address City State ZipCode)
             * get current rewards balance, save for later (preRewardsBalance)
             * 
             * 
             * 
             * generate a new order
             *    For each item
             *       scan ID
             *       prompt quantity
             *    end
             *    
             *    getTotal()
             *    
             *    print preRewardsBalance, available discount
             *    
             *    if they have >0 available discount, ask how much they want to apply
             *       prompt for DiscountUsedOnOrder
             *       getTotalDue() = getttotal - discount
             *       
             *       add points to customer's rewards (from totalDue)
             *      
             *      
             *    Pay now or bill you?
             *    Generate order date (today)
             *    Prompt for pickup date
             *     
             * 
             * getBakeryItemID(BakeryItemName)
             * 
             * perform transaction (userID,
             * 
             */
        }

    }
}
