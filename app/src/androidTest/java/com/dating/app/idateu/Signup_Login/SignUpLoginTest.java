package com.dating.app.idateu.Signup_Login;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import com.dating.app.idateu.R;
import com.dating.app.idateu.SignUp_LogIn.SignUpLogIn;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class SignUpLoginTest
{
    ViewInteraction email = onView(withId(R.id.emailInput));
    ViewInteraction logIn = onView(withId(R.id.login_button));
    ViewInteraction pass = onView(withId(R.id.passwordInput));

    @Rule
    public ActivityTestRule<SignUpLogIn> rule = new
            ActivityTestRule<SignUpLogIn>(SignUpLogIn.class);

    @Test
    public void displayErrorInvalidEmail()
        {
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        email.perform(typeText("inValid@Email"));
        Espresso.closeSoftKeyboard();
        logIn.perform(click());
        email.check(matches(hasErrorText("Invalid Email")));
        }

    @Test
    public void LandScapeErrorInvalidEmail()
    {
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        email.perform(typeText("inValid@Email"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView))
                .perform(swipeUp());
        logIn.perform(click());
        email.check(matches(hasErrorText("Invalid Email")));
    }

    @Test
    public void displayErrorInvalidPass()
        {
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        email.perform(typeText("Valid@Email.com"));
        Espresso.closeSoftKeyboard();
        pass.perform(typeText(""));
        Espresso.closeSoftKeyboard();
        logIn.perform(click());
        pass.check(matches(hasErrorText("Invalid Password")));
        }

    @Test
    public void LandScapeErrorInvalidPass()
    {
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        email.perform(typeText("Valid@Email.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView))
                .perform(swipeUp());
        logIn.perform(click());
        pass.check(matches(hasErrorText("Invalid Password")));
    }

}
