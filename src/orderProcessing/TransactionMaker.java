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
        
        Customer customer = new Customer("123 Test St.", "Apt. #14", "State College", "PA", "USA", 16801, "Bob", "Smith", 1235555555);
        CustomerList.getCustomers().addCustomer(customer);
        
        InventoryItem testItem = new InventoryItem(34, "Book", 20); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        Sale firstSale = new Sale(0, 4, customer); // (productID, quantity, customer)
        
        System.out.println("Customer Order History: ");
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
        System.out.println();
        
        System.out.print("Would you like to buy, return, or exchange?: ");
        String userOption = scanner.next();
        if (userOption.startsWith("b")) {
            Sale theSale = new Sale(0, 2, CustomerList.getCustomers().getCustomerByID(customer.getCustomerID())); // (productID, quantity, customer)
        }
        if (userOption.startsWith("r")) {
            Return theReturn = new Return(1, 4, CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()));
        }
        if (userOption.startsWith("e")) {
            //Exchange exchange = new Exchange()
            System.out.println("Will be added soon!");
        }
        
        // Print Reciept
        System.out.println("Customer Information: " + CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).getFullName() + " (" + CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).getFullAddress() + ")");
        System.out.println("Item: " + testItem.getDescription() + " - Product ID: " + testItem.getProductID() + " - Unit Price: $" + HelperMethods.priceToString(testItem.getPrice()));
        System.out.println("");
    }
    
}
