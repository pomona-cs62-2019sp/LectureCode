
public class Foo {
	public static void main(String[] args) {
		final IndexList<Integer> myList = new ArrayIndexList<Integer>();
		myList.add(0, 47);
		IndexList<String> myList2 = new ArrayIndexList<String>();
		myList2.add(0, "Data structures!");
		IndexList<Double> myList3 = new ArrayIndexList<Double>();
		myList3.add(0, 3.14);
	}
}
