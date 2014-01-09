package application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallToStringConverterTest {

	private WallToStringConverter converter;

	@Mock Clock clock;

	@Before
	public void setup() {
		converter = new WallToStringConverter(clock);
	}

	@Test
	public void correctlyConvertsMessagesPostedSecondsAgo() {
		Wall wall = wallWithMessages(
				new Message("msg1", seconds(1)),
				new Message("msg2", seconds(5))
		);

		timeIs(seconds(6));

		assertThat(converter.convert(wall), is(equalTo("msg1 (5 seconds ago)" + "\n" + "msg2 (1 second ago)")));
	}

	@Test
	public void correctlyConvertsMessagesPostedMinutesAgoRoundedDownToMinutes() {
		Wall wall = wallWithMessages(
				new Message("msg1", minutes(0)),
				new Message("msg2", seconds(59)),
				new Message("msg3", minutes(58))
		);

		timeIs(minutes(59) + seconds(59));

		assertThat(converter.convert(wall), is(equalTo("msg1 (59 minutes ago)" + "\n"
													 + "msg2 (59 minutes ago)" + "\n"
													 + "msg3 (1 minute ago)")));
	}

	@Test
	public void correctlyConvertsMessagesPostedHoursAgo() {
		Wall wall = wallWithMessages(
				new Message("msg1", hours(0)),
				new Message("msg2", hours(1))
		);

		timeIs(hours(2));

		assertThat(converter.convert(wall), is(equalTo("msg1 (2 hours ago)" + "\n"
													 + "msg2 (1 hour ago)")));
	}

	@Test
	public void correctlyConvertsMessagesPostedDaysAgo() {
		Wall wall = wallWithMessages(
				new Message("msg1", days(0)),
				new Message("msg2", days(1))
		);

		timeIs(days(2));

		assertThat(converter.convert(wall), is(equalTo("msg1 (2 days ago)" + "\n"
													 + "msg2 (1 day ago)")));
	}

	@Test
	public void correctlyConvertsMessagesPostedMonthsAgo() {
		Wall wall = wallWithMessages(
				new Message("msg1", months(0)),
				new Message("msg2", months(29))
		);

		timeIs(months(30));

		assertThat(converter.convert(wall), is(equalTo("msg1 (30 months ago)" + "\n"
													 + "msg2 (1 month ago)")));
	}

	@Test
	public void correctlyConvertsMessagesPostedLessThanSecondAgo() {
		Wall wall = wallWithMessages(
				new Message("msg1", milliseconds(20))
		);

		timeIs(milliseconds(830));

		assertThat(converter.convert(wall), is(equalTo("msg1 (0 seconds ago)")));
	}


	private void timeIs(long currentTime) {
		when(clock.now()).thenReturn(currentTime);
	}

	private static Wall wallWithMessages(Message... messages) {
		Wall wall = new Wall();
		for (Message message : messages) {
			wall.add(message);
		}
		return wall;
	}

	private static long milliseconds(long n) {
		return n;
	}

	private static long seconds(long n) {
		return n * 1000;
	}

	private static long minutes(long n) {
		return n * seconds(60);
	}

	private static long hours(long n) {
		return n * minutes(60);
	}

	private static long days(long n) {
		return n * hours(24);
	}

	private static long months(long n) {
		return n * days(30);
	}

}
