public class SumThread1 extends Thread {
	private int lo; // Starting subscript for sum
	private int hi; // Stop adding when get to hi
	private int[] arr; // array of values to add
	int ans = 0; // for communicating result

	public SumThread1(int[] a, int l, int h) {
		arr = a;
		lo = l;
		hi = h;
	}

	/**
	 * Computer sum arr[lo] + arr[lo + 1] + ... arr[hi-1]
	 */
	public void run() {
		ans = 0;
		for (int count = lo; count < hi; count++) {
			ans = ans + arr[count];
		}
	}

	/**
	 * 
	 * @return arr[lo] + arr[lo + 1] + ... arr[hi-1]
	 */
	public int getAns() {
		return ans;
	}
}
