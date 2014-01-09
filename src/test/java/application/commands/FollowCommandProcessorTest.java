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
public class FollowCommandProcessorTest {

	private static final String USER_NAME_ONE = "UserNameOne";
	private static final String USER_NAME_TWO = "UserNameTwo";

	private FollowCommandProcessor followCommandProcessor;

	@Mock private Users users;
	@Mock private User userOne;

	@Before
	public void setup() {
		followCommandProcessor = new FollowCommandProcessor(users);
	}

	@Test
	public void addsUserToTheListOfFollowingsByAnotherUser() {
		when(users.getUser(USER_NAME_ONE)).thenReturn(userOne);

		followCommandProcessor.follow(USER_NAME_ONE, USER_NAME_TWO);

		verify(userOne).follows(USER_NAME_TWO);
	}

}
