package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;

public class QuotesSteps {

    public static void findQuoteWith(String title) {
        Allure.step("Поиск цитаты с заголовком");
        MainHelper.isDisplayedWithSwipe(onView(withText(title)), 3, true);
    }

}
