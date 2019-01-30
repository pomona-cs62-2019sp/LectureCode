/*
 * Inheritance is a powerful OO tool by which one class
 * inherits the fields and methods of another class.
 */
public class SavingsAccount extends BankAccount{
	/*
	 * Here SavingsAccount is a subclass of BankAccount.
	 * And vice versa BankAccount is a superclass of SavingsAccount.
	 */
	
	private double interestRate;
	
	public SavingsAccount(String owner, int accountID, double interestRate) {
		super(owner,accountID);
	}
	
	
	
	public void accumulateInterest() {
		
	}
}
