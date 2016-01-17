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

        //not sure if he wants input????
        /*
        System.out.print("Enter your adress: ");
        String address = scanner.next();
        System.out.print("Enter your address: ");
        String address2 = scanner.next();
        System.out.print("Enter your city: ");
        String city = scanner.next();
        System.out.print("Enter your state: ");
        String state = scanner.next();
        System.out.print("Enter your country: ");
        String country = scanner.next();
        System.out.print("Enter your zip: ");
        int zip = scanner.nextInt();
        System.out.print("Enter your first name: ");
        String firstName = scanner.next();
        System.out.print("Enter your last name: ");
        String lastName = scanner.next();
        System.out.print("Enter your phone: ");
        int phone = scanner.nextInt();
        Customer customer = new Customer(address, address2, city, state, country, zip, firstName, lastName, phone);
        */
        
        Customer customer = new Customer("123", "456", "state", "pa", "usa", 16801, "bob", "smith", 5555);

        System.out.print("Enter price of item ");
        int price = scanner.nextInt();
        System.out.print("Enter amount paid ");
        int paid = scanner.nextInt();
        double change = price - paid;
        change = change % 100;

        System.out.println("Customer Information: " + customer.getFirstName() + customer.getFullAddress());
        System.out.println("Price of Inventory Item: ");
        System.out.println("The Amount Given by Customer: ");
        System.out.println("Amount of Change Due to Customer: ");
        System.out.println("");
    }
    
}
