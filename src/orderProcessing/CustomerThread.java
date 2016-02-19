/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * The class creates example customer threads
 * @author J. Barclay Walsh and Matt White
 */
public class CustomerThread extends Thread {
    
    int transactionType;
    int id;
    int quantity;
    int customerID;
    
    /**
     * Creates a customer thread
     * @param newTransactionType - 0 = sale; 1 = 0 = sale; 1 = return; 2 = exchange; 3 = inventory adjustment
     * @param newID - For sales and inventory adjustments, the productID; for returns and exchanges, the orderID
     * @param newQuantity - The quantity to buy/return/exchange/adjust (adjust can be negative, all other must be positive)
     * @param newCustomerID - The ID of the customer that is making the transaction
     */
    public CustomerThread(int newTransactionType, int newID, int newQuantity, int newCustomerID) {
        this.transactionType = newTransactionType;
        this.id = newID;
        this.quantity = newQuantity;
        this.customerID = newCustomerID;
    }
    
    /**
     * Run the Thread
     */
    @Override
    public void run() {
        // Random wait time
        /*int waitTime = HelperMethods.randomInteger(1, 300); // 1-300ms
        try {
            Thread.sleep(waitTime);
        }
        catch (Exception e) {
            // Print error
            System.out.println(e.getMessage());
        }*/
        
        try {
            switch (this.transactionType) {
                case 0:  Sale newSale = new Sale(this.id, this.quantity, CustomerList.getCustomers().getCustomerByID(this.customerID));
                         break;
                case 1:  Return newReturn = new Return(this.id, this.quantity, CustomerList.getCustomers().getCustomerByID(this.customerID));
                         break;
                case 2:  Exchange newExchange = new Exchange(this.id, this.quantity, CustomerList.getCustomers().getCustomerByID(this.customerID));
                         break;
                case 3:  InventoryAdjustment newInventoryAdjustment = new InventoryAdjustment(this.id, this.quantity, CustomerList.getCustomers().getCustomerByID(this.customerID));
                         break;
                default: System.out.println("Invalid transaction type.");
                         break;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
}
