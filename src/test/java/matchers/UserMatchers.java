package matchers;

import application.User;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class UserMatchers {

	public static Matcher<User> hasName(String name) {
		return hasProperty("name", equalTo(name));
	}

}
