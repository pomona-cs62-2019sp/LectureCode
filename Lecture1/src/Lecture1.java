import java.util.LinkedList;
import java.util.List;

public class Lecture1 {
	public static void main(String [] args) {
                // Try changing the LinkedList to an ArrayList
		List<Integer> l = new LinkedList<Integer>();
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 10000000; i++) {
			l.add(i);
		}
		
		for(int i = 0; i < 10000000; i++) {
			l.remove(0);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time: " + (end - start));
	}
}
