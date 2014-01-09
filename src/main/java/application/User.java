package application;

public class User {

	private final String name;
	private final Wall wall;

	public User(String name, Wall wall) {
		this.name = name;
		this.wall = wall;
	}

	public String getName() {
		return name;
	}

	public void post(String message) {
		wall.add(message);
	}

	public Wall getWall() {
		return wall;
	}

}
