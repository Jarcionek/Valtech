package application;

public class Application {

	private static final String USER_NAME = "\\w+";
	private static final String POSTING_SEPARATOR = " -> ";
	private static final String POSTING = USER_NAME + POSTING_SEPARATOR + ".*";

	private final Users users; //TODO this should be injected, then each command should be unit tested

	public Application() {
		users = new Users();
	}

	public String execute(String command) {
		if (command.matches(POSTING)) {
			String userName = command.split(POSTING_SEPARATOR)[0];
			String message = command.split(POSTING_SEPARATOR)[1];

			User user = users.getUser(userName);
			user.post(message);

			return null;
		} else if (command.matches(USER_NAME)) {
			return users.getUser(command).getWallAsString();
		}

		throw new IllegalArgumentException("Unknown command: " + command);
	}

}
