package application.commands;

import application.Message;
import application.User;
import application.Users;
import application.Wall;

import java.util.*;

public class WallCommandProcessor {

	private static final Comparator<Message> COMPARATOR = new Comparator<Message>() {
		@Override
		public int compare(Message o1, Message o2) {
			long difference = o1.getTimestamp() - o2.getTimestamp();
			if (difference < 0) {
				return -1;
			}
			if (difference > 0) {
				return 1;
			}
			return 0;
		}
	};

	private final Users users;

	public WallCommandProcessor(Users users) {
		this.users = users;
	}

	public Wall read(String userName) {
		User user = users.getUser(userName);
		List<User> followingUsers = new LinkedList<User>();
		for (String followingUser : user.getFollowing()) {
			followingUsers.add(users.getUser(followingUser));
		}

		List<Message> messages = new ArrayList<Message>();
		addMessages(user, messages);
		for (User followingUser : followingUsers) {
			addMessages(followingUser, messages);
		}
		Collections.sort(messages, COMPARATOR);

		Wall aggregatedWall = new Wall();
		for (Message message : messages) {
			aggregatedWall.add(message);
		}

		return aggregatedWall;
	}

	private static void addMessages(User user, Collection<Message> messages) {
		String prefix = user.getName() + " - ";
		for (Message message : user.getWall()) {
			messages.add(new Message(prefix + message.getMessage(), message.getTimestamp()));
		}
	}

}
