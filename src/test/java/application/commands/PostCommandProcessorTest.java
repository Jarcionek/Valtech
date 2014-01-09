package application.commands;

import application.User;
import application.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandProcessorTest {

	private static final String USER_NAME = "UserName";
	private static final String MESSAGE = "message";

	private PostCommandProcessor postCommandProcessor;

	@Mock private Users users;
	@Mock private User user;

	@Before
	public void setup() {
		postCommandProcessor = new PostCommandProcessor(users);
	}

	@Test
	public void addsMessageToUserWall() {
		when(users.getUser(USER_NAME)).thenReturn(user);

		postCommandProcessor.post(USER_NAME, MESSAGE);

		verify(user).post(MESSAGE);
	}

}
