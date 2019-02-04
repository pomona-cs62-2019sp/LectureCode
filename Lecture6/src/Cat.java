
public class Cat extends Animal {
	public int legs = 4;
	public static String species = "Cat";
	public static void testClassMethod() {
		System.out.println("The static method in Cat");
	}
	public void testInstanceMethod() {
		System.out.println("The instance method in Cat");
	}
}
