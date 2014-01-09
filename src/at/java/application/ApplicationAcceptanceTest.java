package application;

import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static matchers.StringMatchers.matchesRegex;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationAcceptanceTest {

	private static final String TIME_REGEX = "\\(\\d+ [a-z]+ ago\\)";
	private static final String MESSAGE_ONE = "I love the weather today";
	private static final String MESSAGE_TWO = "Damn! We lost!";
	private static final String MESSAGE_THREE = "Good game though.";

	private Users users = new Users();
	private final Clock clock = new Clock();
	private Application app = new Application(new PostCommandProcessor(users, clock),
											  new ReadCommandProcessor(users),
											  new WallToStringConverter(clock));

	@Test
	public void usersCanPostToAndSeeTheirWall() {
		app.execute("Alice -> " + MESSAGE_ONE);
		app.execute("Bob -> " + MESSAGE_TWO);
		app.execute("Bob -> " + MESSAGE_THREE);

		assertThat(app.execute("Alice"), matchesRegex("\\Q" + MESSAGE_ONE + "\\E " + TIME_REGEX));
		assertThat(app.execute("Bob"), matchesRegex("\\Q" + MESSAGE_THREE + "\\E " + TIME_REGEX + "\n" +
													"\\Q" + MESSAGE_TWO + "\\E " + TIME_REGEX));
	}

}
