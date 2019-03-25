import java.util.Iterator;

import structure5.Vector;

public class ArrayListOddIterator extends Vector<Integer> {
	public Iterator<Integer> oddIterator() {
		return new OddIterator();
	}
	
	public class OddIterator implements Iterator<Integer> {
		Vector<Integer> iterList;
		int index;
		
		public OddIterator() {
			iterList = new Vector<Integer>();
			for(int i = 0; i < elementCount; i++) {
				if(get(i) % 2 == 1) { //get(i) -> elts[i]
					iterList.add(get(i));
				}
			}
			index = 0;
		}
		
		public boolean hasNext() {
			return index < iterList.size();
		}
		
		public Integer next() {
			index++;
			return iterList.get(index-1);
		}
	}
	
	public static void main(String[] args) {
		ArrayListOddIterator list = new ArrayListOddIterator();
		list.add(0);
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		Iterator<Integer> iter = list.oddIterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
