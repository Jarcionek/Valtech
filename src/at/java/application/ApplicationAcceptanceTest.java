package application;

import application.commands.PostCommandProcessor;
import application.commands.ReadCommandProcessor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApplicationAcceptanceTest {

	private Users users = new Users();
	private Application app = new Application(new PostCommandProcessor(users), new ReadCommandProcessor(users));

	@Test
	public void usersCanPostToAndSeeTheirWall() {
		app.execute("Alice -> I love the weather today");
		app.execute("Bob -> Damn! We lost!");
		app.execute("Bob -> Good game though.");

		assertThat(app.execute("Alice"), is(equalTo("I love the weather today")));
		assertThat(app.execute("Bob"), is(equalTo("Damn! We lost!\nGood game though.")));
	}

}
