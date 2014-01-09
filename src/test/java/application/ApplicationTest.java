package application;

import application.commands.FollowCommandProcessor;
import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
import application.commands.WallCommandProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

	private static final String USER_NAME_ONE = "UserNameOne";
	private static final String USER_NAME_TWO = "UserNameTwo";
	private static final String A_MESSAGE = "a message";
	private static final Wall WALL = new Wall();
	private static final String WALL_AS_STRING = "it doesn't matter what this text it is";

	@Mock private PostCommandProcessor postCommandProcessor;
	@Mock private ReadCommandProcessor readCommandProcessor;
	@Mock private FollowCommandProcessor followCommandProcessor;
	@Mock private WallCommandProcessor wallCommandProcessor;
	@Mock private WallToStringConverter wallToStringConverter;

	private Application app;

	@Before
	public void setup() {
		app = new Application(postCommandProcessor, readCommandProcessor, followCommandProcessor, wallCommandProcessor, wallToStringConverter);
	}

	@Test
	public void userCanPostToTheirWall() {
		app.execute(USER_NAME_ONE + " -> " + A_MESSAGE);

		verify(postCommandProcessor).post(USER_NAME_ONE, A_MESSAGE);
	}
	
	@Test
	public void userCanReadTheirWall() {
		when(readCommandProcessor.read(USER_NAME_ONE)).thenReturn(WALL);
		when(wallToStringConverter.convert(WALL)).thenReturn(WALL_AS_STRING);

		assertThat(app.execute(USER_NAME_ONE), is(equalTo(WALL_AS_STRING)));
	}

	@Test
	public void userCanFollowOtherUsers() {
		app.execute(USER_NAME_ONE + " follows " + USER_NAME_TWO);

		verify(followCommandProcessor).follow(USER_NAME_ONE, USER_NAME_TWO);
	}

	@Test
	public void userCanSeeAggregatedWallOfAllTheyFollows() {
	    when(wallCommandProcessor.read(USER_NAME_ONE)).thenReturn(WALL);
		when(wallToStringConverter.convert(WALL)).thenReturn(WALL_AS_STRING);

		assertThat(app.execute(USER_NAME_ONE + " wall"), is(equalTo(WALL_AS_STRING)));
	}

}
