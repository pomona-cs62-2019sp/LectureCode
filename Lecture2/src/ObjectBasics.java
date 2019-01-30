import java.util.ArrayList;
import java.util.LinkedList;

public class ObjectBasics {
	public static void main(String[] args) throws Exception {
		Object o = new Object();  //<- base class for every other class
		String s = new String("Hello world");
		LinkedList l = new LinkedList();
		
		// Strings are a little bit special
		String s2 = "Hi";
		
		//BankAccount code
		BankAccount ba1 = new BankAccount("Devanny",1);
		System.out.println(ba1.getOwner());
		System.out.println(ba1.getAmount());
		// ba1.amount = ba1.amount + 1000;
		int am = ba1.getAmount();
		am += 1000;
		System.out.println(ba1.getAmount());
		try {
			ba1.deposit(1000);
		}
		catch (Exception foo) {
			System.out.println(foo.getMessage());
			System.out.println("The exception stopped the deposit");
		} 
		if(!ba1.withdraw(1500)) {
			System.out.println("Invalid funds");
		}
		System.out.println(ba1.getAmount());
		if(!ba1.withdraw(1500)) {
			System.out.println("Invalid funds");
		}
		System.out.println(ba1.getAmount());
		
		// Annoying to have to print out ba1.getAmount() every time
		// Instead implement toString()
		
		System.out.println("Starting Savings Account section");
		SavingsAccount sa = new SavingsAccount("Papoutsaki", 2, 1.04);
		sa.deposit(1000);

		
		// First piece of code is executed when the for loop starts
		// Second piece of code is the loop condition - must be a boolean
		// Third piece is executed after each loop iteration
		for (int i = 0; i < 10; i+=2) {
			sa.accumulateInterest();
		}
		
		System.out.println(sa);
		
		CheckingAccount ca = new CheckingAccount("Pomona" , 3);
		
		ca.deposit(100000);
		ca.writeCheck(sa, 1000);
		System.out.println(ca);
		System.out.println(sa);
		
		// <...> are called generic type arguments
		ArrayList<BankAccount> tas = new ArrayList<BankAccount>();
		for(int i = 0; i < 5; i++) {
			tas.add(new BankAccount("ta" + i, 4 + i));
		}
		System.out.println(tas.get(3));
		System.out.println(tas.size());
		
	}
}
