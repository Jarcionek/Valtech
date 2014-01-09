package application.commands;

import application.Users;

public class PostCommandProcessor {

	private final Users users;

	public PostCommandProcessor(Users users) {
		this.users = users;
	}

	public void post(String userName, String message) {
		users.getUser(userName).post(message);
	}

}
