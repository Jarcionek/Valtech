package application;

import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;

public class Application {

	private static final String USER_NAME_REGEX = "\\w+";
	private static final String POSTING_SEPARATOR = " -> ";
	private static final String POST_COMMAND_REGEX = USER_NAME_REGEX + POSTING_SEPARATOR + ".*";

	private final PostCommandProcessor postCommandProcessor;
	private final ReadCommandProcessor readCommandProcessor;
	private final WallToStringConverter wallToStringConverter;

	public Application(PostCommandProcessor postCommandProcessor, ReadCommandProcessor readCommandProcessor, WallToStringConverter wallToStringConverter) {
		this.postCommandProcessor = postCommandProcessor;
		this.readCommandProcessor = readCommandProcessor;
		this.wallToStringConverter = wallToStringConverter;
	}

	public String execute(String command) {
		if (command.matches(POST_COMMAND_REGEX)) {
			String userName = command.split(POSTING_SEPARATOR)[0];
			String message = command.split(POSTING_SEPARATOR)[1];

			postCommandProcessor.post(userName, message);
			return null;
		} else if (command.matches(USER_NAME_REGEX)) {
			return wallToStringConverter.convert(readCommandProcessor.read(command));
		}

		return "Unknown command: " + command;
	}

}
