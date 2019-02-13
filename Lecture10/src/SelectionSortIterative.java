/**
 * An iterative implementation of Selection sort.
 * @author America Chambers 
 */
public class SelectionSortIterative extends AbstractSelectionSort {
	public void sort(int[] array) {
		selectionSortIterative(array);		
	}
	
	/**
	 * Sorts integer array using iterative selection sort 
	 * @param array array of integers to be sorted
	 */
	private static void selectionSortIterative(int[] array) {		
		for(int i = 0; i < array.length; ++i) {
			int min = indexOfSmallest(array, i); 			
			swap(array, i, min);			
		}
	}
}
