package application;

import java.util.List;

public class User {

	private final String name;
	private final List<String> wall;

	public User(String name, List<String> wall) {
		this.name = name;
		this.wall = wall;
	}

	public String getName() {
		return name;
	}

	public void post(String message) {
		wall.add(message);
	}

	public String getWallAsString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String message : wall) {
			stringBuilder.append("\n");
			stringBuilder.append(message);
		}
		return stringBuilder.delete(0, 1).toString();
	}
}
