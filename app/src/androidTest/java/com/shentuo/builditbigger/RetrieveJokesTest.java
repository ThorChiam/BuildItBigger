package com.shentuo.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by ShentuoZhan on 16/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class RetrieveJokesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        mActivityRule.getActivity();
    }

    @Test
    public void retrieveJokes() {
        // Press the button.
        onView(withId(R.id.tell_joke_btn)).perform(click());

        // Check that the text not empty or error message.
        onView(withId(R.id.jokeContents)).check(matches(notNullValue()));
        onView(withId(R.id.jokeContents)).check(matches(not(withText(""))));
        onView(withId(R.id.jokeContents))
                .check(matches(not(withText(mActivityRule.getActivity().getString(R.string.error_message)))));
    }
}
