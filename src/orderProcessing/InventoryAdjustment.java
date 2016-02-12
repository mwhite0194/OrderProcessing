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
    private int type;
    
    /**
     * Private - can only be called from processTransaction() method
     * @param newProductID
     * @param newQuantityToAdjust
     * @param newCustomer the employee responsible for the adjustment
     */
    public InventoryAdjustment(int newProductID, int newQuantityToAdjust, Customer newCustomer) {
        this.processTransaction(newProductID, newQuantityToAdjust, newCustomer);
    }
    
    /**
     * Process the return
     * @param theProductID the ID of the purchased product
     * @param theQuantityToAdjust the quantity of the product purchased
     * @param theEmployee the employee responsible for the adjustment
     * @return true if successful; false if not
     */
    @Override
    public synchronized final boolean processTransaction(int theProductID, int theQuantityToAdjust, Customer theEmployee) {
        // Inventory adjustments
        if (Inventory.getInventory().getItemByID(theProductID) != null) {
            if (theQuantityToAdjust < 0) {
                if (Inventory.getInventory().getItemByID(theProductID).getQuantity() < (-1 * theQuantityToAdjust)) {
                    // Can't subtract more than you have - invalid transaction
                    System.out.println("Invalid inventory adjustment - the new inventory level cannot be less than zero!");
                    return false;
                }
            }
            // Generate inventory adjustment transaction and add to transaction history
            int newOrderID = Transaction.getNextOrderID();
            this.orderID = newOrderID;
            this.total = 0;
            this.productID = theProductID;
            this.quantity = theQuantityToAdjust;
            this.type = 1; // 3 = inventory adjustment
            
            // Adjust the inventory
            Inventory.getInventory().getItemByID(theProductID).setQuantity(Inventory.getInventory().getItemByID(theProductID).getQuantity() + theQuantityToAdjust);
            
            // Add the transaction
            CustomerList.getCustomers().getCustomerByID(theEmployee.getCustomerID()).addTransaction(this);
            System.out.println("Valid transaction. Successful inventory adjustment of " + theQuantityToAdjust + " " + Inventory.getInventory().getItemByID(theProductID).getDescription() + "(s) by " + theEmployee.getFullName() + "; there are now " + Inventory.getInventory().getItemByID(theProductID).getQuantity() + " in stock.");
            return true;
        } else {
            // Invalid productID
            System.out.println("Invalid inventory adjustment - the product ID does not match any product in the system.");
            return false;
        }
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
     * Get type
     * @return the type (0 = sale; 1 = return; 2 = exchange; 3 = inventory adjustment)
     */
    @Override
    public int getType() {
        return this.type;
    }
    
    /**
     * Print transaction details
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("\nTransaction Details:\nOrder ID: " + this.orderID + "; Total: " + this.total + "; Product ID: " + this.productID + "; Quantity: " + this.quantity + "\n");
    }
    
}
