/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * Class for objects in the store inventory
 * @author J. Barclay Walsh and Matt White
 */
public class InventoryItem {
    
    private static int nextProductID;
    
    private final int productID; 
    private double productPrice;
    private String productDesc;
    
    public InventoryItem(double newPrice, String newDescription) {
        this.productID = nextProductID;
        InventoryItem.nextProductID++;
        this.productPrice = newPrice;
        this.productDesc = newDescription;
    }
    
    // Getters and setters
    
    /**
     * Get productID
     * @return the InventoryItem's productID
     */
    public int getProductID() {
        return this.productID;
    }
    
    /**
     * Get price
     * @return InventoryItem's Price
     */
    public double getPrice() {
        return this.productPrice;
    }
    
    /**
     * Set price
     * @param newPrice the new price for the product
     */
    public void setPrice(double newPrice) {
        this.productPrice = newPrice;
    }
    
    /**
     * Get product description
     * @return InventoryItem's Description
     */
    public String getDescription() {
        return this.productDesc;
    }
    
    /**
     * Set product description
     * @param newDesc the new description for the product
     */
    public void setDescription(String newDesc) {
        this.productDesc = newDesc;
    }
    
}
