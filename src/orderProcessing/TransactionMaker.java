/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class for demoing the system
 * @author J. Barclay Walsh
 */
public class TransactionMaker {
    
    
    public static void main(String[] args) {
        
        long startTime = System.nanoTime();
        
        // Start MySQL Connection
        String url = "jdbc:mysql://108.52.194.58:3306/ist411";
        String username = "ist411";
        String password = "cwqx6abVRB82Tt4i8Byb";

        System.out.println("Connecting to MySQL database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        
        // Test the order processing system
        Scanner scanner = new Scanner(System.in);
        boolean purchaseRemaining = false;
        
        // Generate sample customers        
        generateSampleCustomers();
        // Print customer list
        CustomerList.getCustomers().printCustomers();
        
        // Generate sample products
        generateSampleProducts();
        // Print initial inventory
        Inventory.getInventory().printInventoryWithInventoryValue();
        
        // Generate first sample sale (4 Movo WS3s @ $12.95/ea)
        //Sale firstSale = new Sale(1001, 4, CustomerList.getCustomers().getCustomerByID(0)); // (productID, quantity, customer)
        
        //CustomerList.getCustomers().getCustomerByID(0).printOrderHistory();
        
        /*System.out.print("Would you like to [b]uy, [r]eturn, [e]xchange, or make an inventory [a]djustment?: ");
        String userOption = scanner.next();
        
        if (userOption.startsWith("b")) {
            int productIDtoBuy = 1001;
            Sale theSale = new Sale(productIDtoBuy, 45, CustomerList.getCustomers().getCustomerByID(0));
            // If the product ID is 0, the sale failed (sale transaction not created)
            if (theSale.getProductID() == 0) {
                purchaseRemaining = true;
                //System.out.println("The quantity of " + Inventory.getInventory().getItemByID(productIDtoBuy).getDescription() + " you requested is not available. Would you like to purchase the remaining stock (" + Inventory.getInventory().getItemByID(productIDtoBuy).getQuantity() + ")?");
            }
        } else if (userOption.startsWith("r")) {
            Return theReturn = new Return(1, 3, CustomerList.getCustomers().getCustomerByID(0)); // (orderID, quantity, customer)
        } else if (userOption.startsWith("e")) {
            Exchange theExchange = new Exchange(1, 1, CustomerList.getCustomers().getCustomerByID(0)); // (orderID, defective (0 or 1), customer)
        } else if (userOption.startsWith("a")) {
            InventoryAdjustment theInventoryAdjustment = new InventoryAdjustment(1001, -16, CustomerList.getCustomers().getCustomerByID(0)); // (productID, quantity to adjust (+/-), employee)
        }
            
        if (purchaseRemaining) {
            String test = scanner.next();
            if (test.startsWith("y")) {
                Sale theSale2 = new Sale(1001, (Inventory.getInventory().getItemByID(1001).getQuantity()),
                        CustomerList.getCustomers().getCustomerByID(0));
            } else {
                System.out.println("Order cancelled.");
            }
        } */
        
        
        // THREADED VERSION (all sales to Bob Smith)
        // TODO: Answer quantity unavailable message automatically
        CustomerThread transactionThread;
        
        // 100 random transactions
        /*for (int i = 0; i < 100; i++) {
            int itemID = HelperMethods.randomInteger(1001, 1010);
            int quantity = HelperMethods.randomInteger(1, 5);
            transactionThread = new CustomerThread(0, itemID, quantity, 0);
            transactionThread.start();
        }*/
        
        // 100 identical transactions (to sequential customers)
        /*for (int i = 0; i < 100; i++) {
            int customerID = i+1;
            int itemID = 1007;
            int quantity = 1;
            transactionThread = new CustomerThread(0, itemID, quantity, customerID);
            
            //TODO: Sometime have a return of the same transaction a random interval later
            
            transactionThread.start();
        }*/
        
        // 50,000 identical transactions (to random customers)
        CustomerThread customerThread = new CustomerThread(4000);
		
        synchronized(customerThread) {
            try {
                customerThread.start();
                customerThread.wait();
            }
            catch(InterruptedException e) {
                System.out.println("Interrupted!");
            }
        }
        
        //CustomerList.getCustomers().getCustomerByID(0).printOrderHistory();

        //Inventory.getInventory().printInventoryWithInventoryValue();
        
        System.out.println("Transactions added to the customer (Bob Smith): " + CustomerList.getCustomers().getCustomerByID(0).getTransactionsAdded());
        System.out.println("getInventory() was accessed " + Inventory.inventoryAccessed + " times.");
        System.out.println("getInventory() was accessed for sales " + Inventory.inventoryAccessedForSale + " times.");
        System.out.println("Melatonin item's quantity was set " + Inventory.getInventory().getItemByID(1007).itemQuantitySet + " times.");
        //CustomerList.getCustomers().getCustomerByID(0).printOrderHistory();
        
        long runTime = System.nanoTime() - startTime;
        
        System.out.println("Total run time: " + (runTime/1000000) + " miliseconds");
        
    }
    
    /**
     * Generate Sample Products
     */
    public static void generateSampleProducts() {
        InventoryItem testItem = new InventoryItem(12.95, "Movo WS3 Dead Cat Windscreen", 28); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(22.07, "Shimano UN55 Bracket\t", 12); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(10.40, "Nag Champa Incense Sticks", 120); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(6.44, "99% Isopropyl Alcohol (Pint)", 223); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(12.99, "Blue Racquetballs (3-pack)", 33); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(49.95, "Photive Bluetooth Earbuds", 43); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(16.77, "Melatonin (3mg)\t\t", 430); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(8.36, "Ahmad English Tea #1\t", 7); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(15.95, "Vertical Vortex Toy\t", 70); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
        testItem = new InventoryItem(13.32, "Seirus Innovation 8030\t", 14); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        
    }
    
    /**
     * Generate Sample Customers
     */
    public static void generateSampleCustomers() {
        // The main test customer we use in the demo
        Customer customer = new Customer("123 Test St.", "Apt. #14", "State College", "PA", "USA", 16801, "Bob", "Smith", 1235555555);
        CustomerList.getCustomers().addCustomer(customer);
        
        // Generic test customers
        for(int i = 0; i < 100; i++) {
            customer = new Customer(i + " Test St.", "Apt. #" + (int)(i/5), "State College", "PA", "USA", 16801, "Test", "Customer " + (i+1), 1235555555);
            CustomerList.getCustomers().addCustomer(customer);
        }
    }
    
}
