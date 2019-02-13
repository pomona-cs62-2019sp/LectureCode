public abstract class AbstractSelectionSort implements SortInterface {

	/**
	 * @param array array of integers
	 * @param startIndex valid index into array
	 * @return index of smallest value in array[startIndex...n]
	 */
	protected static int indexOfSmallest(int[] array, int startIndex) {		
		int smallest = startIndex;
		for(int i = startIndex+1; i < array.length; ++i) {
			if(array[i] < array[smallest]) {
				smallest = i;
			}
		}
		return smallest;
	}

	
	/**
	 * Swaps array[n1] and array[n2]
	 * 
	 * @param array array of integers
	 * @param n1 valid index into array
	 * @param n2 valid index into array
	 * @throws IllegalArgumentException if n1 or n2 are not valid indices
	 */
	protected static void swap(int[] array, int n1, int n2){
		if(n1 >= array.length || n2 >= array.length || n1 < 0 || n2 < 0) {
			throw new IllegalArgumentException("Invalid array indices");
		}

		int tmp = array[n1];
		array[n1] = array[n2];
		array[n2] = tmp;
	}

}
