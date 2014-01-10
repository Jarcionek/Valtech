package application.commands;

import application.Message;
import application.User;
import application.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static matchers.IterableMatchers.sameIterableAs;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallCommandProcessorTest {

	private static final String USER_NAME_1 = "UserOne";
	private static final String USER_NAME_2 = "UserTwo";
	private static final String USER_NAME_3 = "UserThree";

	private final User user_1 = new User(USER_NAME_1);
	private final User user_2 = new User(USER_NAME_2);
	private final User user_3 = new User(USER_NAME_3);

	private WallCommandProcessor wallCommandProcessor;

	@Mock private Users users;

	@Before
	public void setup() {
		wallCommandProcessor = new WallCommandProcessor(users);

		user_1.follows(USER_NAME_2);
		user_1.follows(USER_NAME_3);

		user_1.post(msg(1));
		user_2.post(msg(2));
		user_3.post(msg(3));
		user_2.post(msg(4));
		user_1.post(msg(5));
		user_3.post(msg(6));
	}

	@Test
	public void returnsAggregatedWallOfUserAndFollowingUsers() {
		when(users.getUser(USER_NAME_1)).thenReturn(user_1);
		when(users.getUser(USER_NAME_2)).thenReturn(user_2);
		when(users.getUser(USER_NAME_3)).thenReturn(user_3);

		assertThat(wallCommandProcessor.read(USER_NAME_1), is(sameIterableAs(msg(USER_NAME_3, 6),
																			 msg(USER_NAME_1, 5),
																			 msg(USER_NAME_2, 4),
																			 msg(USER_NAME_3, 3),
																			 msg(USER_NAME_2, 2),
																			 msg(USER_NAME_1, 1))));
	}


	private static Message msg(int n) {
		return new Message("" + n, n);
	}

	private static Message msg(String author, int n) {
		return new Message(author + " - " + n, n);
	}

}
