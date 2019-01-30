
public class CheckingAccount extends BankAccount {
	public CheckingAccount(String owner, int accountID) {
		super(owner, accountID);
	}

	public boolean writeCheck(BankAccount receiver, int amount) throws Exception {
		if (super.withdraw(amount)) {
			receiver.deposit(amount);
			return true;
		}
		return false;
	}
}
