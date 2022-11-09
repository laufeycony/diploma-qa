package tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AboutAppSteps;
import steps.AuthorizationSteps;
import steps.BrowserSteps;
import steps.ControlPanelSteps;

@RunWith(AllureAndroidJUnit4.class)
public class Z_AboutSoftTests {

    @Rule
    public IntentsTestRule intentsTestRule =
            new IntentsTestRule(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(7000);
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
