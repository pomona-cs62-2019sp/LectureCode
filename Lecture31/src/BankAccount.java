public class BankAccount {
	private int balance;
	
	public BankAccount() {
		balance = 0;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void deposit(int dollars) {
		if(dollars < 0)
			throw new IllegalArgumentException("Cannot deposit negative money");
//		balance+=dollars;
		int b = balance;
		balance = b + dollars;
	}
	
	public void withdraw(int dollars) {
		int b = balance;
		if (b < dollars)
			throw new IllegalArgumentException("Cannot withdraw more than your balance");
		balance = b - dollars;
	}
	
	public static void main(String[] args) {
		for (int i = 0; true; i++) {
			if(i%100000 == 0)
				System.out.print(".");
			
			BankAccount acct = new BankAccount();
			acct.deposit(100);
			
			Thread withdraw = new Thread(() -> {
				acct.withdraw(20);
			});
			Thread deposit = new Thread(() -> {
				acct.deposit(20);
			});
			
			withdraw.start();
			deposit.start();
			try {
				withdraw.join();
				deposit.join();
			} catch (InterruptedException e) {
				System.out.println("A thread was interrupted");
			}
			
			if(acct.getBalance() != 100)
				System.out.println("Error on iter " + i + ": " + acct.getBalance());
		}
	}
}
