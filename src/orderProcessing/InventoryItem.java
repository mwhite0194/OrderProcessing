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
    private int quantity;
    
    /**
     * Constructor for InventoryItem
     * @param newPrice
     * @param newDescription 
     * @param newQuantity 
     */
    public InventoryItem(double newPrice, String newDescription, int newQuantity) {
        if(nextProductID == 0) {
            // First product, start with ID 1001
            nextProductID = 1001;
        }
        this.productID = nextProductID;
        InventoryItem.nextProductID++;
        this.productPrice = newPrice;
        this.productDesc = newDescription;
        this.quantity = newQuantity;
    }
    
    /**
     * Constructor for InventoryItem (no default quantity)
     * @param newPrice
     * @param newDescription 
     */
    public InventoryItem(double newPrice, String newDescription) {
        this.productID = nextProductID;
        InventoryItem.nextProductID++;
        this.productPrice = newPrice;
        this.productDesc = newDescription;
        this.quantity = 0;
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
    public synchronized void setPrice(double newPrice) {
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
    public synchronized void setDescription(String newDesc) {
        this.productDesc = newDesc;
    }
    
    /**
     * Get product inventory quantity
     * @return InventoryItem's Quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Set product inventory quantity
     * @param newQuantity the new quantity for the product
     */
    public synchronized void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
    
    /**
     * Print InventoryItem details
     */
    public void printInventoryItemDetails() {
        System.out.println(this.productID + "\t\t" + this.productDesc + "\t\t$" + HelperMethods.priceToString(this.productPrice) + "\t\t" + this.quantity);
    }
    
}
