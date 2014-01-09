package application;

import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;

public class Application {

	private static final String USER_NAME_REGEX = "\\w+";
	private static final String POSTING_SEPARATOR = " -> ";
	private static final String POST_COMMAND_REGEX = USER_NAME_REGEX + POSTING_SEPARATOR + ".*";

	private final PostCommandProcessor postCommandProcessor;
	private final ReadCommandProcessor readCommandProcessor;

	public Application(PostCommandProcessor postCommandProcessor, ReadCommandProcessor readCommandProcessor) {
		this.postCommandProcessor = postCommandProcessor;
		this.readCommandProcessor = readCommandProcessor;
	}

	public String execute(String command) {
		if (command.matches(POST_COMMAND_REGEX)) {
			String userName = command.split(POSTING_SEPARATOR)[0];
			String message = command.split(POSTING_SEPARATOR)[1];

			postCommandProcessor.post(userName, message);
			return null;
		} else if (command.matches(USER_NAME_REGEX)) {
			return readCommandProcessor.read(command).toString();
		}

		throw new IllegalArgumentException("Unknown command: " + command); //TODO return string instead
	}

}
