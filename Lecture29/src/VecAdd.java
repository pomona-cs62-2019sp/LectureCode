import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.*;
import java.util.concurrent.RecursiveAction;

/**
 * Class to use parallelism to compute the sum of two vectors. Extends
 * RecursiveAction, which itself extends ForkJoinTask<Void>
 * 
 * @author kim
 * @version 2/20/2011
 */
public class VecAdd extends RecursiveAction {
	private static final long NPS = (1000L * 1000 * 1000);
	private static final int VECTOR_SIZE = 200000;

	private static final ForkJoinPool fjPool = ForkJoinPool.commonPool();

	private static int SEQUENTIAL_CUTOFF = 100;
	int lo;
	int hi;
	int[] res;
	int[] arr1;
	int[] arr2;

	public VecAdd(int l, int h, int[] r, int[] a1, int[] a2) {
		lo = l;
		hi = h;
		res = r;
		arr1 = a1;
		arr2 = a2;
	}

	protected void compute() {
		if ((hi - lo) < SEQUENTIAL_CUTOFF) {
			for (int i = lo; i < hi; i++) {
				res[i] = arr1[i] + arr2[i];
			}
		} else {
			int mid = (hi + lo) / 2;
			VecAdd left = new VecAdd(lo, mid, res, arr1, arr2);
			VecAdd right = new VecAdd(mid, hi, res, arr1, arr2);
			left.fork();
			right.compute();
			left.join();
		}
	}


	public static int[] add(int[] arr1, int[] arr2) {
		assert (arr1.length == arr2.length);
		int[] ans = new int[arr1.length];
		fjPool.invoke(new VecAdd(0, arr1.length, ans, arr1, arr2));
		return ans;
	}
	
	public static int[] staticadd(int[] arr1, int[] arr2) {
		assert (arr1.length == arr2.length);
		int[] ans = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			ans[i]= arr1[i] + arr2[i];
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int[] first = new int[VECTOR_SIZE];
		int[] second = new int[VECTOR_SIZE];
		for(int count = 0; count < VECTOR_SIZE; count++) {
			first[count] = count;
			second[count] = 2*count;
		}
		for (int reps = 0; reps < 20; reps++){
			long last = System.nanoTime();
			int[] answer = add(first, second);
			double elapsed = (double) (System.nanoTime() - last) / NPS;
			System.out.println("Parallel add in time " + elapsed);
		}

		for (int reps = 0; reps < 20; reps++){
			SEQUENTIAL_CUTOFF = 2 * SEQUENTIAL_CUTOFF;
			long last = System.nanoTime();
			int[] answer = add(first,second);
			double elapsed = (double)(System.nanoTime() - last)/NPS;
			System.out.println("Parallel add in time "+elapsed+
					" with cutoff "+SEQUENTIAL_CUTOFF);
		}
		/*
		 * for(int val:answer) { System.out.print(val+", "); }
		 */
		for (int reps = 0; reps < 20; reps++) {
			long last = System.nanoTime();
			int[] answer = staticadd(first, second);
			double elapsed = (double) (System.nanoTime() - last) / NPS;
			System.out.println("Sequential add in time " + elapsed);
		}
	}
}