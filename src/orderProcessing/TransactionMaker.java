/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class for demoing the system
 * @author J. Barclay Walsh and Matt White
 */
public class TransactionMaker {
    
    
    public static void main(String[] args) {
        
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
        
        // Generate first sample sale
        Sale firstSale = new Sale(1001, 4, CustomerList.getCustomers().getCustomerByID(0)); // (productID, quantity, customer)
        
        CustomerList.getCustomers().getCustomerByID(0).printOrderHistory();
        
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
        CustomerThread transaction1 = new CustomerThread(0, 1001, 1, 0);
        CustomerThread transaction2 = new CustomerThread(0, 1001, 2, 0);
        CustomerThread transaction3 = new CustomerThread(0, 1001, 3, 0);
        transaction1.start();
        transaction2.start();
        transaction3.start();
        
        // Wait! Concurrent modification error for the serializable classes if you don't wait for the threads to finish. Yes, this should be done a better way.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            System.out.println("Failed to wait.");
        }
        
        CustomerList.getCustomers().getCustomerByID(0).printOrderHistory();
            
        Inventory.getInventory().printInventoryWithInventoryValue();
        
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
