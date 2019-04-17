import java.util.Random;

public class ComputeSum1 {
	private static final int WARM_UP_ROUNDS = 40;
	private static final int NUM_REPS = 400;
	private static final int SIZE = 100000000;
	private static final int NUM_THREADS = 4;

	public static int sumParallel(int[] arr) {
		int len = arr.length;
		int ans = 0;
		SumThread1[] ts = new SumThread1[NUM_THREADS];
		for (int i = 0; i < NUM_THREADS; i++) {// do parallel computations
			ts[i] = new SumThread1(arr, i * len / NUM_THREADS, (i + 1) * len
					/ NUM_THREADS);
			ts[i].start(); // start not run
		}

		try {
			for (int i = 0; i < NUM_THREADS; i++) { // combine results
				ts[i].join(); // wait for helper to finish!
				ans += ts[i].getAns();
			}
			return ans;
		} catch (InterruptedException exc) {
			System.out.println("Execution interrupted");
			return -1;
		}
	}

	public static int sum(int[] arr) {
		int len = arr.length;
		int ans = 0;
		for (int i = 0; i < len; i++) {
			ans = ans + arr[i];
		}
		return ans;
	}

	public static final void main(String[] args) {
		int[] testArray = new int[SIZE];
		Random r = new Random(47);
		for (int count = 0; count < SIZE; count++) {
			testArray[count] = r.nextInt(10);
		}
		long answer;
		for (int i = 1; i < WARM_UP_ROUNDS; i++) {
			answer = sum(testArray);
			answer = sumParallel(testArray);
		}
		long seqTime = 0;
		long parTime = 0;
		for (int i = 1; i < NUM_REPS; i++) {
			System.gc();
			long timeStart = System.currentTimeMillis();
			answer = sumParallel(testArray);
			long timeEnd = System.currentTimeMillis();
			parTime = parTime + (timeEnd-timeStart);
			System.out.println(answer + " takes parallel time " + (timeEnd-timeStart));
			System.gc();
			timeStart = System.currentTimeMillis();
			answer = sum(testArray);
			timeEnd = System.currentTimeMillis();
			seqTime = seqTime + (timeEnd-timeStart);
			System.out.println(answer + " takes sequential time " + (timeEnd-timeStart));
		}
		System.out.println("Ave parallel time: "+parTime/NUM_REPS);
		System.out.println("Ave sequential time: "+seqTime/NUM_REPS);

	}
}
