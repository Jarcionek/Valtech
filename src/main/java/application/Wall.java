package application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Wall implements Iterable<Message> {

	private List<Message> list = new LinkedList<Message>();

	public void add(Message message) {
		list.add(message);
	}

	@Override
	public Iterator<Message> iterator() {
		return list.iterator();
	}

}
