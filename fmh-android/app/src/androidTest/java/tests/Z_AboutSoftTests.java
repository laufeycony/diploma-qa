package tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static screenElements.AuthorizationScreen.signInButton;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import steps.AboutAppSteps;
import steps.AuthorizationSteps;
import steps.BrowserSteps;
import steps.ControlPanelSteps;
import steps.Wait;


@RunWith(AllureAndroidJUnit4.class)
public class Z_AboutSoftTests {

    @Rule
    public IntentsTestRule intentsTestRule =
            new IntentsTestRule(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        onView(isRoot()).perform(Wait.wait(R.id.main_menu_image_button, 6000));
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.logIn("login2", "password2");
    }

    @Test
    @DisplayName("Переход к политике конфиденциальности по ссылке")
    public void shouldGoToPrivacyPolicy() {
        ControlPanelSteps.goToAboutBlock();
        AboutAppSteps.goToPrivacyPolicy();
        BrowserSteps.checkTheSuccessfulTransitionToPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход к пользовательскому соглашению по ссылке")
    public void shouldGoToUserAgreement() {
        ControlPanelSteps.goToAboutBlock();
        AboutAppSteps.goToTermsOfUse();
        BrowserSteps.checkTheSuccessfulTransitionToTermsOfUse();
    }
}
