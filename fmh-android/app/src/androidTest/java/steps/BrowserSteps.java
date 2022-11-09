package steps;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import io.qameta.allure.kotlin.Allure;

public class BrowserSteps {

    public static void checkTheSuccessfulTransitionToPrivacyPolicy() {
        Allure.step("Проверка перехода на сайт с политикой конфиденциальности");
        intended(allOf(
                hasData("https://vhospice.org/#/privacy-policy/"),
                hasAction(Intent.ACTION_VIEW)
        ));
    }

    public static void checkTheSuccessfulTransitionToTermsOfUse() {
        Allure.step("Проверка перехода на сайт с пользовательским соглашением");
        intended(allOf(
                hasData("https://vhospice.org/#/terms-of-use"),
                hasAction(Intent.ACTION_VIEW)
        ));
    }

}
