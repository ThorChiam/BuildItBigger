package com.shentuo.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        new EndpointsAsyncTask().execute(mActivityRule.getActivity());
    }
}
