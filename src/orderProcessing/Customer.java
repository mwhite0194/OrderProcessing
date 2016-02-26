/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.util.ArrayList;

/**
 * Class for Customer objects in the transaction processing system
 * @author J. Barclay Walsh and Matt White
 */
public class Customer {
    
    private static int nextCustomerID;
    
    private final int customerID;
    private int phone, zip;
    private String address, addressLine2, city, state, country, firstName, lastName;
    private final ArrayList<Transaction> transactionHistory;
    private int transactionsAdded;
   
    /**
     * Constructor for Customer
     * @param newAddress
     * @param newaddressLine2
     * @param newCity
     * @param newCountry
     * @param newState
     * @param newZip
     * @param newFirstName
     * @param newPhoneNumber
     * @param newLastName
     */
    public Customer(String newAddress, String newaddressLine2, String newCity, String newState, String newCountry, int newZip, String newFirstName, String newLastName, int newPhoneNumber) {
        this.customerID = nextCustomerID;
        Customer.nextCustomerID++;
        this.address = newAddress;
        this.addressLine2 = newaddressLine2;
        this.city = newCity;
        this.state = newState;
        this.country = newCountry;
        this.zip = newZip;
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.phone = newPhoneNumber;
        this.transactionHistory = new ArrayList<>();
        this.transactionsAdded = 0;
    }
    
    // Getters and setters
    
    /**
     * Get customerID
     * @return the customer's customerID
     */
    public int getCustomerID() {
        return this.customerID;
    }
    
    /**
     * Get address
     * @return Customer's Address (Line 1)
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * Set address
     * @param newAddress new address for customer
     */
    public synchronized void setAddress(String newAddress) {
        this.address = newAddress;
    }
    
    /**
     * Get address (line 2)
     * @return Customer's Address (Line 1)
     */
    public String getAddressLine2() {
        return this.addressLine2;
    }
    
    /**
     * Set address (line 2)
     * @param newAddressLine2 new address (line 2) for customer
     */
    public synchronized void setAddressLine2(String newAddressLine2) {
        this.addressLine2 = newAddressLine2;
    }
    
    /**
     * Get city
     * @return Customer's City
     */
    public String getCity() {
        return this.city;
    }
    
    /**
     * Set city
     * @param newCity new city for customer
     */
    public synchronized void setCity(String newCity) {
        this.city = newCity;
    }
    
    /**
     * Get state
     * @return Customer's City
     */
    public String getState() {
        return this.state;
    }
    
    /**
     * Set state
     * @param newState new state for customer
     */
    public synchronized void setState(String newState) {
        this.state = newState;
    }
    
    /**
     * Get country
     * @return Customer's Country
     */
    public String getCountry() {
        return this.country;
    }
    
    /**
     * Set country
     * @param newCountry new country for customer
     */
    public synchronized void setCountry(String newCountry) {
        this.country = newCountry;
    }
    
    /**
     * Get zip code
     * @return Customer's Zip Code
     */
    public int getZip() {
        return this.zip;
    }
    
    /**
     * Set zip code
     * @param newZip new state for customer
     */
    public synchronized void setZip(int newZip) {
        this.zip = newZip;
    }
    
    /**
     * Get phone
     * @return Customer's Phone Number
     */
    public int getPhone() {
        return this.phone;
    }
    
    /**
     * Set phone
     * @param newPhone new state for customer
     */
    public synchronized void setPhone(int newPhone) {
        this.phone = newPhone;
    }
    
    /**
     * Get first name
     * @return Customer's First Name
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Set first name
     * @param newFirstName new first name for customer
     */
    public synchronized void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
    
    /**
     * Get last name
     * @return Customer's Last Name
     */
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * Set last name
     * @param newLastName new last name for customer
     */
    public synchronized void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
    
    /**
     * Get full name
     * @return Customer's Full Name
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
    /**
     * Get full address
     * @return Customer's Full Address
     */
    public synchronized String getFullAddress() {
        return this.address + ", " + this.addressLine2 + ", " + this.city + ", " + this.state + ", " + this.zip + ", " + this.country;
    }
    
    /**
     * Add Transaction
     * @param newTransaction the new transaction to add
     */
    public synchronized void addTransaction(Transaction newTransaction) {
        this.transactionHistory.add(newTransaction);
        this.transactionsAdded++;
    }
    
    /**
     * Get the number of transactions added to this customer
     * @return (int) the number of transactions
     */
    public int getTransactionsAdded() {
        return this.transactionsAdded;
    }
    
    /**
     * Get Transaction List
     * @return the list of transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return this.transactionHistory;
    }
    
    /**
     * Validate Transaction By OrderID
     * @param orderIDToGet the orderID to lookup in the customer's order history
     * @return true if customer has the transactionID in their history, false if they do not have it
     */
    public boolean validateTransactionByID(int orderIDToGet) {
        for (Transaction theTransaction : this.transactionHistory) {
            if (theTransaction.getOrderID() == orderIDToGet) {
                return true;
            }
        }
        return false;
        
        //return this.transactionHistory.stream().anyMatch((theTransaction) -> (theTransaction.getProductID() == orderIDToGet));
    }
    
    /**
     * Get Transaction By OrderID
     * @param orderIDToGet the orderID to lookup in the customer's order history
     * @return the transaction if customer has the transactionID in their history, NULL if they do not have it
     */
    public Transaction getTransactionByID(int orderIDToGet) {
        for (Transaction theTransaction : this.transactionHistory) {
            if (theTransaction.getOrderID() == orderIDToGet) {
                return theTransaction;
            }
        }
        return null;
    }
    
    /**
     * Print Order History
     */
    public void printOrderHistory() {
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
        System.out.println("Customer Order History (" + this.getFullName() + "): ");
        if(this.transactionHistory.size() > 0) {
            System.out.println("\nOrder ID\t\tProduct ID\t\t\t\t\tQuantity\tTotal");
            this.transactionHistory.stream().forEach((theTransaction) -> {
                theTransaction.printTransactionDetails();
                //System.out.println("Order ID: " + theTransaction.getOrderID() + "; Product ID: " + theTransaction.getProductID() + "; Total: $" + HelperMethods.priceToString(theTransaction.getTotal()));
            });
        } else {
            System.out.println("This customer has not placed any orders yet.");
        }
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
    }
    
}
