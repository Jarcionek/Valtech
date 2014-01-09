package application;

import org.junit.Test;

import static matchers.IterableMatchers.sameIterableAs;
import static org.junit.Assert.assertThat;

public class WallTest {

	private static final Message MESSAGE_ONE = new Message("message one", 1L);
	private static final Message MESSAGE_TWO = new Message("message one", 2L);

	private Wall wall = new Wall();

	@Test
	public void messageRoundTrip() {
		wall.add(MESSAGE_ONE);
		wall.add(MESSAGE_TWO);

		assertThat(wall, sameIterableAs(MESSAGE_TWO, MESSAGE_ONE));
	}

}
