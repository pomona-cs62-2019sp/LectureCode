public class SumThread2 extends Thread {
	private static final int SEQUENTIAL_CUTOFF = 50000;
	private int lo; // Starting subscript for sum
	private int hi; // Stop adding when get to hi
	private int[] arr; // array of values to add
	int ans = 0; // for communicating result

	public SumThread2(int[] a, int l, int h) {
		arr = a;
		lo = l;
		hi = h;
	}

	/**
	 * Computer sum arr[lo] + arr[lo + 1] + ... arr[hi-1]
	 */
	public void run() {
		if (hi - lo < SEQUENTIAL_CUTOFF) {
			for (int count = lo; count < hi; count++) {
				ans = ans + arr[count];
			}
		} else {
			try {
				SumThread2 left = new SumThread2(arr, lo, (hi + lo) / 2);
				SumThread2 right = new SumThread2(arr, (hi + lo) / 2, hi);
				left.start();
				right.start();
				left.join(); // don't move this up a line - why?
				right.join();
				ans = left.getAns() + right.getAns();
			} catch (InterruptedException exp) {
				// join might throw InterruptedException!
				ans = -1;
			}
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
