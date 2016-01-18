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
        
        Customer customer = new Customer("123", "456", "state", "pa", "usa", 16801, "bob", "smith", 5555);
        InventoryItem item = new InventoryItem(34, "book", 2);
        
        System.out.print("Would you like to buy, return, or exchange?: ");
        String userOption = scanner.next();
        if (userOption.startsWith("b"))
        {
            Sale sale = new Sale(3, 3, customer);
        }
        if (userOption.startsWith("r"))
        {
            //Return return = new Return();
        }
        if (userOption.startsWith("e"))
        {
            //Exchange exchange = new Exchange()
        }
        System.out.print("Enter amount to pay ");


        
        //reciept
        System.out.println("Customer Information: " + customer.getFirstName() + customer.getFullAddress());
        System.out.println("Item: " + item.getDescription() + item.getProductID() + item.getPrice());
        System.out.println("Ite: ");
        System.out.println("");
    }
    
}
