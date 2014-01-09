package application;

import java.util.List;

/**
 * Data of a single user.
 */
public class User {

	private final String name;
	private final Wall wall;
	private final List<String> following;

	public User(String name, Wall wall, List<String> following) {
		this.name = name;
		this.wall = wall;
		this.following = following;
	}

	public String getName() {
		return name;
	}

	public void post(Message message) {
		wall.add(message);
	}

	public Wall getWall() {
		return wall;
	}

	public void follows(String userName) {
		following.add(userName);
	}

	public Iterable<String> getFollowing() {
		return following;
	}

}
