/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * The class for processing exchange transactions
 * @author J. Barclay Walsh
 */
public class Exchange extends Transaction {
    
    private int orderID;
    private double total;
    private int productID;
    private int quantity;
    private int type;
    
    public Exchange(int newProductID, int newQuantity, Customer newCustomer) {
        this.processTransaction(newProductID, newQuantity, newCustomer);
    }
    
    /**
     * Process the return
     * @param theOrderID the orderID of the order being exchanged
     * @param defective 0 = not defective; 1 = defective
     * @param theCustomer the customer who placed the initial order
     * @return true if successful; false if not
     */
    @Override
    public synchronized final boolean processTransaction(int theOrderID, int defective, Customer theCustomer) {
        if (CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).validateTransactionByID(theOrderID)) {
            // Customer has placed this order; process exchange
            
            // Set exchange variables
            int exchangeProductID = theCustomer.getTransactionByID(theOrderID).getProductID();
            int exchangeQuantity = theCustomer.getTransactionByID(theOrderID).getQuantity();
            double exchangeTotal = 0; // even exchanges are a net-zero transaction
            String exchangeDescription = Inventory.getInventory().getItemByID(exchangeProductID).getDescription();
            
            // Generate return transaction and add to transaction history
            int newOrderID = Transaction.getNextOrderID();
            this.orderID = newOrderID;
            this.total = exchangeTotal;
            this.productID = exchangeProductID;
            this.quantity = exchangeQuantity;
            this.type = 2; // 2 = exchange
            
            // Add the transaction
            CustomerList.getCustomers().getCustomerByID(theCustomer.getCustomerID()).addTransaction(this);
            
            // Check if product was marked defective
            if(defective == 0) {
                // Non-defective product(s); return exchange to store inventory
                System.out.println("Valid transaction. Successful exchange of " + exchangeQuantity + " " + exchangeDescription + "(s) from " + theCustomer.getFullName() + ".");
            } else {
                // Defective product(s); do not return exchange to store inventory
                Inventory.getInventory().getItemByID(exchangeProductID).setQuantity(Inventory.getInventory().getItemByID(exchangeProductID).getQuantity() - exchangeQuantity);
                System.out.println("Valid transaction. Successful exchange of " + exchangeQuantity + " defective " + exchangeDescription + "(s) from " + theCustomer.getFullName() + ".");
            }
            return true;
        } else {
            // Invalid transaction; inform customer
            System.out.println("Invalid exchange. Invalid order or incorrect customer.");
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
        System.out.println(this.orderID + " (exchange)\t\t" + this.productID + " (" + Inventory.getInventory().getItemByID(this.productID).getDescription() + ")\t\t" + this.quantity + "\t\t$" + HelperMethods.priceToString(this.total) + "");
    }
    
}
