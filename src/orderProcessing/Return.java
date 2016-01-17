/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * The class for processing returns
 * @author J. Barclay Walsh and Matt White
 */
public class Return extends Transaction {
    
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
        // Returns
        return false;
    }
    
}
