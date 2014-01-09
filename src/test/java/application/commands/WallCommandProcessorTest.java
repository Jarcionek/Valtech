package application.commands;

import application.Message;
import application.User;
import application.Users;
import application.Wall;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static matchers.IterableMatchers.sameIterableAs;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallCommandProcessorTest {

	private static final String USER_NAME_1 = "UserOne";
	private static final String USER_NAME_2 = "UserTwo";
	private static final String USER_NAME_3 = "UserThree";

	private static final User USER_1 = new User(USER_NAME_1, wallWithMessages(msg(1), msg(5)), newArrayList(USER_NAME_2, USER_NAME_3));
	private static final User USER_2 = new User(USER_NAME_2, wallWithMessages(msg(2), msg(4)), Lists.<String>newArrayList());
	private static final User USER_3 = new User(USER_NAME_3, wallWithMessages(msg(3), msg(6)), Lists.<String>newArrayList());

	private WallCommandProcessor wallCommandProcessor;

	@Mock private Users users;

	@Before
	public void setup() {
		wallCommandProcessor = new WallCommandProcessor(users);
	}

	@Test
	public void returnsAggregatedWallOfUserAndFollowingUsers() {
		when(users.getUser(USER_NAME_1)).thenReturn(USER_1);
		when(users.getUser(USER_NAME_2)).thenReturn(USER_2);
		when(users.getUser(USER_NAME_3)).thenReturn(USER_3);

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

	private static Wall wallWithMessages(Message... messages) {
		Wall wall = new Wall();
		for (Message message : messages) {
			wall.add(message);
		}
		return wall;
	}

}
