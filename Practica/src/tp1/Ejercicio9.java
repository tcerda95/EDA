package tp1;

public class Ejercicio9 {

	public static void combinations(int num) {
		combinationsRec(translate(num).toCharArray(), 0);
	}

	public static void combinationsRec(char[] arr, int index) {
		if (index == arr.length) {
			System.out.print(new String(arr) + " ");
			return;
		}

		for (int i = 0; i < 3; i++) {
			arr[index] = (char)(arr[index] + i);
			combinationsRec(arr, index+1);
			arr[index] = (char)(arr[index] - i);
		}
	}

	public static void main(String[] args) {
		combinations(256);
	}


	public static String translate(int num) {
		String str = "";
		int digit;
		char ch;
		while (num > 0) {
			digit = num % 10;
			num = num / 10;
			ch = (char) ((int)'A' + (digit-2)*3);
			str = ch + str;
		}
		return str;
	}
}
