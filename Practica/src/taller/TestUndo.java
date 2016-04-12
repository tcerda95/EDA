package taller;

public class TestUndo {

	public static void main(String[] args) {
		UndoList<String> list = new UndoList<>((o1, o2)->o1.compareTo(o2));
		list.insert("C");
		list.insert("A");
		list.insert("D");
		list.insert("B");
		list.print();
		list.undo();
		list.print();
		list.undo();
		list.undo();
		list.print();
		list.undo();
		list.print();
	}

}
