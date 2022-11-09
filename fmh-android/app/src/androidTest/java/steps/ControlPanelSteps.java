package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import screenElements.AboutAppScreen;
import screenElements.ClaimsScreen;
import screenElements.MainScreen;
import screenElements.NewsScreen;
import screenElements.QuotesScreen;

public class ControlPanelSteps {

    public static void logOut() {
        Allure.step("Выход из личного кабинета");
        MainScreen.authorizationButton.perform(click());
        MainScreen.logOutButton.check(matches(isDisplayed()));
        MainScreen.logOutButton.perform(click());
        // проверка не производится для экономии ресурсов
    }

    public static void checkTradeMark() {
        Allure.step("Проверка видимости эмблемы приложения");
        MainScreen.tradeMark.check(matches(isDisplayed()));
    }

    public static void goToNewsBlock() {
        Allure.step("Переход в блок \"Новости\"");
        MainScreen.menuButton.perform(click());
        MainScreen.newsOfMenu.check(matches(isDisplayed()));
        MainScreen.newsOfMenu.perform(click());
        NewsScreen.titleOfNewsBlock.check(matches(isDisplayed()));
    }

    public static void goToClaimsBlock() {
        Allure.step("Переход в блок \"Заявки\"");
        MainScreen.menuButton.perform(click());
        MainScreen.claimsOfMenu.check(matches(isDisplayed()));
        MainScreen.claimsOfMenu.perform(click());
        ClaimsScreen.titleOfClaimsBlock.check(matches(isDisplayed()));
    }

    public static void goToAboutBlock() {
        Allure.step("Переход в блок \"О приложении\"");
        MainScreen.menuButton.perform(click());
        MainScreen.aboutOfMenu.check(matches(isDisplayed()));
        MainScreen.aboutOfMenu.perform(click());
        AboutAppScreen.versionValue.check(matches(isDisplayed()));
    }

    public static void goToQuotesBlock() {
        Allure.step("Переход в блок цитат о хосписе");
        MainScreen.goToQuotesBlockButton.perform(click());
        QuotesScreen.firstMissionTitleValue.check(matches(isDisplayed()));
    }

    public static void checkThatAllItemsOfMenuAreDisplayed() {
        Allure.step("Провека, что видны все пункты меню");
        MainScreen.mainOfMenu.check(matches(isDisplayed()));
        MainScreen.claimsOfMenu.check(matches(isDisplayed()));
        MainScreen.newsOfMenu.check(matches(isDisplayed()));
        MainScreen.aboutOfMenu.check(matches(isDisplayed()));
    }

}
