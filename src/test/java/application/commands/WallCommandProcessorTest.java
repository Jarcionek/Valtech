package application.commands;

import application.Message;
import application.User;
import application.Users;
import application.Wall;
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

	private static final String USER_NAME = "UserName";
	private static final String FOLLOWING_USER_NAME_ONE = "FUserNameOne";
	private static final String FOLLOWING_USER_NAME_TWO = "FUserNameTwo";

	private static final Wall USER_WALL = wallWithMessages(msg(1), msg(5));
	private static final Wall FOLLOWING_USER_ONE_WALL = wallWithMessages(msg(2), msg(4));
	private static final Wall FOLLOWING_USER_TWO_WALL = wallWithMessages(msg(3), msg(6));

	private WallCommandProcessor wallCommandProcessor;

	@Mock private Users users;
	@Mock private User user;
	@Mock private User followingUserOne;
	@Mock private User followingUserTwo;

	@Before
	public void setup() {
		wallCommandProcessor = new WallCommandProcessor(users);
	}

	@Test
	public void returnsAggregatedWallOfUserAndFollowingUsers() {
		when(users.getUser(USER_NAME)).thenReturn(user);
		when(user.getFollowing()).thenReturn(newArrayList(FOLLOWING_USER_NAME_ONE, FOLLOWING_USER_NAME_TWO));

		when(users.getUser(FOLLOWING_USER_NAME_ONE)).thenReturn(followingUserOne);
		when(users.getUser(FOLLOWING_USER_NAME_TWO)).thenReturn(followingUserTwo);

		when(user.getWall()).thenReturn(USER_WALL);
		when(followingUserOne.getWall()).thenReturn(FOLLOWING_USER_ONE_WALL);
		when(followingUserTwo.getWall()).thenReturn(FOLLOWING_USER_TWO_WALL);

		assertThat(wallCommandProcessor.read(USER_NAME), is(sameIterableAs(msg(6), msg(5), msg(4), msg(3), msg(2), msg(1))));
	}


	private static Message msg(int n) {
		return new Message("" + n, n);
	}

	private static Wall wallWithMessages(Message... messages) {
		Wall wall = new Wall();
		for (Message message : messages) {
			wall.add(message);
		}
		return wall;
	}

}
