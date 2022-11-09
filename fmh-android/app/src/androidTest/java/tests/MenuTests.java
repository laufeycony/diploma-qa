package tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AuthorizationSteps;
import steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.AboutAppScreen;
import screenElements.ClaimsScreen;
import screenElements.MainScreen;
import screenElements.NewsScreen;

@RunWith(AllureAndroidJUnit4.class)
public class MenuTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

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
    @DisplayName("Раскрытие элементов меню при нахождении в главном блоке")
    public void menuElementsShouldBeVisible() {
        MainScreen.menuButton.perform(click());
        ControlPanelSteps.checkThatAllItemsOfMenuAreDisplayed();
        // условно лишний шаг перехода в раздел Жалоб, который позволяет затем выйти из приложения
        MainScreen.claimsOfMenu.perform(click());
    }

    @Test
    @DisplayName("Переход в главный блок при помощи меню")
    public void shouldGoToMainBlockWithMenu() {
        MainScreen.menuButton.perform(click());
        // чтобы проверить переход в главный блог, необходимо изначально перейти в любой другой
        MainScreen.claimsOfMenu.perform(click());
        MainScreen.menuButton.perform(click());
        MainScreen.mainOfMenu.perform(click());
        MainScreen.containerListForNews.check(matches(isDisplayed()));
        MainScreen.containerListForClaims.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход в блок \"Заявки\" при помощи меню")
    public void shouldGoToClaimsBlockWithMenu() {
        ControlPanelSteps.goToClaimsBlock();
        ClaimsScreen.containerForClaims.check(matches(isDisplayed()));
        ClaimsScreen.filtersButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход в блок \"Новости\" при помощи меню")
    public void shouldGoToNewsBlockWithMenu() {
        ControlPanelSteps.goToNewsBlock();
        NewsScreen.sortNewsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход в блок \"О приложении\" при помощи меню")
    public void shouldGoToAboutBlockWithMenu() {
        ControlPanelSteps.goToAboutBlock();
        AboutAppScreen.versionValueElement.check(matches(isDisplayed()));
        AboutAppScreen.goBackButton.perform(click());
    }
}
