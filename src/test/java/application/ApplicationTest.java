package application;

import org.junit.Test;

public class ApplicationTest {

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionForUnknownCommand() {
		execute("this is unknown command");
	}

	@Test
	public void acceptsPostingCommand() {
	    execute("UserName -> some message");
	}
	
	@Test
	public void acceptsReadingCommand() {
	    execute("SomeOtherUserName");
	}


	private static void execute(String command) {
		new Application().execute(command);
	}

}
