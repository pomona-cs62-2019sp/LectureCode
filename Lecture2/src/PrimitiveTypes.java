
public class PrimitiveTypes {
	public static void main(String[] args) {
		boolean bool = true;
		int x;
		x = 5;
		x = 7;
		
		//Integer types
		byte by = 127;	// 1 byte
		byte by2 = 5;
		short sh = -6;	// 2 bytes
		int i = 2147483647;		// 4 bytes
		int i2 = 5;
		int i3 = i + i2;
		long l = -8;		// 8 bytes

		System.out.println(i3);
		System.out.println(Byte.MAX_VALUE);
		System.out.println(Short.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		
		//Decimal types
		float f = 3.5f;		// 4 bytes
		double d = -3.89d;	// 8 bytes
		
		char c = 'a';
		
		//Wrapper types
		Integer iObj = new Integer(i);
		Double dObj = new Double(d);
		
		//Arrays
		int[] iArr = new int[i2];
		iArr[0] = 1;
		iArr[1] = 2;
		iArr[2] = 4;
		iArr[3] = 8;
		iArr[4] = 16;
		iArr = new int[10];
		System.out.println("After resize: " + iArr[0]);
		
		int[] piArr = {3,1,4,1,5,9};
		System.out.println("The fourth digit of pi is: " + piArr[3]);
		
		// Multi-dimensional arrays
		int[][] twoDArr = new int[4][6];
		System.out.println(twoDArr[2][3]);
		
		// Jagged arrays
		int[][] jaggedArr = new int[3][];
		jaggedArr[0] = new int[5];
		jaggedArr[1] = new int[3];
		jaggedArr[2] = new int[6];
		System.out.println(jaggedArr[2][5]);
	}
}
