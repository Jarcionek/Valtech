package application.commands;

import application.Users;
import application.Wall;

public class ReadCommandProcessor {

	private final Users users;

	public ReadCommandProcessor(Users users) {
		this.users = users;
	}

	public Wall read(String userName) {
		return users.getUser(userName).getWall();
	}

}
