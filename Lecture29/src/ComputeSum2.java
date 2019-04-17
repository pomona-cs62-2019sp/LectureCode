import java.util.Random;

public class ComputeSum2 {

	public static int sumParallel(int[] arr) {
		SumThread2 t = new SumThread2(arr, 0, arr.length);
		t.run();          // do parallel computations
		return t.ans;
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
		final int SIZE = 100000000;
		int[] testArray = new int[SIZE];
		Random r = new Random(47);
		for (int count = 0; count < SIZE; count++) {
			testArray[count] = r.nextInt(10);
		}
		int answer = sum(testArray);
		answer = sumParallel(testArray);
		long timeStart = System.currentTimeMillis();
		answer = sumParallel(testArray);
		long timeEnd = System.currentTimeMillis();
		System.out.println(answer + " takes parallel time " + (timeEnd-timeStart));
	}
}
