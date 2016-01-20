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
     * Private - can only be called from processTransaction() method
     * @param newProductID
     * @param newQuantity
     * @param newCustomer
     */
    public Return(int newProductID, int newQuantity, Customer newCustomer) {
        this.processTransaction(newProductID, newQuantity, newCustomer);
    }
    
    /**
     * Process the return
     * @param theOrderID the orderID of the order being returned
     * @param theQuantity the quantity returned
     * @param theCustomer the customer ordering the product(s)
     * @return true if successful; false if not
     */
    @Override
    public final boolean processTransaction(int theOrderID, int theQuantity, Customer theCustomer) {
        System.out.println("LOOK HERE");
        //CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).printOrderHistory();
        if (CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).validateTransactionByID(theOrderID)) {
            // Customer has placed this order; process return
            
            // TODO: Accept returns of less than the total (max) quantity
            
            // Set return variables
            int returnProductID = theCustomer.getTransactionByID(theOrderID).getProductID();
            int returnQuantity = theCustomer.getTransactionByID(theOrderID).getQuantity();
            double returnTotal = theCustomer.getTransactionByID(theOrderID).getTotal();
            String returnDescription = Inventory.getInventory().getItemByID(returnProductID).getDescription();
            
            // Return the quantity of the item returned to the store inventory
            Inventory.getInventory().getItemByID(returnProductID).setQuantity(Inventory.getInventory().getItemByID(returnProductID).getQuantity() + returnQuantity);
            
            // Generate return transaction and add to transaction history
            int newOrderID = Transaction.getNextOrderID();
            this.orderID = newOrderID;
            this.total = -1 * returnTotal;
            this.productID = returnProductID;
            this.quantity = returnQuantity;
            CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).addTransaction(this);
            System.out.println("Valid transaction. Successful reuturn of " + returnQuantity + " " + returnDescription + "(s) from " + theCustomer.getFullName() + " for a total refund of $" + HelperMethods.priceToString((-1 * this.total)) + ".");
            return true;
        } else {
            // Invalid transaction; inform customer
            System.out.println("Invalid return. Invalid order or incorrect customer.");
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
     * Print transaction details
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("\nTransaction Details:\nOrder ID: " + this.orderID + "; Total: " + this.total + "; Product ID: " + this.productID + "; Quantity: " + this.quantity + "\n");
    }
    
}
