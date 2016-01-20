/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * The class for processing inventory adjustment transactions
 * @author J. Barclay Walsh and Matt White
 */
public class InventoryAdjustment extends Transaction {
    
    private int orderID;
    private double total;
    private int productID;
    private int quantity;
    
    /**
     * Process the return
     * @param theProductID the ID of the purchased product
     * @param theQuantity the quantity of the product purchased
     * @param theCustomer
     * @return true if successful; false if not
     */
    @Override
    public boolean processTransaction(int theProductID, int theQuantity, Customer theCustomer) {
        // Inventory adjustments
        return false;
    }
    
    /**
     * Get orderID
     * @return the orderID
     */
    @Override
    public int getOrderID() {
        return this.orderID;
    }
    
    /**
     * Get total
     * @return the total
     */
    @Override
    public double getTotal() {
        return this.total;
    }
    
    /**
     * Get productID
     * @return the productID
     */
    @Override
    public int getProductID() {
        return this.productID;
    }
    
    /**
     * Get quantity
     * @return the quantity
     */
    @Override
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Print transaction details
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("\nTransaction Details:\nOrder ID: " + this.orderID + "; Total: " + this.total + "; Product ID: " + this.productID + "; Quantity: " + this.quantity + "\n");
    }
    
}
