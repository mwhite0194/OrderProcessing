/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.util.ArrayList;

/**
 * Class for managing product inventory
 * @author J. Barclay Walsh and Matt White
 */
public class Inventory {
    
    private final ArrayList<InventoryItem> theInventoryList;
    
    private static Inventory theInventory;
    
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
    public boolean addItem(InventoryItem newItem) {
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
    public boolean removeItem(InventoryItem itemToRemove) {
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
        this.getInventoryList().stream().forEach((inventoryListItem) -> {
            System.out.println("Product ID: " + inventoryListItem.getProductID() + "; Description: " + inventoryListItem.getDescription() + "; Unit Price: " + inventoryListItem.getPrice() + "; Quantity: " + inventoryListItem.getQuantity());
        });
    }
    
}
