package lab2;



/** 
 *   Ledger defines for each user the balance at a given time
     in the ledger model of bitcoins
     and contains methods for checking and updating the ledger
     including processing a transaction
 */

public class Ledger extends UserAmount{


    /** 
     *
     *  Task 1: Fill in the method checkUserAmountDeductable
     *          You need to replace the dummy value true by the correct calculation
     *
     * Check all items in amountToCheckForDeduction can be deducted from the current one
     *
     *   amountToCheckForDeduction is usually obtained
     *   from a list of inputs of a transaction
     *
     * Checking that a TransactionOutputList  can be deducted will be later done
     *  by first converting that TransactionOutputList into a
     *  UserAmount and then using this method
     *
     * A naive check would just check whether each entry of a outputlist of a Transaction 
     *   can be deducted
     *
     * But there could be an output for the same user Alice of say 10 units twice
     *   where there are not enough funds to deduct it twice but enough
     *   funds to deduct it once
     * The naive check would succeed, but after converting the ouput list of a Transaction
     *  to UserAmount we obtain that for Alice 20 units have to be deducted
     *  so the deduction of the UserAmount created fails.
     *
     * One could try for checking that one should actually deduct each entry in squence
     *   but then one has to backtrack again.
     * Converting the TransactionOutputList into a UserAmount
     *   is a better approach since the outputlist of a Transaction
     *   is usually much smaller than the main Ledger.
     * 
     *
     */    

    public boolean checkUserAmountDeductable(UserAmount userAmountCheck){
	// you need to replace then next line by the correct statement
        // call UserAmount.checkBalance on every uAC item,
        // passing in the item name and item total amount (uA.getBalance)
        for (String user : userAmountCheck.getUsers()
             ) {
            if (getBalance(user)< userAmountCheck.getBalance(user)){
//                System.out.println(user+"'s balance is not enough.");
                return false;
            }
        }
	return true;
    };


    /** 
     *
     *  Task 2: Fill in the method checkEntryListDeductable 
     *          You need to replace the dummy value true by the correct calculation
     *
     *  It checks that an EntryList (which will be inputs of a transactions)
     *     can be deducted from Ledger
     *
     *   done by first converting the EntryList into a UserAmount
     *     and then checking that the resulting UserAmount can be deducted.
     *   
     */    


    public boolean checkEntryListDeductable(EntryList txel){
	// you need to replace then next line by the correct statement
        UserAmount txua = new UserAmount(txel);
        return checkUserAmountDeductable(txua);
    };

    /** 
     *  Task 3: Fill in the methods subtractEntryList and  addEntryList.
     *
     *   Subtract an EntryList (txel, usually transaction inputs) from the ledger 
     *
     *   requires that the list to be deducted is deductable.
     *   
     */    
    

    public void subtractEntryList(EntryList txel){
	//  fill in Body
        if (checkEntryListDeductable(txel)){
            // now we can deduct
            for (Entry entry : txel.toList()
                 ) {
                subtractBalance(entry.getUser(), entry.getAmount());
            }
        }
    }




    /** 
     * Add an EntryList (txel, usually transaction outputs) to the current ledger
     *
     */    

    public void addEntryList(EntryList txel){
	// fill in Body
        for (Entry entry : txel.toList()
        ) {
            addBalance(entry.getUser(), entry.getAmount());
        }
    }


    /** 
     *
     *  Task 4: Fill in the method checkTransactionValid
     *          You need to replace the dummy value true by the correct calculation
     *
     * Check a transaction is valid:
     *    the sum of outputs is less than or equal the sum of inputs
     *    and the inputs can be deducted from the ledger.
     *
     */    
    
    public boolean checkTransactionValid(Transaction tx){
	// you need to replace then next line by the correct statement
        if(!tx.checkTransactionAmountsValid() || !checkEntryListDeductable(tx.toInputs()) )
        {
            return false;
        }
        return true;
    };

    /** 
     *
     *  Task 5: Fill in the method processTransaction
     *
     * Process a transaction
     *    by first deducting all the inputs
     *    and then adding all the outputs.
     *
     */    
    

    public void processTransaction(Transaction tx){
	// fill in Body
        // deduct all the inputs
        subtractEntryList(tx.toInputs());
        // add all the outputs
        addEntryList(tx.toOutputs());
    };

    public void printLedger(){
        System.out.println(getUserAmountBase());
        System.out.println("");
    }

    /** 
     *  Task 6: Fill in the testcases as described in the labsheet
     *    
     * Testcase
     */
    
