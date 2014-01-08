package application;

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
	private static final String WALL = "user's wall line 1\nuser's wall line 2";

	@Mock private Users users;
	@Mock private User user;

	private Application app;

	@Before
	public void setup() {
		app = new Application(users);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionForUnknownCommand() {
		app.execute("this is unknown command");
	}

	@Test
	public void userCanPostToTheirWall() {
		when(users.getUser(USER_NAME)).thenReturn(user);

		app.execute(USER_NAME + " -> " + A_MESSAGE);

		verify(user).post(A_MESSAGE);
	}
	
	@Test
	public void userCanReadTheirWall() {
		when(users.getUser(USER_NAME)).thenReturn(user);
		when(user.getWallAsString()).thenReturn(WALL);

		String wall = app.execute(USER_NAME);

		assertThat(wall, is(equalTo(WALL)));
	}

}
