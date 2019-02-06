/**
 * An interface for array lists.
 * 
 * @author Roberto Tamassia, Michael Goodrich
 */

public interface IndexList<E> {

	/** 
	 * @return the number of elements in the indexed list. 
	 */
	public int size();

	/** 
	 * @return whether the indexed list is empty. 
	 */
	public boolean isEmpty();

	/** 
	 * @param i the index where new element goes
	 * @param e  element to be inserted at index i
	 * post: slot i now holds e.  Elements that used to be at index i and above
	 * have been moved one slot to the right to make room for the new element.
	 */
	public void add(int i, E e) throws IndexOutOfBoundsException;

	/** 
	 * @return the element stored at the given index. 
	 */
	public E get(int i) throws IndexOutOfBoundsException;

	/** 
	 * @param i  the index of the element to be removed
	 * @return the element at index i
	 * post: Removes the element stored at index i, filling the hole by
	 * moving elements to the right of that slot over 1 to the left. 
	 */
	public E remove(int i) throws IndexOutOfBoundsException;

	/** 
	 * @param i the index to be updated
	 * @param e the element to go in slot i
	 * @return the element originally in slot i
	 * post: The element stored at index i is now e. 
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException;
}
