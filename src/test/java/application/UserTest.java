package application;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	private static final String ANY_NAME = "userName";
	private static final String A_MESSAGE = "this is a message that will be posted";
	private static final String POSTED_MESSAGE_ONE = "posted message 1";
	private static final String POSTED_MESSAGE_TWO = "posted message 2";
	private static final List<String> TWO_POSTED_MESSAGES = Lists.newArrayList(POSTED_MESSAGE_ONE, POSTED_MESSAGE_TWO);

	@Mock private List<String> wall;

	@Test
	public void savesPostedMessagesToWall() {
		new User(ANY_NAME, wall).post(A_MESSAGE);

		verify(wall).add(A_MESSAGE);
	}

	@Test
	public void returnsWallAsNewLineCharacterSeparatedConcatenationOfMessages() {
	    User user = new User(ANY_NAME, wall);

		when(wall.iterator()).thenReturn(TWO_POSTED_MESSAGES.iterator());

		assertThat(user.getWallAsString(), is(equalTo(POSTED_MESSAGE_ONE + "\n" + POSTED_MESSAGE_TWO)));
	}

}
