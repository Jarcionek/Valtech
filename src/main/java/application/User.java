package application;

import java.util.HashSet;
import java.util.Set;

/**
 * Data of a single user.
 */
public class User {

	private final String name;
	private final Wall wall;
	private final Set<String> following;

	public User(String name) {
		this.name = name;
		this.wall = new Wall();
		this.following = new HashSet<String>();
		this.following.add(name);
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
