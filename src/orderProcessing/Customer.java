/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static orderProcessing.Inventory.url;
import static orderProcessing.TransactionMaker.url;

/**
 * Class for Customer objects in the transaction processing system
 * @author J. Barclay Walsh
 */
public class Customer {
    
    // MySQL Connection Settings
    static String url = "jdbc:mysql://108.52.194.58:3306/ist411";
    static String username = "ist411";
    static String password = "cwqx6abVRB82Tt4i8Byb";
    
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
        
        // Add to MySQL database
        // Start MySQL Connection
        System.out.println("Connecting to MySQL database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            
            // Create query to get inventory from the remote MySQL database
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO transaction(order_id, customer_id, total, product_id, quantity, type) VALUES (" + newTransaction.getOrderID() + ", " + this.getCustomerID() + ", " + newTransaction.getTotal() + ", " + newTransaction.getProductID() + ", " + newTransaction.getQuantity() + ", " + newTransaction.getType() + ");" ;
            stmt.executeUpdate(query);
            
            System.out.println("Transaction successfully added to the database!");
            
            connection.close(); // close the MySQL connection
        } catch (SQLException e) {
            throw new IllegalStateException("An error occurred when connecting to the database!", e);
        }
        
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
    
    /**
     * Print Order History (MySQL)
     */
    public void printOrderHistoryMySQL() {
        // Start MySQL Connection
        System.out.println("Connecting to MySQL database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            
            // Create query to get transactions for this customer from the remote database
            Statement stmt = connection.createStatement();
            String query = "SELECT transaction.order_id, transaction.total, transaction.product_id, transaction.quantity, transaction.type, transaction_type.name, inventory.description FROM transaction, transaction_type, inventory WHERE inventory.product_id = transaction.product_id AND transaction.customer_id = \'" + this.customerID + "\' AND transaction_type.type_id = transaction.type ORDER BY transaction.order_id;";
            ResultSet queryResult = stmt.executeQuery(query);
            
            System.out.println("\n------------------------------------------------------------------------------------------------\n");
            System.out.println("Customer Order History (" + this.getFullName() + "): ");
            System.out.println("\nOrder ID\t\tProduct ID\t\t\t\t\tQuantity\tTotal");
            
            // Fetch MySQL query results
            try {
                while (queryResult.next()) {
                    System.out.println(queryResult.getObject(1) + " (" + queryResult.getObject(6) + ")\t\t" + queryResult.getObject(3) + " (" + queryResult.getObject(7) + ")\t\t" + queryResult.getObject(4) + "\t\t$" + HelperMethods.priceToString((Double) queryResult.getObject(2)));
                }
            } finally {
                try { 
                    queryResult.close(); 
                } catch (Throwable ignore) { 
                    /* Ignore */
                }
            }
            
            System.out.println("\n------------------------------------------------------------------------------------------------\n");
            
            connection.close(); // close the MySQL connection
        } catch (SQLException e) {
            throw new IllegalStateException("An error occurred when connecting to the database!", e);
        }
    }
    
}
