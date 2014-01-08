package application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApplicationAcceptanceTest {

	@Test
	public void usersCanPostToAndSeeTheirWall() {
		Application app = new Application(new Users());

		app.execute("Alice -> I love the weather today");
		app.execute("Bob -> Damn! We lost!");
		app.execute("Bob -> Good game though.");

		assertThat(app.execute("Alice"), is(equalTo("I love the weather today")));
		assertThat(app.execute("Bob"), is(equalTo("Damn! We lost!\nGood game though.")));
	}

}
