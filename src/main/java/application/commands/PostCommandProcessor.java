package application.commands;

import application.Clock;
import application.Message;
import application.Users;

public class PostCommandProcessor {

	private final Users users;
	private final Clock clock;

	public PostCommandProcessor(Users users, Clock clock) {
		this.users = users;
		this.clock = clock;
	}

	public void post(String userName, String message) {
		users.getUser(userName).post(new Message(message, clock.now()));
	}

}
