/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * The class for processing new sale transactions
 * @author J. Barclay Walsh and Matt White
 */
public class Sale extends Transaction {
    
    private int orderID;
    private double total;
    private int productID;
    private int quantity;
    
    /**
     * Private - can only be called from processTransaction() method
     * @param newProductID
     * @param newQuantity
     * @param newCustomer
     */
    public Sale(int newProductID, int newQuantity, Customer newCustomer) {
        this.processTransaction(newProductID, newQuantity, newCustomer);
    }
    
    /**
     * Process the return
     * @param theProductID the ID of the purchased product
     * @param theQuantity the quantity of the product purchased
     * @param theCustomer the customer ordering the product(s)
     * @return true if successful; false if not
     */
    @Override
    public final boolean processTransaction(int theProductID, int theQuantity, Customer theCustomer) {
        if (Inventory.getInventory().getItemByID(theProductID).getQuantity() >= theQuantity) {
            // Valid transaction; deduct quantity and process order
            Inventory.getInventory().getItemByID(theProductID).setQuantity(Inventory.getInventory().getItemByID(theProductID).getQuantity() - theQuantity);
            int newOrderID = Transaction.getNextOrderID();
            this.orderID = newOrderID;
            this.total = (Inventory.getInventory().getItemByID(theProductID).getPrice()) * (theQuantity);
            this.productID = theProductID;
            this.quantity = theQuantity;
            theCustomer.addTransaction(this);
            return true;
        } else {
            // Invalid transaction; inform customer
            System.out.println("Invalid transaction. Quantity not available.");
            // TODO: Offer to allow sale of entire current stock instead
            return false;
        }
    }
    
}
