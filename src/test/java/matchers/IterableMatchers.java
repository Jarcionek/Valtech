package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class IterableMatchers {

	public static Matcher<Iterable> sameIterableAs(final Object... expected) {
		return new TypeSafeDiagnosingMatcher<Iterable>() {
			@Override
			protected boolean matchesSafely(Iterable actual, Description mismatchDescription) {
				int i = 0;
				for (Object o : actual) {
					if (i == expected.length) {
						mismatchDescription.appendText("more elements than expected\n");
						mismatchDescription.appendText("unexpected element at position " + i + ": " + o);
						return false;
					}
					if (!sameBeanAs(expected[i]).matches(o)) {
						mismatchDescription.appendText("mismatch at position " + i + "\n");
						mismatchDescription.appendText("expected: " + expected[i] + "\n");
						mismatchDescription.appendText("but was: " + o);
						return false;
					}
					i++;
				}
				if (i < expected.length) {
					mismatchDescription.appendText("fewer elements than expected\n");
					mismatchDescription.appendText("expected iterable of size " + expected.length + " but was " + i + "\n");
					mismatchDescription.appendText("element not found: " + expected[i]);
					return false;
				}

				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("same iterable (in order) as " + asString(expected));
			}
		};
	}

	private static String asString(Object[] objects) {
		if (objects.length == 0) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Object object : objects) {
			sb.append(", ");
			sb.append(object);
		}
		return sb.delete(1, 3).append("}").toString();
	}

}
