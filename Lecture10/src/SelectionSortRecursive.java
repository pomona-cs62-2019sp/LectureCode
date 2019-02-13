/**
 * A recursive implementation of Selection sort
 * @author America Chambers 
 */

public class SelectionSortRecursive extends AbstractSelectionSort {
	public void sort(int[] array) {
		selectionSortRecursive(array, 0);		
	}
	
		
	/**
	 * 
	 * @param array array of integers
	 * @param startIndex a valid index into array
	 */
	private static void selectionSortRecursive(int[] array, int startIndex) {
		if(startIndex < array.length) {

			// find smallest element in array[startIndex...n]
			int smallest = indexOfSmallest(array, startIndex);

			// move largest element to position startIndex
			swap(array, smallest, startIndex);

			// recurse on everything to the left of startIndex
			selectionSortRecursive(array, startIndex+1);

		}
	}
}
