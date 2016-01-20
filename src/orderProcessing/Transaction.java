/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 *
 * @author J. Barclay Walsh and Matt White
 */
public abstract class Transaction {
    
    private static int nextOrderID;
    
    private int orderID;
    private double total;
    private int productID;
    private int quantity;
    
    public abstract boolean processTransaction(int theProductID, int theQuantity, Customer theCustomer);
    
    // Getters (transactions cannot be modified, so no setters)
    
    /**
     * Get next orderID
     * @return the next orderID to use
     */
    public static int getNextOrderID() {
        if (nextOrderID > 0) {
            nextOrderID++;
        } else {
            nextOrderID = 1;
        }
        return nextOrderID;
    }
    
    /**
     * Get orderID
     * @return the orderID
     */
    public abstract int getOrderID();
    
    /**
     * Get total
     * @return the total
     */
    public abstract double getTotal();
    
    /**
     * Get productID
     * @return the productID
     */
    public abstract int getProductID();
    
    /**
     * Get quantity
     * @return the quantity
     */
    public abstract int getQuantity();
    
    /**
     * Print Transaction Details
     */
    public abstract void printTransactionDetails();
    
}
