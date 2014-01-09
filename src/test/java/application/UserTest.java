package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	private static final String ANY_NAME = "userName";
	private static final String A_MESSAGE = "a message";

	@Mock private Wall wall;

	@Test
	public void savesPostedMessagesToWall() {
		new User(ANY_NAME, wall).post(A_MESSAGE);

		verify(wall).add(A_MESSAGE);
	}

}