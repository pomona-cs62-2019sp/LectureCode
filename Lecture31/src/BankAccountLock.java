import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountLock {
	private Lock myLock;
	private int balance;
	
	public BankAccountLock() {
		balance = 0;
		myLock = new ReentrantLock();
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void deposit(int dollars) {
		myLock.lock();
		if(dollars < 0)
			throw new IllegalArgumentException("Cannot deposit negative money");
		int b = balance;
		balance = b + dollars;
		myLock.unlock();
	}
	
	public void withdraw(int dollars) {
		myLock.lock();
		int b = balance;
		if (b < dollars)
			throw new IllegalArgumentException("Cannot withdraw more than your balance");
		balance = b - dollars;
		myLock.unlock();
	}
	
	public static void main(String[] args) {
		for (int i = 0; true; i++) {
			BankAccountLock acct = new BankAccountLock();
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
