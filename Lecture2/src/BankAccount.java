
public class BankAccount {
	//Fields
	private int amount;
	private String owner;
	private int accountID;
	
	/*
	 * Encapsulation is the OO principle that one should hide
	 * data fields and only expose methods to manipulate the data.
	 * This way the methods control the data and other code can't
	 * overwrite fields.
	 * 
	 * In this case we want to avoid another programmer decrementing
	 * amount below zero in the case of a large withdrawal.
	 */
	
	//Methods
	public BankAccount(String owner, int accountIDnumber) {
		this.owner = owner;
		this.accountID = accountIDnumber;
		this.amount = 0;
	}
	
	public void deposit(int depAmount) throws Exception {
		if (depAmount < 0) {
			throw new Exception("Negative deposit amount: " + depAmount);
		}
		amount += depAmount;
	}
	
	public boolean withdraw(int withAmount) {
		if (withAmount > amount) {
			return false;
		}
		amount -= withAmount;
		return true;
	}
	
	public int getAmount() {
		return amount;
	}
	public int getAccountID() {
		return accountID;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String newOwner) {
		owner = newOwner;
	}
}
