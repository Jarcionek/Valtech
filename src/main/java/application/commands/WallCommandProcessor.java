package application.commands;

import application.Message;
import application.User;
import application.Users;
import application.Wall;
import com.google.common.collect.Iterables;

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

		List<Wall> walls = new LinkedList<Wall>();
		walls.add(user.getWall());
		for (User followingUser : followingUsers) {
			walls.add(followingUser.getWall());
		}

		List<Message> messages = new ArrayList<Message>();
		for (Wall wall : walls) {
			Iterables.addAll(messages, wall);
		}
		Collections.sort(messages, COMPARATOR);

		Wall aggregatedWall = new Wall();
		for (Message message : messages) {
			aggregatedWall.add(message);
		}

		return aggregatedWall;
	}

}
