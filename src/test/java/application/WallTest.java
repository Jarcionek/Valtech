package application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class WallTest {

	private static final Message MESSAGE_ONE = new Message("message one", 1L);
	private static final Message MESSAGE_TWO = new Message("message one", 2L);

	private Wall wall = new Wall();

	@Test
	public void messageRoundTrip() {
		wall.add(MESSAGE_ONE);
		wall.add(MESSAGE_TWO);

		assertThat(wall, hasItems(MESSAGE_ONE, MESSAGE_TWO)); //TODO it doesn't check the size
	}

}
