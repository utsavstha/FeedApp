package com.example.utsavstha.feedapp;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by utsavstha on 7/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class FeedTest {
    @Rule
    public ActivityTestRule<FeedsActivity> feedsActivityActivityTestRule =
            new ActivityTestRule<>(FeedsActivity.class);

    @Test
    public void testFeedsActivity(){
        onView(withId(R.id.rv_feeds)).check(matches(isDisplayed()));
    }
}
