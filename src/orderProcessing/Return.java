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
    private int type;
    
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
        //CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).printOrderHistory();
        if (CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).validateTransactionByID(theOrderID)) {
            // Customer has placed this order; process return
            
            // TODO: Accept returns of less than the total (max) quantity
            // Set return variables
            int returnProductID = theCustomer.getTransactionByID(theOrderID).getProductID();
            int returnQuantity = theQuantity;
            // Check if return quantity is invalid
            if(theCustomer.getTransactionByID(theOrderID).getQuantity() < returnQuantity) {
                System.out.println("Invalid return. Returned amount cannot be larger than the initial order.");
                return false;
            }
            double returnTotal = returnQuantity * Inventory.getInventory().getItemByID(returnProductID).getPrice();
            String returnDescription = Inventory.getInventory().getItemByID(returnProductID).getDescription();
            
            // Return the quantity of the item returned to the store inventory
            Inventory.getInventory().getItemByID(returnProductID).setQuantity(Inventory.getInventory().getItemByID(returnProductID).getQuantity() + returnQuantity);
            
            // Generate return transaction and add to transaction history
            int newOrderID = Transaction.getNextOrderID();
            this.orderID = newOrderID;
            this.total = -1 * returnTotal;
            this.productID = returnProductID;
            this.quantity = returnQuantity;
            this.type = 1; // 1 = return
            
            // Add the transaction
            CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).addTransaction(this);
            System.out.println("Valid transaction. Successful return of " + returnQuantity + " " + returnDescription + "(s) from " + theCustomer.getFullName() + " for a total refund of $" + HelperMethods.priceToString((returnQuantity * Inventory.getInventory().getItemByID(returnProductID).getPrice())) + ".");
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
        System.out.println(this.orderID + " (return)\t\t" + this.productID + " (" + Inventory.getInventory().getItemByID(this.productID).getDescription() + ")\t\t" + this.quantity + "\t\t($" + HelperMethods.priceToString(-1 * this.total) + ")");
    }
    
}
