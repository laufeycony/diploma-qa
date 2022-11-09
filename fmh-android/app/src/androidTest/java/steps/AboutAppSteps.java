package steps;

import static androidx.test.espresso.action.ViewActions.click;


import io.qameta.allure.kotlin.Allure;
import screenElements.AboutAppScreen;

public class AboutAppSteps {

    public static void goToPrivacyPolicy() {
        Allure.step("Переход к политике конфиденциальности");
        AboutAppScreen.privacyPolicyValue.perform(click());
    }

    public static void goToTermsOfUse() {
        Allure.step("Переход к пользовательскому соглашению");
        AboutAppScreen.termsOfUseValue.perform(click());
    }

}
