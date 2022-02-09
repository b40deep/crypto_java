package lab2;


/**   Transaction (Transaction)
 *    consisting of a list of transction inputs and
 *    a list of transaction outputs
 */    

public class Transaction {

    /** The list of inputs  */
    private EntryList inputs;

    /** The list of outputs  */
    private EntryList outputs;    


    /**
     * Creates a new transaction 
     */ 
    public Transaction(EntryList inputs, EntryList outputs){
	this.inputs = inputs;
	this.outputs= outputs;
    }

    /**
     * return the list of inputs
     */ 
    
    public EntryList toInputs(){
	return inputs;
    }


    /**
     * return the list of outputs
     */ 
    
    public EntryList toOutputs(){
	return outputs;
    }    


    /**
     * check the sum of inputs is >= the sum of outputs
     */
    
    public boolean checkTransactionAmountsValid (){
	return (toInputs().toSum() >= toOutputs().toSum());
    }

    /**
     * print the transaction
     */ 
    

    public void print() {
	System.out.println("Inputs:");
        toInputs().print("User: "," spends ");
	System.out.println("Outputs:");
        toOutputs().print("User: "," receives ");	
    }


    /** 
     * Generic Test cases, providing a headline
     *    printing out the transaction
     *    and printing out whether it is valid
     */            

    
    public void testCase(String header){
	System.out.println(header);
	print();
	System.out.println("Is valid regarding sums = " + checkTransactionAmountsValid());
	System.out.println("");
    }
	

    /** 
     * Test cases
     */            

    public static void test(){
	Transaction tx;
	tx = new Transaction(new EntryList(),
			     new EntryList());	
	tx.testCase("Transaction null to null");
	tx = new Transaction(new EntryList("Alice",10),
			     new EntryList("Bob",5));
	tx.testCase("Transaction Alice 10  to Bob 5");


	tx = new Transaction(new EntryList("Alice",5),
			     new EntryList("Bob",10));
	tx.testCase("Transaction Alice 5  to Bob 10");
	
	tx = new Transaction(new EntryList("Alice",10,"Bob",5),
			     new EntryList("Alice",7,"Carol",8));
        tx.testCase("Transaction Alice 10  Bob 5 to Alice 7 Carol 8");

	tx = new Transaction(new EntryList("Alice",10,"Bob",5),
			     new EntryList("Alice",10,"Carol",8));
        tx.testCase("Transaction Alice 10  Bob 5 to Alice 10 Carol 8");

    }


    /** 
     * main function running test cases
     */            
    
    public static void main(String[] args) {
	Transaction.test();
    }    
    
}

