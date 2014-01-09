package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	private static final String ANY_NAME = "userName";
	private static final String ANOTHER_NAME = "AnotherUserName";
	private static final Message A_MESSAGE = new Message("a message", 12345L);

	@Mock private Wall wall;
	@Mock private List<String> following;

	@Test
	public void savesPostedMessagesToWall() {
		new User(ANY_NAME, wall, following).post(A_MESSAGE);

		verify(wall).add(A_MESSAGE);
	}

	@Test
	public void savesFollowingUsers() {
	    new User(ANY_NAME, wall, following).follows(ANOTHER_NAME);

		verify(following).add(ANOTHER_NAME);
	}

}
