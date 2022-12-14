package tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import android.os.SystemClock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import steps.AuthorizationSteps;
import steps.ControlPanelSteps;
import steps.Wait;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTests {

    @Rule
    public ActivityTestRule<ru.iteco.fmhandroid.ui.AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";

    @Before
    public void sleep() throws InterruptedException {
        onView(isRoot()).perform(Wait.wait(R.id.enter_button, 6000));
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            ControlPanelSteps.logOut();
        }
    }

    @Test
    @DisplayName("Вход в личный кабинет с валидными данными (латинские символы)")
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.checkTradeMark();
        ControlPanelSteps.logOut();
    }

    @Test
    @DisplayName("Вход в личный кабинет с пустыми полями")
    public void shouldTryLogInWithEmptyField() throws InterruptedException {
        AuthorizationSteps.clickSignInButton();
        AuthorizationSteps.checkMessageThatFieldShouldNotBeEmpty(activityTestRule);
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void shouldLogOut() throws InterruptedException {
        AuthorizationSteps.logIn(validLogin, validPassword);
        ControlPanelSteps.logOut();
        AuthorizationSteps.isAuthorizationScreen();
    }
}
