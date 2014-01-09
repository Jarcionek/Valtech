package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class StringMatchers {

	public static Matcher<String> matchesRegex(final String expectedRegex) {
		return new TypeSafeDiagnosingMatcher<String>() {

			@Override
			protected boolean matchesSafely(String item, Description mismatchDescription) {
				if (item.matches(expectedRegex)) {
					return true;
				} else {
					mismatchDescription.appendText("string \"" + item + "\" does not match it");
					return false;
				}
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("a string matching regex \"" + expectedRegex + "\"");
			}
		};
	}

}
