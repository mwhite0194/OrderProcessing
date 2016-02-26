/*
 * Order Processing App – IST 411
 */

package orderProcessing;

/**
 * The class creates example customer threads
 * @author J. Barclay Walsh and Matt White
 */
public class CustomerThread extends Thread {
    
    /*int transactionType;
    int id;
    int quantity;
    int customerID;*/
    int numberOfThreads;
    
    /**
     * Creates a customer thread
     * @param newTransactionType - 0 = sale; 1 = 0 = sale; 1 = return; 2 = exchange; 3 = inventory adjustment
     * @param newID - For sales and inventory adjustments, the productID; for returns and exchanges, the orderID
     * @param newQuantity - The quantity to buy/return/exchange/adjust (adjust can be negative, all other must be positive)
     * @param newCustomerID - The ID of the customer that is making the transaction
     */
    /*public CustomerThread(int newTransactionType, int newID, int newQuantity, int newCustomerID) {
        this.transactionType = newTransactionType;
        this.id = newID;
        this.quantity = newQuantity;
        this.customerID = newCustomerID;
    }*/
    
    public CustomerThread(int theNumberOfThreads) {
        this.numberOfThreads = theNumberOfThreads;
    }
    
    /**
     * Run the Thread
     */
    @Override
    public void run() {
        // Random wait time
        /*int waitTime = HelperMethods.randomInteger(1, 30); // 1-30ms
        try {
            Thread.sleep(waitTime);
        }
        catch (Exception e) {
            // Print error
            System.out.println(e.getMessage());
        }*/
        int skippedThreads = 0;
        int successfulThreads = 0;
        synchronized(this) {
            int melatoninToAdd = this.numberOfThreads;
            Transaction[] transactionThreads = new Transaction[melatoninToAdd]; ;
            InventoryAdjustment moreMelatonin = new InventoryAdjustment(1007, melatoninToAdd, CustomerList.getCustomers().getCustomerByID(0)); // Add the specified number of melatonin units to the inventory
            
            // Create Threads
            for (int i = 0; i < melatoninToAdd; i++) {
                int customerID = (int)i/(melatoninToAdd/100);//HelperMethods.randomInteger(1, 100);
                int itemID = 1007;
                int quantity = 1;
                int transactionType = 0;//HelperMethods.randomIntegerWithSeed(0, 3, 262017552961037);
                
                try {
                    switch (transactionType) {
                        case 0:  transactionThreads[i] = new Sale(itemID, quantity, CustomerList.getCustomers().getCustomerByID(customerID));
                                 break;
                        case 1:  transactionThreads[i] = new Return(itemID, quantity, CustomerList.getCustomers().getCustomerByID(customerID));
                                 break;
                        case 2:  transactionThreads[i] = new Exchange(itemID, quantity, CustomerList.getCustomers().getCustomerByID(customerID));
                                 break;
                        case 3:  transactionThreads[i] = new InventoryAdjustment(itemID, quantity, CustomerList.getCustomers().getCustomerByID(customerID));
                                 break;
                        default: System.out.println("Invalid transaction type.");
                                 break;
                    }
                } catch(Exception e) {
                    System.out.println(e);
                }          
            }
            
            // Run Threads
            for (int i = 0; i < transactionThreads.length; i++) {
                //transactionThreads[i].start();
                try {
                    transactionThreads[i].start();
                    System.out.println("New thread started.");
                    successfulThreads++;
                } catch (OutOfMemoryError e) { //java.lang.OutOfMemoryError
                    // Print error if there are too many active threads – it appears 2024 is the maximum threads configured
                    skippedThreads++;
                    System.out.println("Can't create new thread - too many threads! – Loop #" + i + " – Active Threads: " + java.lang.Thread.activeCount());
                }
            }
            System.out.println("Thread count: " + Thread.activeCount());

            boolean threadsAreAlive;
            int loops = 0;
            do {
                loops++;
                threadsAreAlive = false;
                for (Transaction currentTransactionThread : transactionThreads) {
                    threadsAreAlive = currentTransactionThread.isAlive() || threadsAreAlive;
                    //currentTransactionThread.notify();
                }
            } while(threadsAreAlive);

            System.out.println("Threads alive loops: " + loops);
        }
        
        System.out.println("Skipped threads: " + skippedThreads);
        System.out.println("Successful threads: " + successfulThreads);
        System.out.println("There should be " + this.numberOfThreads + " total threads.");
        System.out.println("There were " + Sale.getValidTransactions() + " valid \"Sale\" transactions.");
        System.out.println("There should be " + (430 + skippedThreads) + " melatonin units remaining.");
        System.out.println("There are " + Inventory.getInventory().getItemByID(1007).getQuantity() + " melatonin units remaining.");
    }
    
}
