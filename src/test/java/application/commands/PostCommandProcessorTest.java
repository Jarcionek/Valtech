package application.commands;

import application.Clock;
import application.Message;
import application.User;
import application.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static matchers.MessageMatchers.sameMessageAs;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandProcessorTest {

	private static final String USER_NAME = "UserName";
	private static final String MESSAGE_CONTENT = "message";
	private static final long CURRENT_TIME = 123456L;
	private static final Message MESSAGE = new Message(MESSAGE_CONTENT, CURRENT_TIME);

	private PostCommandProcessor postCommandProcessor;

	@Mock private Users users;
	@Mock private User user;
	@Mock private Clock clock;

	@Before
	public void setup() {
		postCommandProcessor = new PostCommandProcessor(users, clock);
	}

	@Test
	public void addsMessageToUserWall() {
		when(users.getUser(USER_NAME)).thenReturn(user);
		when(clock.now()).thenReturn(CURRENT_TIME);

		postCommandProcessor.post(USER_NAME, MESSAGE_CONTENT);

		verify(user).post(argThat(is(sameMessageAs(MESSAGE))));
	}

}
