package application;

import org.junit.Test;

import static matchers.UserMatchers.hasName;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class UsersTest {

	private static final String USER_NAME = "UserName";

	private Users users = new Users();

	@Test
	public void createsNewUserIfRequestedUserDoesNotExist() {
		User user = users.getUser(USER_NAME);

		assertThat(user, hasName(USER_NAME));
	}

	@Test
	public void returnsExistingUserIfRequestedUserExists() {
	    User user1 = users.getUser(USER_NAME);
		User user2 = users.getUser(USER_NAME);

		assertThat(user1, is(sameInstance(user2)));
	}

}
