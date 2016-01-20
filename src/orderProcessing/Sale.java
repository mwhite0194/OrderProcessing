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
            
            this.printTransactionDetails();
            
            CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).addTransaction(this);
            System.out.println("Valid transaction. Successful sale of " + theQuantity + " " + Inventory.getInventory().getItemByID(theProductID).getDescription() + "(s) to " + theCustomer.getFullName() + " for a total of $" + HelperMethods.priceToString(this.total) + "." + " (Order ID: " + this.orderID + ")");
            return true;
        } else {
            // Invalid transaction; inform customer
            System.out.println("Invalid transaction. Quantity not available.");
            // TODO: Offer to allow sale of entire current stock instead         
            System.out.println("Would you like to make purchase the remaining quantity of: " + 
                    Inventory.getInventory().getItemByID(theProductID).getQuantity() + "?");
            return false;   
        }
    }
    
    
    /**
     * Print transaction details
     */
    @Override
    public void printTransactionDetails() {
        //System.out.println("\nTransaction Details:\nOrder ID: " + this.orderID + "; Total: " + this.total + "; Product ID: " + this.productID + "; Quantity: " + this.quantity + "\n");
        System.out.println("Order ID: " + this.orderID + "; Product ID: " + this.productID + " (" + Inventory.getInventory().getItemByID(this.productID).getDescription() + "); Quantity: $" + this.quantity + "; Total: $" + HelperMethods.priceToString(this.total));
    }
    
}