    public static void test() {
	// fill in Body
        // Create an empty Ledger and add to it users Alice, Bob, Carol, David,
        // initialised with the amount 0 for each user,
        System.out.println(">> creating Ledger ledgit");
        Ledger ledgit = new Ledger();
        EntryList entryList = new EntryList("Alice",0,"Bob",0,"Carol",0);
        entryList.addEntry("David",0);
        ledgit.addEntryList(entryList);
        ledgit.printLedger();

        // set the balance for Alice to 20,
        System.out.println(">> set the balance for Alice to 20");
        ledgit.setBalance("Alice",20);
        ledgit.printLedger();

        // set the balance for Bob to 15,
        System.out.println(">> set the balance for Bob to 15");
        ledgit.setBalance("Bob",15);
        ledgit.printLedger();

        // add to balance of Alice to 5,
        System.out.println(">> add to balance of Alice to 5");
        ledgit.addBalance("Alice",5);
        ledgit.printLedger();

        // subtract 5 from the balance of Bob,
        System.out.println(">> subtract 5 from the balance of Bob");
        ledgit.subtractBalance("Bob",5);
        ledgit.printLedger();

        // check whether the EntryList txel1 giving Alice 15 units, and Bob 10 units can be deducted,
        EntryList txel1 = new EntryList("Alice",15,"Bob",10);
        System.out.println(">> test txel1 (A:15,B:10) deducts: "+ledgit.checkEntryListDeductable(txel1));
        ledgit.printLedger();

        // check whether the EntryList txel2 giving Alice 15 units, giving Alice again 15 units,
        // and giving Bob 5 units, can be deducted,
        EntryList txel2 = new EntryList("Alice",15,"Alice",15,"Bob",5);
        System.out.println(">> test txel2 (A:15,A:15,B:5) deducts: "+ledgit.checkEntryListDeductable(txel2));
        ledgit.printLedger();

        // deduct txel1 from the ledger
        System.out.println(">> deduct txel1 (A:15,B:10) from the ledger");
        ledgit.subtractEntryList(txel1);
        ledgit.printLedger();

        // add txel2 to the ledger
        System.out.println(">> add txel2 (A:15,A:15,B:5) to the ledger");
        ledgit.addEntryList(txel2);
        ledgit.printLedger();

        // Create a transaction tx1 which takes as input for Alice 45 units and gives Bob 5 and Carol 20 units.
        System.out.println(">> transaction tx1 A:45 to B:5, C:20");
        Transaction tx1 = new Transaction(new EntryList("Alice",45),
                new EntryList("Bob",5,"Carol",20));
        ledgit.printLedger();

        // Check whether it is valid.
        System.out.println(">> test if tx1 is valid: "+ledgit.checkTransactionValid(tx1));
        ledgit.printLedger();

        // Create a transaction tx2 which takes as input for Alice 20 units and gives Bob 5 and Carol 20 units
        System.out.println(">> transaction tx2 A:20 to B:5, C:20");
        Transaction tx2 = new Transaction(new EntryList("Alice",20),
                new EntryList("Bob",5,"Carol",20));
        ledgit.printLedger();

        // Check whether it is valid
        System.out.println(">> test if tx2 is valid: "+ledgit.checkTransactionValid(tx2));
        ledgit.printLedger();

        // Create a transaction tx3 which takes as input for Alice 25 units and gives Bob 10 and Carol 15 units
        System.out.println(">> transaction tx3 A:25 to B:10, C:15");
        Transaction tx3 = new Transaction(new EntryList("Alice",25),
                new EntryList("Bob",10,"Carol",15));
        ledgit.printLedger();

        // Check whether it is valid
        System.out.println(">> test if tx3 is valid: "+ledgit.checkTransactionValid(tx3));
        ledgit.printLedger();

        // Update Ledger by processing tx3.
        System.out.println(">> processing tx3 A:25 to B:10, C:15");
        ledgit.processTransaction(tx3);
        ledgit.printLedger();

        // Create a transaction tx4 which takes as inputs for Alice twice 5 units, and as output to Bob 10 units.
        System.out.println(">> transaction tx4 A:5, A:5 to B:10");
        Transaction tx4 = new Transaction(new EntryList("Alice",5,"Alice",5),
                new EntryList("Bob",10));
        ledgit.printLedger();

        // Update Ledger by processing tx4
        System.out.println(">> processing tx4 A:5, A:5 to B:10");
        ledgit.processTransaction(tx4);
        ledgit.printLedger();
    }
    
    /** 
     * main function running test cases
     */            

    public static void main(String[] args) {
	Ledger.test();	
    }
}
