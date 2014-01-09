package application.commands;

import application.Users;

public class FollowCommandProcessor {

	private final Users users;

	public FollowCommandProcessor(Users users) {
		this.users = users;
	}

	public void follow(String userNameOne, String userNameTwo) {
		users.getUser(userNameOne).follows(userNameTwo);
	}

}
