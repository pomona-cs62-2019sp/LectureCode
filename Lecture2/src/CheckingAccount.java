
public class CheckingAccount extends BankAccount {
	public boolean writeCheck(BankAccount receiver, int amount) {
		if(this.withdraw(amount)) {
			receiver.deposit(amount);
			return true;
		}
		return false;
	}
}
