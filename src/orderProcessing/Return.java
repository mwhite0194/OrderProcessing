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
        if (theCustomer.validateTransactionByID(theOrderID)) {
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
    
}
