package tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import steps.AuthorizationSteps;
import steps.ControlPanelSteps;
import steps.NewsCreationAndEditingSteps;
import steps.NewsSteps;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.Wait;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

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


    @Test // проблемы со стабильностью
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String chosenCategory = "Зарплата";
        String category = "no";
        String title = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.initiateTheCreationOfNews();
        NewsCreationAndEditingSteps.fillInTheNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsCreationAndEditingSteps.saveNews();
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.checkNewsData(chosenCategory, description);
        NewsSteps.goToEditingModeForNews();
        NewsSteps.deleteNews(chosenCategory);
        ControlPanelSteps.goToNewsBlock();
        onView(isRoot()).perform(Wait.wait(R.id.add_news_image_view, 6000));
        NewsSteps.checkThatNewsDoesNotExist(chosenCategory, description);
    }
}

