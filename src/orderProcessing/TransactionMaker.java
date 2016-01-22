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
        boolean userIsActive = true;
        
        Customer customer = new Customer("123 Test St.", "Apt. #14", "State College", "PA", "USA", 16801, "Bob", "Smith", 1235555555);
        CustomerList.getCustomers().addCustomer(customer);
        
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
        
        InventoryItem testItem = new InventoryItem(34, "Green Book", 20); // (price, description, quantity)
        Inventory.getInventory().addItem(testItem);
        Sale firstSale = new Sale(0, 4, customer); // (productID, quantity, customer)
        
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
        
        System.out.print("Would you like to [b]uy, [r]eturn, or [e]xchange?: ");
        String userOption = scanner.next();
        
        //while (userIsActive == true) {
            if (userOption.startsWith("b")) {
                Sale theSale = new Sale(0, 45, CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()));
                if (theSale.equals(false)) {
                    // Offer to allow sale of entire current stock instead
                    String purchaseRemaining = scanner.next();
                    if (purchaseRemaining.startsWith("y")) {
                        userIsActive = false; 
                        Sale theSale2 = new Sale(0, (Inventory.getInventory().getItemByID(0).getQuantity()),
                                CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()));
                    } else {
                        userIsActive = false;
                        return;
                    }    
                    //break;
                }
            } else if (userOption.startsWith("r")) {
                /*if (CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).validateTransactionByID(0)) {
                    System.out.println("Valid");
                } else {
                    System.out.println("Invalid");
                }*/
                Return theReturn = new Return(1, 4, CustomerList.getCustomers().getCustomerByID(customer.getCustomerID())); // (orderID, quantity, customer)
                userIsActive = false;
            } else if (userOption.startsWith("e")) {
                //Exchange exchange = new Exchange()
                System.out.println("Will be added soon!");
                userIsActive = false;
            }
        //}
        
        // Print Reciept
        //System.out.println("Customer Information: " + CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).getFullName() + " (" + CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).getFullAddress() + ")");
        //System.out.println("Item: " + testItem.getDescription() + " - Product ID: " + testItem.getProductID() + " - Unit Price: $" + HelperMethods.priceToString(testItem.getPrice()));
        //System.out.println("");
        
        CustomerList.getCustomers().getCustomerByID(customer.getCustomerID()).printOrderHistory();
    }
    
}
