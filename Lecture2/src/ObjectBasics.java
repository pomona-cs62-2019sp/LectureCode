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
		
		SavingsAccount sa = new SavingsAccount("Papoutsaki", 2, 1.04);
		sa.deposit(1000);
	}
}
