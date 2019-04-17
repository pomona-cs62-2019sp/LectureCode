import java.util.concurrent.RecursiveTask;

public class SumArray4 extends RecursiveTask<Integer> {
	private static final int SEQUENTIAL_CUTOFF = 5000;
	private int lo; // Starting subscript for sum
	private int hi; // Stop adding when get to hi
	private int[] arr; // array of values to add

	public SumArray4(int[] a, int l, int h) {
		arr = a;
		lo = l;
		hi = h;
	}
	

	/**
	 * Computer sum arr[lo] + arr[lo + 1] + ... arr[hi-1]
	 */
	public Integer compute() {
		if (hi - lo < SEQUENTIAL_CUTOFF) {
			int ans = 0;
			for (int count = lo; count < hi; count++) {
				ans = ans + arr[count];
			}
			return ans;
		} else {
				SumArray4 left = new SumArray4(arr, lo, (hi + lo) / 2);
				SumArray4 right = new SumArray4(arr, (hi + lo) / 2, hi);
				left.fork();
				int rightAns = right.compute(); // don't start new thread!
				int leftAns = left.join(); // don't move this up a line - why?
				return leftAns + rightAns;
		}
	}
}
