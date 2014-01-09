package application;

import application.commands.FollowCommandProcessor;
import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
import application.commands.WallCommandProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static matchers.StringMatchers.matchesRegex;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationAcceptanceTest {

	private static final String TIME_REGEX = "\\(\\d+ [a-z]+ ago\\)";
	private static final String MESSAGE_ONE = "I love the weather today";
	private static final String MESSAGE_TWO = "Damn! We lost!";
	private static final String MESSAGE_THREE = "Good game though.";
	private static final String MESSAGE_FOUR = "I'm in New York today! Anyone wants to have a coffee?";

	private Users users = new Users();
	private final Clock clock = new Clock();
	private Application app = new Application(new PostCommandProcessor(users, clock),
											  new ReadCommandProcessor(users),
											  new FollowCommandProcessor(users),
											  new WallCommandProcessor(users),
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

	@Test
	public void usersCanFollowOtherUsersAndSeeAggregatedWalls() {
		app.execute("Alice -> " + MESSAGE_ONE);
		app.execute("Bob -> " + MESSAGE_TWO);
		app.execute("Bob -> " + MESSAGE_THREE);

	    app.execute("Charlie -> " + MESSAGE_FOUR);
	    app.execute("Charlie follows Alice");
		assertThat(app.execute("Charlie wall"), allOf(containsString(MESSAGE_ONE),
													  containsString(MESSAGE_FOUR)));

		app.execute("Charlie follows Bob");
		assertThat(app.execute("Charlie wall"), allOf(containsString(MESSAGE_ONE),
													  containsString(MESSAGE_TWO),
													  containsString(MESSAGE_THREE),
													  containsString(MESSAGE_FOUR)));
	}

}
