/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.util.ArrayList;

/**
 * Class for managing Customers
 * @author J. Barclay Walsh and Matt White
 */
public class CustomerList {
    
    private final ArrayList<Customer> customers;
    
    private static CustomerList theCustomerList;
    
    /**
     * Constructor private to prevent instantiation
     */
    private CustomerList() {
        customers = new ArrayList<>();
    }
    
    /**
     * Return the customers
     * @return The static/only customers
     */
    public static CustomerList getCustomers() {
        if(theCustomerList == null){
            theCustomerList = new CustomerList();
        }
        return theCustomerList;
    }
    
    /**
     * Return the Customers ArrayList
     * @return an ArrayList of all customers items an quantities
     */
    public ArrayList<Customer> getCustomersList() {
        return this.customers;
    }
    
    /**
     * Add Customer to customers
     * @param newCustomer the Customer to add to theCustomersList
     * @return true if add was successful; false if Customer failed to add
     */
    public synchronized boolean addCustomer(Customer newCustomer) {
        int customerID = newCustomer.getCustomerID();
        ArrayList<Customer> customersList = CustomerList.getCustomers().getCustomersList();
        if (!customersList.stream().noneMatch((customersListItem) -> (customersListItem.getCustomerID() == customerID))) {
            return false;
        }
        CustomerList.getCustomers().getCustomersList().add(newCustomer);
        return true;
    }
    
    /**
     * Remove Customer from customers (by Customer)
     * @param customerToRemove the Customer to remove from the customers
     * @return true if deletion was successful; false if it was not
     */
    public synchronized boolean removeCustomer(Customer customerToRemove) {
        return CustomerList.getCustomers().getCustomersList().remove(customerToRemove);
    }
    
    /**
     * Get item by itemID
     * @param itemIDToGet the customerID of the customers item to return
     * @return the specified customers item, or NULL if it does not exist
     */
    public Customer getCustomerByID(int itemIDToGet) {
        ArrayList<Customer> customersList = CustomerList.getCustomers().getCustomersList();
        for (Customer customersListItem : customersList) {
            if (customersListItem.getCustomerID() == itemIDToGet) {
                return customersListItem;
            }
        }
        
        return null;
    }
    
    /**
     * Print customers
     */
    public void printCustomers() {
        this.getCustomersList().stream().forEach((customersListItem) -> {
            System.out.println("Customer ID: " + customersListItem.getCustomerID() + "; Name: " + customersListItem.getFullName());
        });
    }
    
}
