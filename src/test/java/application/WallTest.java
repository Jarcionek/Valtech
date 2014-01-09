package application;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WallTest {

	private static final String MESSAGE_ONE = "message one";
	private static final String MESSAGE_TWO = "message two";
	private static final String WALL_AS_STRING = MESSAGE_ONE + "\n" + MESSAGE_TWO;

	private Wall wall;

	@Before
	public void setup() {
		wall = new Wall();
	}

	@Test
	public void messageRoundTrip() {
		wall.add(MESSAGE_ONE);
		wall.add(MESSAGE_TWO);

		assertThat(wall.toString(), is(equalTo(WALL_AS_STRING)));
	}

}
