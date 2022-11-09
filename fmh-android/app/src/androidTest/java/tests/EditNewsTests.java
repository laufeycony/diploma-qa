package tests;

import static androidx.test.espresso.action.ViewActions.click;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AuthorizationSteps;
import steps.ControlPanelSteps;
import steps.NewsCreationAndEditingSteps;
import steps.NewsSteps;
import ru.iteco.fmhandroid.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import additional.MainHelper;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.NewsScreen;

@RunWith(AllureAndroidJUnit4.class)
public class EditNewsTests {

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

    public void createNewsWithActiveStatus() {
        Allure.step("Создание новости с активным статусом");
        // общие параметры для создания/редактирования новости
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        // параметры новости
        String chosenCategory = "Зарплата";
        String description = "Description";
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.initiateTheCreationOfNews();
        NewsCreationAndEditingSteps.fillInTheNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsCreationAndEditingSteps.saveNews();
    }

    public void createNewsWithNotActiveStatus() throws InterruptedException {
        Allure.step("Создание заявки с НЕактивным статусом");
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        // параметры новости (должны совпадать с параметрами пердварительно созданной новости!!!!!)
        String chosenCategory = "Зарплата";
        String description = "Description";
        String finalStatus = "Not active";
        createNewsWithActiveStatus();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, создана
        NewsSteps.checkNewsData(chosenCategory, description);
        NewsSteps.initiateNewsEditing(chosenCategory);
        // убеждаемся, что для изменения статуса выбрана именно ранее созданная новость
        NewsCreationAndEditingSteps.checkNewsInEditMode(chosenCategory, currentDate, description);
        // изменение статуса
        NewsCreationAndEditingSteps.changeNewsStatus();
        NewsCreationAndEditingSteps.saveNews();
        Thread.sleep(5000);
        ControlPanelSteps.goToClaimsBlock();
        ControlPanelSteps.goToNewsBlock();
        NewsScreen.editNewsButton.perform(click());
        // проверка, что новость имеет статус "На активна"
        //NewsPage.checkNewsStatus(chosenCategory,currentDate, finalStatus);
    }

    @Test // низкая стабильностьь теста
    @DisplayName("Редактирование новости при заполнении всех полей валидными данными (кирилические символы, текущая дата, текущее время в формате циферблата)")
    public void editNewsWithValidData() throws InterruptedException {
        // общие параметры для создания/редактирования новости
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        // параметры старой новости
        String chosenCategory = "Зарплата";
        String description = "Description";
        // параметры для редактирования новости
        String newChosenCategory = "Массаж";
        String newDescription = "New description";
        // создаем новость
        createNewsWithActiveStatus();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, создана
        NewsSteps.checkNewsData(chosenCategory, description);
        // переход к редактированию новости
        NewsSteps.initiateNewsEditing(chosenCategory);
        // убеждаемся, что для редактирования выбрана именно ранее созданная новость
        Thread.sleep(2000);
        NewsCreationAndEditingSteps.checkNewsInEditMode(chosenCategory, currentDate, description);
        // редактирование новости
        NewsCreationAndEditingSteps.fillInTheNewsFields(emptyCategory, withCategoryChoice, newChosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, newDescription);
        NewsCreationAndEditingSteps.saveNews();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, отредактирована (данные обновились)
        NewsSteps.checkFirstNewsDataAfterEdit(newChosenCategory, newDescription, currentDate);
        // удаление новости
        NewsSteps.goToEditingModeForNews();
        NewsSteps.deleteNews(newChosenCategory);
    }

    @Test  // тест проходит (без удаления)
    @DisplayName("Изменение статуса с \"Активна\" на \"Не активна\" при редактировании новости")
    public void shouldChangeNewsStatusToNotActive() throws InterruptedException {
        // общие параметры для редактирования новости
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        // параметры новости (должны совпадать с параметрами пердварительно созданной новости!!!!!)
        String chosenCategory = "Зарплата";
        String description = "Description";
        String finalStatus = "Not active";
        // создаем новость
        createNewsWithActiveStatus();
        ControlPanelSteps.goToNewsBlock();
        // проверяем,что новость, действительно, создана
        NewsSteps.checkNewsData(chosenCategory, description);
        // переход к редактированию новости
        NewsSteps.initiateNewsEditing(chosenCategory);
        // убеждаемся, что для изменения статуса выбрана именно ранее созданная новость
        NewsCreationAndEditingSteps.checkNewsInEditMode(chosenCategory, currentDate, description);
        // изменение статуса
        NewsCreationAndEditingSteps.changeNewsStatus();
        NewsCreationAndEditingSteps.saveNews();
        Thread.sleep(5000);
        ControlPanelSteps.goToClaimsBlock();
        ControlPanelSteps.goToNewsBlock();
        NewsScreen.editNewsButton.perform(click());
        // проверка, что новость имеет статус "На активна"
        NewsSteps.checkNewsStatus(chosenCategory, currentDate, finalStatus);
        // проверка, что новость исчезла из панели новостей (допущение, что сохраненная ноовсть всегда показывается первой)
        ControlPanelSteps.goToClaimsBlock();
        ControlPanelSteps.goToNewsBlock();
        NewsSteps.checkThatNewsDoesNotExist(chosenCategory, description);
        // удаление новости
        NewsSteps.goToEditingModeForNews();
        NewsSteps.deleteNews(chosenCategory);
    }

    @Test // падает по техническим причинам!
    @DisplayName("Изменение статуса с \"Не активна\" на \"Активна\" при редактировании новости")
    public void shouldChangeNewsStatusToActive() throws InterruptedException {
        String chosenCategory = "Зарплата";
        String description = "Description";
        String finalStatus = "Active";
        String currentDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        createNewsWithNotActiveStatus();
        onView(MainHelper.withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click());
        onView(MainHelper.withIndex(withContentDescription("News editing button"), 0)).perform(click());
        // убеждаемся, что для изменения статуса выбрана именно ранее созданная новость
        NewsCreationAndEditingSteps.checkNewsInEditMode(chosenCategory, currentDate, description);
        // изменение статуса
        NewsCreationAndEditingSteps.changeNewsStatus();
        NewsCreationAndEditingSteps.saveNews();
        // проверка, что новость имеет статус активна
        NewsSteps.checkNewsStatus(chosenCategory, currentDate, finalStatus);
        // проверка, что новость снова видна в блоке "Новости"
        ControlPanelSteps.goToNewsBlock();
        //checkFirstNewsDataAfterEdit(chosenCategory,description,currentDate);
        NewsSteps.checkNewsData(chosenCategory, description);
    }

}

