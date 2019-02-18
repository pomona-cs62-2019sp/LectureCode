import java.util.ArrayList;
//import java.util.function.Consumer;
import java.util.Iterator;

/**
 * Realization of an indexed list by means of an array, which is doubled when
 * the size of the indexed list exceeds the capacity of the array.
 * Based on the authors' datastructures library, it approximates the behavior
 * of the java.util.ArrayList class
 * @author Roberto Tamassia, Michael Goodrich
 */
public class ArrayIndexList<E> implements IndexList<E> {
	private E[ ] elts; // array storing the elements of the indexed list
	private int capacity = 16; // initial length of array elts
	private int eltsFilled = 0; // number of elements stored in the indexed list

	/** Creates the indexed list with initial capacity 16. */
	@SuppressWarnings("unchecked")
	public ArrayIndexList() {
		elts = (E[]) new Object[capacity]; // the compiler may warn, but this is ok
	}

	/** 
	 * @return the number of elements in the indexed list. 
	 */
	public int size() {
		return eltsFilled;
	}

	/** 
	 * @return whether the indexed list is empty. 
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/** 
	 * @return the element stored at the given index. 
	 */
	public E get(int r) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		return elts[r];
	}

	/** 
	 * @param r the index to be updated
	 * @param newElt the element to go in slot r
	 * @return the element originally in slot r
	 * post: The element stored at index r is now e. 
	 */
	public E set(int r, E newElt) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		E temp = elts[r];
		elts[r] = newElt;
		return temp;
	}

	/** 
	 * @param r the index where new element goes
	 * @param elt  element to be inserted at index r
	 * post: slot r now holds elt.  Elements that used to be at index r and above
	 * have been slid one slot to the right to make room for the new element.
	 */
	public void add(int r, E elt) throws IndexOutOfBoundsException {
		checkIndex(r, size() + 1);
		ensureCapacity();
		for (int i = eltsFilled - 1; i >= r; i--) {
			// shift elements up
			elts[i + 1] = elts[i];
		}
		elts[r] = elt;
		eltsFilled++;
	}
	
	/**
	 * post: If elts is full (no room for new elements), then copy elts
	 * to a new array with twice the capacity.
	 * There is now at least one empty slot in the array representation 
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		if (eltsFilled == capacity) { // an overflow
			capacity *= 2;
			E[] newElts = (E[]) new Object[capacity];
			for (int i = 0; i < eltsFilled; i++)
				newElts[i] = elts[i];
			elts = newElts;
		}
	}

	
	/** 
	 * @param elt  element to be inserted at end of list
	 * post: elt has been added to end of list
	 */
	public void add(E elt) throws IndexOutOfBoundsException {
		add(size(),elt);
	}

	/** 
	 * @param r  the index of the element to be removed
	 * @return the element at index r
	 * post: Removes the element stored at index r, filling the hole by
	 * moving elements to the right of that slot over 1 to the left. 
	 */
	public E remove(int r) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		E temp = elts[r];
		for (int i = r; i < eltsFilled - 1; i++) {
			// shift elements down
			elts[i] = elts[i + 1];
		}
		eltsFilled--;
		return temp;
	}

	/** 
	 * @param r index to be checked
	 * @param n  one greater than largest index allowed
	 * @throws IndexOutOfBoundsException if r is not between 0 and n-1, inclusive.
	 */
	private void checkIndex(int r, int n) 
			throws IndexOutOfBoundsException { 
		if (r < 0 || r >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + r);
		}
	}
	
	public Iterator<E> iterator() {
		return new ArrayIndexListIterator();
	}
	
	private class ArrayIndexListIterator implements Iterator<E>{
		private int currentIndex = -1;

		public boolean hasNext() {
			return currentIndex < eltsFilled-1;
		}

		public E next() {
			currentIndex++;
			return elts[currentIndex];
		}

		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("remove method not implemented");		
		}
		
	}
	
	
	public static void main(String[] args) {
		IndexList<String> myList = new ArrayIndexList<String>();
		myList.add("hello");
		myList.add("there");
		Iterator<String> listIterator = myList.iterator();
		while(listIterator.hasNext()){
			System.out.println(listIterator.next());
		}

		for(String elt: myList) {
			System.out.println(elt);
		}
		
		ArrayList<String> myAL = new ArrayList<String>();
		myAL.add("This");
		myAL.add("is");
		myAL.add("a new list");
		listIterator = myAL.iterator();
		System.out.println(listIterator.next());
		// uncommenting next line will raise an exception!
		// myAL.add("in this program");
		// System.out.println(listIterator.next());
		
		// iterate using forEach
		System.out.println("Using new-fangled internal iterator");
		myAL.forEach(elt -> {System.out.println(elt);});
		

	}
}
