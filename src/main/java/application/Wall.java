package application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Stores messages in reverted order - the most recent message (with greatest timestamp in millis) will be first.
 */
public class Wall implements Iterable<Message> {

	private List<Message> list = new LinkedList<Message>();

	public void add(Message message) {
		list.add(0, message);
	}

	@Override
	public Iterator<Message> iterator() {
		return list.iterator();
	}

}
