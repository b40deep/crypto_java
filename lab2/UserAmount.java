package lab2;

import java.util.TreeMap;
import java.util.Set;


/** 
 *   UserAmount defines a map from users to the amount they have
     users are given as strings.
 */

public class UserAmount {

    /** 
     * The current balance of each user, with each account's name mapped to its 
     *    current balance.
     */
    
    protected TreeMap<String, Integer> userAmountBase;

    /** 
     * Constructor for creating UserAmount with no users
     */
    public UserAmount() {
	userAmountBase = new TreeMap<String, Integer>();
    }

    /** 
     * Constructor for creating UserAmount from a map from string to integers
     */
    
    public UserAmount(TreeMap<String, Integer> userAmountBase) {
	this.userAmountBase = userAmountBase;
    }

    /**
     * Constructor for creating UserAmount from an EntryList
     * by adding for each entry the amount to the user.
     * EntryList could have more than one entry for the same user
     * in which case the amounts are added up
     *
     * This is used when when checking that a EntryList can be deducted
     *   from  an accountbalance given as an element of Ledger
     *    we first create using this constructor a UserAmount 
     *    which determines for each user the sum of amounts to be deducted
     *
     *   then we can check whether each entry in the original ledger is
     *     greater the sum of items for each user to be deducted
     */



    public UserAmount(EntryList entryList) {
	this.userAmountBase = new TreeMap<String, Integer>();
	for (Entry  entry : entryList.toList()){
	    this.addBalance(entry.getUser(),entry.getAmount());
	};
    }    


    /** obtain the underlying Treemap from string to integers
     */   
    
    public TreeMap<String,Integer> getUserAmountBase(){
	return userAmountBase;
    };

    /** 
      * obtain the list of users in the tree map
      */   
    
    public Set<String> getUsers(){
	return getUserAmountBase().keySet();
    };    

    
    

    /** 
     * Adds an account for user with balance.
     *
     * if there was an entry it is overridden.  
     */
    public void addAccount(String user, int balance) {
	userAmountBase.put(user, balance);
    }

    /** 
     * @return true if the {@code user} exists in UserAmount
     */
    
    public boolean hasUser(String user) {
	return userAmountBase.containsKey(user);
    }


    /** 
     * @return the balance for this account {@code account}
     *
     *  if there was no entry, return zero
     *
     */
    
    public int getBalance(String user) {
	if (hasUser(user)){
		return userAmountBase.get(user);
	    } else
	    {
		return 0;
	    }
    }


    /** 
     * set the balance for {@code user} to {@code amount}
     *  this will override any existing entries
     */

    
    public void setBalance(String user, int amount){
	userAmountBase.put(user,amount);
	    };
	

    /** 
     *  Adds amount to balance for {@code user}
     * 
     *  if there was no entry for {@code user} add one with 
     *       {@code balance}
     */
    
    public void addBalance(String user, int amount) {
	setBalance(user,getBalance(user) + amount);
    }


    /** 
     *   Subtracts amount from balance for {@code user}
     */
    
    public void subtractBalance(String user, int amount) {
	setBalance(user,getBalance(user) - amount);
    }


    /** 
     * Check balance has at least amount for {@code user}
     */
    public boolean checkBalance(String user, int amount) {
	return (getBalance(user) >= amount);
    }

    /** 
     * Prints the UserAmount
     */
    
    public void print() {
	for (String user : userAmountBase.keySet()) {
	    Integer value = getBalance(user);	    
	    System.out.println("The balance for " + user + " is " + value); 
	}

    }
    

    public static void test() {
	UserAmount uAmount = new UserAmount();
	uAmount.addAccount("Alice",0);
	uAmount.addAccount("Bob",0);
	uAmount.addAccount("Carol",0);
	uAmount.addAccount("David",0);
	System.out.println("After initialising Alice, Bob, Carol, David");
	uAmount.print();
	System.out.println("Set Balance Alice to 10");
	uAmount.setBalance("Alice",10);
	uAmount.print();
	System.out.println("Add to Balance Bob 7");
	uAmount.addBalance("Bob",7);
	uAmount.print();
	System.out.println("Subtract from Balance Bob 5");	
	uAmount.subtractBalance("Bob",5);
	uAmount.print();
	System.out.println("Creating Useramount from EntryList (Alice,10),(Alice,10),(Bob,5)");
	EntryList entryList = new EntryList("Alice",10,"Alice",10,"Bob",5);
	(new UserAmount(entryList)).print();
    }

    /** 
     * main function running test cases
     */            

    public static void main(String[] args) {
	UserAmount.test();	
    }
}
