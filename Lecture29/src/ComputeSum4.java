import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ComputeSum4 {
	private static final int WARM_UP_ROUNDS = 20;
	private static final int NUM_REPS = 40;
	private static final int SIZE = 10000000;

	private static final ForkJoinPool fjPool = ForkJoinPool.commonPool();

	public static int sumParallel(int[] arr) {
		return fjPool.invoke(new SumArray4(arr, 0, arr.length));
	}

	public static int sum(int[] arr) {
		int len = arr.length;
		int ans = 0;
		for (int i = 0; i < len; i++) {
			ans = (ans + arr[i]);
		}
		return ans;
	}
	
	public static int streamSum(ArrayList<Integer> arl) {
		return arl.stream().reduce(0,((m,n) -> m+n));
	}

	public static int parallelStreamSum(ArrayList<Integer> arl) {
		return arl.parallelStream().reduce(0,((m,n) -> m+n));
	}

	public static final void main(String[] args) {
		int[] testArray = new int[SIZE];
		ArrayList<Integer> testList = new ArrayList<Integer>();
		Random r = new Random(47);
		for (int count = 0; count < SIZE; count++) {
			testArray[count] = r.nextInt(10);
			testList.add(testArray[count]);
		}
		long answer;
		for (int i = 1; i < WARM_UP_ROUNDS; i++) {
			answer = sum(testArray);
			answer = sumParallel(testArray);
			answer = streamSum(testList);
			answer = parallelStreamSum(testList);
		}
		long seqTime = 0;
		long parTime = 0;
		long streamTime = 0;
		long pStreamTime = 0;
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
			System.gc();
			timeStart = System.currentTimeMillis();
			answer = streamSum(testList);
			timeEnd = System.currentTimeMillis();
			streamTime = streamTime + (timeEnd-timeStart);
			System.out.println(answer + " takes stream time " + (timeEnd-timeStart));
			System.gc();
			timeStart = System.currentTimeMillis();
			answer = parallelStreamSum(testList);
			timeEnd = System.currentTimeMillis();
			pStreamTime = pStreamTime + (timeEnd-timeStart);
			System.out.println(answer + " takes pstream time " + (timeEnd-timeStart));

		}
		System.out.println("With fork-join pool:");
		System.out.println("Ave parallel time: "+parTime/NUM_REPS);
		System.out.println("Ave sequential time: "+seqTime/NUM_REPS);
		System.out.println("Ave stream time: "+streamTime/NUM_REPS);
		System.out.println("Ave parallel stream time: "+pStreamTime/NUM_REPS);

	}
}
