package com.example.utsavstha.feedapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;
import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SignInTest {
    /*@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.utsavstha.feedapp", appContext.getPackageName());
    }*/

    @Rule
    public ActivityTestRule<SignInActivity> signInActivityActivityTestRule =
            new ActivityTestRule<>(SignInActivity.class);

    @Test
    public void login() throws InterruptedException {
        onView(withId(R.id.et_input_email))
                .perform(typeText("utsav@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.et_input_password))
                .perform(typeText("utsav1234"), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        Thread.sleep(1500);

        onView(withId(R.id.rv_feeds)).check(matches(isDisplayed()));
    }
}
