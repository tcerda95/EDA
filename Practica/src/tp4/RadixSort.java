package tp4;

import java.lang.reflect.Array;

import tp3.Queue;

public class RadixSort {
	private static final int BASE = 10;
	private static final int DIGITS = 4; // número de dígitos

	/**
	 * Ordena números del 0 al 9999. Se asume que el tamaño del arreglo es array.length
	 * @param array arreglo a ordenar
	 */
	public static long sort(int[] array) {
		long startTime = System.currentTimeMillis();
		@SuppressWarnings("unchecked")
		Queue<Integer>[] queues = (Queue<Integer>[]) Array.newInstance(Queue.class, BASE);
		for(int i = 0; i < BASE; i++)
			queues[i] = new Queue<Integer>();

		for(int digit = 1; digit <= DIGITS; digit++) {
			for(int i = 0; i < array.length; i++)
				queues[ getDigit(array[i], digit) ].enqueue(array[i]);
			int j = 0;
			for (Queue<Integer> q : queues)
				while(!q.isEmpty())
					array[j++] = q.dequeue();
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	//Si digit = 1, devuelve el digito menos significativo
	private static int getDigit(int num, int digit) {
		int divs = (int) Math.pow(BASE, digit-1);
		num = num / divs;
		return num % BASE;
	}
}
