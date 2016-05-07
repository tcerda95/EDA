package tp6;

import java.util.Random;

public class TestClosedHash {

	public static void main(String[] args) {
		SimpleMap<Integer,String> map = new ClosedHash<>(5000);
		Random rand = new Random();

		map.put(0, "Z");
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(5, "JJ");
		map.put(44, "D");
		map.put(55, "E");
		map.put(66, "F");
		map.put(77, "G");
		map.put(88, "H");
		map.put(3, "CC");
		map.put(11, "AA");
		map.put(12, "AA");
		map.put(13, "AA");
		map.put(14, "ads");
		map.put(15, "l");
		map.put(235, "PPP");
		map.put(19, "Ñ");
		map.put(20, "mno");
		map.put(22, "GT");
		map.put(23, "AA");
		map.put(27, "AA");
		map.put(46, "AA");
		map.put(-2, "AA");
		map.put(99, "AA");

		map.remove(99);
		map.remove(20);
		map.remove(2);

		for(int i = 0; i < 8000; i++) {
			int r = rand.nextInt(40000);
			map.put(r, "A" + i);
		}

		System.out.println(map.get(1));

		System.out.println(map.values());
		System.out.println(map.size());
	}
}
