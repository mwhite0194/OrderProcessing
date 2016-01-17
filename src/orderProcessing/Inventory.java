/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * Class for managing product inventory
 * @author J. Barclay Walsh and Matt White
 */
public class Inventory {
    
    private final int productID;
    private int quantity;
    
    /**
     * Constructor for Inventory
     * @param newProductID
     * @param newQuantity
     */
    public Inventory(int newProductID, int newQuantity) {
        this.productID = newProductID;
        this.quantity = newQuantity;
    }
    
    // Getters and Setters
    
    /**
     * Get quantity
     * @return InventoryItem's Quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Set quantity
     * @param newQuantity the new quantity for the product's inventory
     */
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
    
    /**
     * Get productID
     * @return InventoryItem's productID
     */
    public int getProductID() {
        return this.productID;
    }
    
}
