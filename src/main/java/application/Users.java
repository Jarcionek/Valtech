package application;

import java.util.LinkedList;
import java.util.List;

public class Users {

	private final List<User> users = new LinkedList<User>();

	public User getUser(String userName) {
		for (User user : users) {
			if (user.getName().equals(userName)) {
				return user;
			}
		}
		User user = new User(userName, new LinkedList<String>());
		users.add(user);
		return user;
	}
}
