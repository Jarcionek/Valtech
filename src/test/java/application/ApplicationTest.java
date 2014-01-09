package application;

import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
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

	private static final String USER_NAME = "UserName";
	private static final String A_MESSAGE = "a message";
	private static final String WALL_AS_STRING = "it doesn't matter what text it is";

	@Mock private PostCommandProcessor postCommandProcessor;
	@Mock private ReadCommandProcessor readCommandProcessor;
	@Mock private Wall wall;

	private Application app;

	@Before
	public void setup() {
		app = new Application(postCommandProcessor, readCommandProcessor);
	}

	@Test
	public void userCanPostToTheirWall() {
		app.execute(USER_NAME + " -> " + A_MESSAGE);

		verify(postCommandProcessor).post(USER_NAME, A_MESSAGE);
	}
	
	@Test
	public void userCanReadTheirWall() {
		when(readCommandProcessor.read(USER_NAME)).thenReturn(wall);
		when(wall.toString()).thenReturn(WALL_AS_STRING);

		assertThat(app.execute(USER_NAME), is(equalTo(WALL_AS_STRING)));
	}

}
