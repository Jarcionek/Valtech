package application;

import application.commands.FollowCommandProcessor;
import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
import application.commands.WallCommandProcessor;

public class Application {

	private static final String USER_NAME_REGEX = "\\w+";
	private static final String POSTING_SEPARATOR = " -> ";
	private static final String FOLLOWING_SEPARATOR = " follows ";
	private static final String WALL_SEPARATOR = " wall";
	private static final String POST_COMMAND_REGEX = USER_NAME_REGEX + POSTING_SEPARATOR + ".*";
	private static final String READ_COMMAND_REGEX = USER_NAME_REGEX;
	private static final String FOLLOW_COMMAND_REGEX = USER_NAME_REGEX + FOLLOWING_SEPARATOR + USER_NAME_REGEX;
	private static final String WALL_COMMAND_REGEX = USER_NAME_REGEX + WALL_SEPARATOR;

	private final PostCommandProcessor postCommandProcessor;
	private final ReadCommandProcessor readCommandProcessor;
	private final FollowCommandProcessor followCommandProcessor;
	private final WallCommandProcessor wallCommandProcessor;
	private final WallToStringConverter wallToStringConverter;

	public Application(PostCommandProcessor postCommandProcessor,
					   ReadCommandProcessor readCommandProcessor,
					   FollowCommandProcessor followCommandProcessor,
					   WallCommandProcessor wallCommandProcessor,
					   WallToStringConverter wallToStringConverter) {
		this.postCommandProcessor = postCommandProcessor;
		this.readCommandProcessor = readCommandProcessor;
		this.followCommandProcessor = followCommandProcessor;
		this.wallCommandProcessor = wallCommandProcessor;
		this.wallToStringConverter = wallToStringConverter;
	}

	public String execute(String command) {
		if (command.matches(POST_COMMAND_REGEX)) {
			String userName = command.split(POSTING_SEPARATOR)[0];
			String message = command.split(POSTING_SEPARATOR)[1];

			postCommandProcessor.post(userName, message);
			return null;
		} else if (command.matches(READ_COMMAND_REGEX)) {
			return wallToStringConverter.convert(readCommandProcessor.read(command));
		} else if (command.matches(FOLLOW_COMMAND_REGEX)) {
			String userNameOne = command.split(FOLLOWING_SEPARATOR)[0];
			String userNameTwo = command.split(FOLLOWING_SEPARATOR)[1];

			followCommandProcessor.follow(userNameOne, userNameTwo);
			return null;
		} else if (command.matches(WALL_COMMAND_REGEX)) {
			return wallToStringConverter.convert(wallCommandProcessor.read(command.split(WALL_SEPARATOR)[0]));
		}

		return "Unknown command: " + command;
	}

}
