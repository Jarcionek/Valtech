package application;

public class WallToStringConverter {

	private final Clock clock;

	public WallToStringConverter(Clock clock) {
		this.clock = clock;
	}

	public String convert(Wall wall) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Message message : wall) {
			stringBuilder.append("\n");
			stringBuilder.append(message.getMessage());
			stringBuilder.append(" (");
			stringBuilder.append(humanReadableTime(clock.now() - message.getTimestamp()));
			stringBuilder.append(" ago)");
		}
		return stringBuilder.delete(0, 1).toString();
	}

	private static String humanReadableTime(long difference) {
		if (difference < 1000) {
			return "0 seconds";
		}

		difference /= 1000;

		int seconds = (int) (difference % 60);
		difference /= 60;

		int minutes = (int) (difference % 60);
		difference /= 60;

		int hours = (int) (difference % 24);
		difference /= 24;

		int days = (int) (difference % 30);
		difference /= 30;

		int months = (int) difference;

		if (months > 0) {
			return months + " month" + sOrNothing(months);
		}

		if (days > 0) {
			return days + " day" + sOrNothing(days);
		}

		if (hours > 0) {
			return hours + " hour" + sOrNothing(hours);
		}

		if (minutes > 0) {
			return minutes + " minute" + sOrNothing(minutes);
		}

		return seconds + " second" + sOrNothing(seconds);
	}

	private static String sOrNothing(int number) {
		return number > 1 ? "s" : "";
	}

}
