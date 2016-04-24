package parciales;

public class TestLuchoList {

	public static void main(String[] args) {
		LuchoList<Integer, String> list = new LuchoList<>();
		list.put(1, "A");
		list.put(2, "B");
		System.out.println(list.getMostAccessed());
		System.out.println(list.get(2));
		System.out.println(list.get(2));
		list.put(3, "C");
		System.out.println(list.getMostAccessed());
		System.out.println(list.get(3));
		list.put(3, "G");
		System.out.println(list.get(3));
		System.out.println(list.getMostAccessed());
	}

}
