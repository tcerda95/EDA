package tp3;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestLatex {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(new LatexNest(args[0]).isCorrectlyNested());
	}

}
