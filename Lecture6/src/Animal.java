
public class Animal {
	public int legs = 2;
	public static String species = "Animal";
	public static void testClassMethod() {
		System.out.println("The static method in Animal");
	}
	public void testInstanceMethod() {
		System.out.println("The instance method in Animal");
	}
	
	public static void main(String[] args) {
		Cat myCat = new Cat();
		myCat.testClassMethod(); //invoking a hidden method
		myCat.testInstanceMethod(); //invoking an overridden method
	    System.out.println(myCat.legs); //accessing a hidden field
	    System.out.println(myCat.species); //accessing a hidden field
	}
}
