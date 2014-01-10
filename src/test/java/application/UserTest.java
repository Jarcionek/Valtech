package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	private static final String NAME_ONE = "UserNameOne";
	private static final String NAME_TWO = "UserNameTwo";
	private static final Message A_MESSAGE = new Message("a message", 12345L);

	private User user = new User(NAME_ONE);

	@Test
	public void savesPostedMessagesToWall() {
		user.post(A_MESSAGE);

		assertThat(user.getWall(), contains(A_MESSAGE));
	}

	@Test
	public void savesFollowingUsers() {
		user.follows(NAME_TWO);

		assertThat(user.getFollowing(), either(contains(NAME_ONE, NAME_TWO)).or(contains(NAME_TWO, NAME_ONE)));
	}

	@Test
	public void userCanFollowEachOtherUserOnlyOnce() {
		user.follows(NAME_TWO);
		user.follows(NAME_TWO);

		assertThat(user.getFollowing(), either(contains(NAME_ONE, NAME_TWO)).or(contains(NAME_TWO, NAME_ONE)));
	}

}
