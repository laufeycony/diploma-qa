package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import screenElements.ClaimScreen;
import screenElements.MainScreen;

public class MainSteps {

    public static void goToFirstClaimFromMainBlock() {
        Allure.step("Переход к первой заявке из главного блока");
        MainScreen.firstClaim.perform(click());
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
    }

    public static void expandFirstNewsInMainBlock() {
        Allure.step("Раскрыть первую новость в главном блоке");
        MainScreen.buttonToExpandFirstNews.perform(click());
        // проверка заложена в следующем методе
    }

    public static void checkDescriptionOfFirstNews() {
        Allure.step("Проверка видимости описания первой новости в главном блоке");
        MainScreen.descriptionOfFirstNews.check(matches(isDisplayed()));
    }

    public static void checkIfNewsPartitionExists() {
        Allure.step("Проверка видимости раздела новостей в главном блоке");
        MainScreen.containerListForNews.check(matches(isDisplayed()));
        onView(withText("News")).check(matches(isDisplayed()));
    }

    public static void checkIfClaimsPartitionExists() {
        Allure.step("Проверка видимости раздела заявок в главном блоке");
        MainScreen.containerListForClaims.check(matches(isDisplayed()));
        onView(withText("Claims")).check(matches(isDisplayed()));
    }

    public static void expandOrHideNewsPart() {
        Allure.step("Свернуть/раскрыть раздел с новостями");
        MainScreen.buttonToExpandOrHideNewsPart.perform(click());
        // проверка шага невозможна (результат разный в зависимости от предыдущего состояния)
    }
}
