package application;

import java.util.LinkedList;
import java.util.List;

public class Wall {

	private List<String> list = new LinkedList<String>();

	public void add(String message) {
		list.add(message);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String message : list) {
			stringBuilder.append("\n");
			stringBuilder.append(message);
		}
		return stringBuilder.delete(0, 1).toString();
	}

}
