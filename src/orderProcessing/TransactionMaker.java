/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.util.Scanner;

/**
 * The class for demoing the system
 * @author J. Barclay Walsh and Matt White
 */
public class TransactionMaker {
    
    
    public static void main(String[] args) {
        // Test the order processing system
        Scanner scanner = new Scanner(System.in);
        boolean purchaseRemaining = false;
        
        // Print initial inventory
        Inventory.getInventory().printInventoryWithInventoryValue();
        
        // Generate sample customers        
        Customer customer = new Customer("123 Test St.", "Apt. #14", "State College", "PA", "USA", 16801, "Bob", "Smith", 1235555555);
        CustomerList.getCustomers().addCustomer(customer);
        
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
        
        // Generate sample products
        generateSampleProducts();
        
        Sale firstSale = new Sale(0, 4, customer); // (productID, quantity, customer)
        
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
        
        System.out.print("Would you like to [b]uy, [r]eturn, or [e]xchange?: ");
        String userOption = scanner.next();
        
            if (userOption.startsWith("b")) {
                Sale theSale = new Sale(0, 45, CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()));
                if (theSale.equals(false)) {
                   purchaseRemaining = true;
                }
            } else if (userOption.startsWith("r")) {
                /*if (CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).validateTransactionByID(0)) {
                    System.out.println("Valid");
                } else {
                    System.out.println("Invalid");
                }*/
                Return theReturn = new Return(1, 4, CustomerList.getCustomers().getCustomerByID(customer.getCustomerID())); // (orderID, quantity, customer)
            } else if (userOption.startsWith("e")) {
                //Exchange exchange = new Exchange()
                System.out.println("Will be added soon!");
            }
            
           if (purchaseRemaining = true) {
            String test = scanner.next();
            if (test.startsWith("y")) {
                Sale theSale2 = new Sale(0, (Inventory.getInventory().getItemByID(0).getQuantity()),
                        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()));
            } else {
                return;
            }
        
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
        
        Inventory.getInventory().printInventoryWithInventoryValue();
    }
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
    
}
