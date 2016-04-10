package tp3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// TODO: en un lejano futuro, tener clases de todo lo q puede ser beginKey con
// su correspondiente par endKey

public class LatexNest {
	private final static String beginKey = "begin";
	private final static String endKey = "end";
	private final static String opener = "{";
	private final static String closer = "}";
	private final static String beginKeyword = beginKey + opener;
	private final static String endKeyword = endKey + opener;
	private ListStack<String> stack;
	private BufferedReader br;

	public LatexNest(String file) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(file));
		stack = new ListStack<>();
	}

	public boolean isCorrectlyNested() throws IOException {
		String line;
		while ((line = br.readLine()) != null)
			if (!processLine(line))
				return false;
		if (!stack.isEmpty())
			return false;
		return true;
	}

	private boolean processLine(String line) {
		String name;
		boolean isValid = true;

		for (int i = 0; i < line.length() && isValid; i++) {
			if (line.charAt(i) == '\\') {
				if (line.startsWith(beginKeyword, i+1)) {
					i += beginKeyword.length() + 1; // siguiente a \begin{
					name = extractName(line, i);
					if (name != null) {
						stack.push(name);
						i += name.length();
					}
					else
						isValid = false;
				}
				else if (line.startsWith(endKeyword, i+1)) {
					i += endKeyword.length() + 1; // siguiente a \end{
					name = extractName(line, i);
					if (name != null && !stack.isEmpty()) {
						isValid = stack.pop().equals(name);
						i += name.length();
					}
					else
						isValid = false;
				}
			}
		}
		return isValid;
	}

	private String extractName(String line, int i) {
		int j = line.indexOf(closer, i);
		if (i < j) // si j es -1 (no encontró el closer) o i == j (string vacio entre closer y opener)
			return line.substring(i, j);
		return null;
	}

}
