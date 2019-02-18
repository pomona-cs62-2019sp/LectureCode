
public class Foo {
	public static void main(String[] args) {
		final IndexList<Integer> myList = new ArrayIndexList<Integer>();
		myList.add(0, 47);
		IndexList<String> myList2 = new ArrayIndexList<String>();
		myList2.add(0, "Data structures!");
		IndexList<Double> myList3 = new ArrayIndexList<Double>();
		myList3.add(0, 3.14);
		
		
		
		foo(10000);
		foo(100000);
		foo(1000000);
		bar(10000);
		bar(100000);
		bar(1000000);
	}
	
	public static void foo(int n) {
		long start = System.currentTimeMillis();
		IndexList<Integer> myList = new ArrayIndexList<Integer>();
		
		for (int i = 0; i < n; i++) {
			// Append i to the end of the list
			myList.add(i, i);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("foo took " + (end - start) 
				+ "ms on input " + n);
	}
	
	public static void bar(int n) {
		long start = System.currentTimeMillis();
		IndexList<Integer> myList = new ArrayIndexList<Integer>();
		
		for (int i = 0; i < n; i++) {
			// Prepend i at the beginning of the list
			myList.add(0, i);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("bar took " + (end - start) 
				+ "ms on input " + n);
	}
}
