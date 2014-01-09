package matchers;

import application.Message;
import org.hamcrest.Matcher;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MessageMatchers {

	public static Matcher<Message> sameMessageAs(Message message) {
		return sameBeanAs(message);
	}
}
