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
	return true;
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
    }




    /** 
     * Add an EntryList (txel, usually transaction outputs) to the current ledger
     *
     */    

    public void addEntryList(EntryList txel){
	// fill in Body
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
    };



    /** 
     *  Task 6: Fill in the testcases as described in the labsheet
     *    
     * Testcase
     */
    
    public static void test() {
	// fill in Body	
    }
    
    /** 
     * main function running test cases
     */            

    public static void main(String[] args) {
	Ledger.test();	
    }
}
