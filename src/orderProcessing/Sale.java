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
    private int type;
    
    /**
     * Create new Sale
     * @param newProductID
     * @param newQuantity
     * @param newCustomer
     */
    public Sale(int newProductID, int newQuantity, Customer newCustomer) {
        this.processTransaction(newProductID, newQuantity, newCustomer);
    }
    
    /**
     * Process the sale
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
            this.type = 0; // 0 = sale
            
            CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).addTransaction(this);
            System.out.println("Valid transaction. Successful sale of " + theQuantity + " " + Inventory.getInventory().getItemByID(theProductID).getDescription() + "(s) to " + theCustomer.getFullName() + " for a total of $" + HelperMethods.priceToString(this.total) + "." + " (Order ID: " + this.orderID + ")");
            return true;
        } else {
            // Offer to allow sale of entire current stock instead
            System.out.println("The quantity of " + Inventory.getInventory().getItemByID(theProductID).getDescription() + " you requested is not available. Would you like to purchase the remaining stock (" + Inventory.getInventory().getItemByID(theProductID).getQuantity() + ")? [y/n]");
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
        System.out.println(this.orderID + " (sale)\t\t" + this.productID + " (" + Inventory.getInventory().getItemByID(this.productID).getDescription() + ")\t\t" + this.quantity + "\t\t$" + HelperMethods.priceToString(this.total));
    }
    
}
