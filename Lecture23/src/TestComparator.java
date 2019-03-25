import java.util.ArrayList;
import java.util.*;

public class TestComparator {
	
	public static class TrimComparator
		implements Comparator<String> {
		// pre: o1 and o2 are string
		// post: returns negative, zero, or positive 
		// depending on relation 
		// between trimmed parameters.
		public int compare(String s1, String s2) {
		       String s1trim = s1.trim();
		       String s2trim = s2.trim();
		       return s1trim.compareTo(s2trim);
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> data = new ArrayList<String>();
		data.add(" hello");
		data.add("there");
		data.add("this");
		data.add("is");
		data.add("a");
		data.add("test");
		// Java 5 way to sort with comparator;
		Collections.sort(data,new TrimComparator());
		// print elements in order
		System.out.println("Print result of Java 5 way");
		for(String elt:data) {
			System.out.println(elt);
		}

		// reset arraylist to sort again
		data.clear();
		data.add(" hello");
		data.add("there");
		data.add("this");
		data.add("is");
		data.add("a");
		data.add("test");
		
		// Java 8 way to sort with lambda expression
		Collections.sort(data,
			(s1,s2) -> {
				String s1trim = s1.trim();
				String s2trim = s2.trim();
				return s1trim.compareTo(s2trim);
			});
		
		System.out.println("Print result of Java 8 way");
		data.forEach(elt -> System.out.println(elt));
	}	
}
