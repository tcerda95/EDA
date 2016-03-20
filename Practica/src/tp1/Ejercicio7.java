package tp1;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio7 {

	public static void permute(String str) {
		permuteRec(str.toCharArray(), 0);
	}

	public static void permuteRec(char[] arr, int index) {
		if (index == arr.length) {
			System.out.println(new String(arr));
			return;
		}

		List<Character> noSwap = new ArrayList<>();

		for(int i = index; i < arr.length; i++) {
			if (!noSwap.contains(arr[i])) {
				noSwap.add(arr[i]);
				swap(arr, i, index);
				permuteRec(arr, index+1);
				swap(arr, i, index);
			}
		}
	}

	public static void swap(char[] arr, int i, int j) {
		char aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	public static void main(String[] args) {
		String str = "ABABCC";
		permute(str);
	}

}
