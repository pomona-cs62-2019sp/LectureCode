public class BankAccountAttempt {
	private int balance;
	private boolean blocked;
	
	public BankAccountAttempt() {
		balance = 0;
		blocked = false;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void deposit(int dollars) {
		while(blocked) {}
		blocked = true;
		
		if(dollars < 0)
			throw new IllegalArgumentException("Cannot deposit negative money");
		int b = balance;
		balance = b + dollars;
		
		blocked = false;
	}
	
	public void withdraw(int dollars) {
		while(blocked) {}
		blocked = true;
		
		int b = balance;
		if (b < dollars)
			throw new IllegalArgumentException("Cannot withdraw more than your balance");
		balance = b - dollars;

		blocked = false;
	}
	
	public static void main(String[] args) {
		for (int i = 0; true; i++) {
			if(i%100000 == 0)
				System.out.print(".");
			
			BankAccountAttempt acct = new BankAccountAttempt();
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
				System.out.println("Threads took too long");
			}
			
			if(acct.getBalance() != 100)
				System.out.println("Error on iter " + i + ": " + acct.getBalance());
		}
	}
}
