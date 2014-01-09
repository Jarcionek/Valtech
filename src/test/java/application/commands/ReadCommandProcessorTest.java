package application.commands;

import application.User;
import application.Users;
import application.Wall;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadCommandProcessorTest {

	private static final String USER_NAME = "UserName";
	private static final Wall WALL = new Wall();

	private ReadCommandProcessor readCommandProcessor;

	@Mock private Users users;
	@Mock private User user;

	@Before
	public void setup() {
		readCommandProcessor = new ReadCommandProcessor(users);
	}

	@Test
	public void returnsWallOfRequestedUser() {
		when(users.getUser(USER_NAME)).thenReturn(user);
		when(user.getWall()).thenReturn(WALL);

		assertThat(readCommandProcessor.read(USER_NAME), is(equalTo(WALL)));
	}

}
