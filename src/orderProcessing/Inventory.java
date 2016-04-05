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
import static orderProcessing.TransactionMaker.url;

/**
 * Class for managing product inventory
 * @author J. Barclay Walsh
 */
public class Inventory {
    
    // MySQL Connection Settings
    static String url = "jdbc:mysql://108.52.194.58:3306/ist411";
    static String username = "ist411";
    static String password = "cwqx6abVRB82Tt4i8Byb";
    
    private final ArrayList<InventoryItem> theInventoryList;
    
    private static Inventory theInventory;
    
    public static int inventoryAccessed = 0;
    public static int inventoryAccessedForSale = 0;
    
    /**
     * Constructor private to prevent instantiation
     */
    private Inventory() {
        theInventoryList = new ArrayList<>();
    }
    
    /**
     * Return the inventory
     * @return The static/only inventory
     */
    public static Inventory getInventory() {
        if(theInventory == null){
            theInventory = new Inventory();
        }
        Inventory.inventoryAccessed++;
        return theInventory;
    }
    
    /**
     * Return the Inventory ArrayList
     * @return an ArrayList of all inventory items an quantities
     */
    public ArrayList<InventoryItem> getInventoryList() {
        return this.theInventoryList;
    }
    
    /**
     * Add item to inventory
     * @param newItem the InventoryItem to add to theInventoryList
     * @return true if add was successful; false if item failed to add
     */
    public synchronized boolean addItem(InventoryItem newItem) {
        int productID = newItem.getProductID();
        ArrayList<InventoryItem> inventoryList = Inventory.getInventory().getInventoryList();
        if (!inventoryList.stream().noneMatch((inventoryListItem) -> (inventoryListItem.getProductID() == productID))) {
            return false;
        }
        Inventory.getInventory().getInventoryList().add(newItem);
        return true;
    }
    
    /**
     * Remove item from inventory (by item)
     * @param itemToRemove the item to remove from the inventory
     * @return true if deletion was successful; false if it was not
     */
    public synchronized boolean removeItem(InventoryItem itemToRemove) {
        return Inventory.getInventory().getInventoryList().remove(itemToRemove);
    }
    
    /**
     * Get item by itemID
     * @param itemIDToGet the productID of the inventory item to return
     * @return the specified inventory item, or NULL if it does not exist
     */
    public InventoryItem getItemByID(int itemIDToGet) {
        ArrayList<InventoryItem> inventoryList = Inventory.getInventory().getInventoryList();
        for (InventoryItem inventoryListItem : inventoryList) {
            if (inventoryListItem.getProductID() == itemIDToGet) {
                return inventoryListItem;
            }
        }
        
        return null;
    }
    
    /**
     * Print inventory
     */
    public void printInventory() {
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
        System.out.println("Current Store Inventory");
        if(this.theInventoryList.size() > 0) {
            System.out.println("\nProduct ID\tDescription\t\t\t\tUnit Price\tQuantity in Stock");
            this.theInventoryList.stream().forEach((theInventoryItem) -> {
                theInventoryItem.printInventoryItemDetails();
            });
        } else {
            System.out.println("The inventory is empty.");
        }
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
    }
    
    /**
     * Print Inventory (MySQL)
     */
    public void printInventoryMySQL() {
        // Start MySQL Connection
        System.out.println("Connecting to MySQL database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            
            // Create query to get inventory from the remote MySQL database
            Statement stmt = connection.createStatement();
            String query = "SELECT product_id, price, description, quantity FROM inventory;" ;
            ResultSet queryResult = stmt.executeQuery(query);
            
            System.out.println("\n------------------------------------------------------------------------------------------------\n");
            System.out.println("Current Store Inventory");
            System.out.println("\nProduct ID\tDescription\t\t\t\tUnit Price\tQuantity in Stock");
            
            // Fetch MySQL query results
            try {
                while (queryResult.next()) {                    
                    // Print Item Details
                    System.out.println(queryResult.getObject(1) + "\t\t" + queryResult.getObject(3) + "\t\t$" + HelperMethods.priceToString((Double) queryResult.getObject(2)) + "\t\t" + queryResult.getObject(4));
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
    
    /**
     * Print inventory with total inventory value
     */
    public void printInventoryWithInventoryValue() {
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
        System.out.println("Current Store Inventory");
        if(this.theInventoryList.size() > 0) {
            System.out.println("\nProduct ID\tDescription\t\t\t\tUnit Price\tQuantity in Stock");
            double totalValue = 0;
            for (InventoryItem theInventoryItem : this.theInventoryList) {
                theInventoryItem.printInventoryItemDetails();
                totalValue += (theInventoryItem.getPrice() * theInventoryItem.getQuantity());
            }
            System.out.println("\nTotal inventory value: $" + HelperMethods.priceToString(totalValue));
        } else {
            System.out.println("The inventory is empty.");
        }
        System.out.println("\n------------------------------------------------------------------------------------------------\n");
    }
    
}
