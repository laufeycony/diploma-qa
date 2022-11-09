package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import screenElements.NewsCreationAndEditingScreen;
import screenElements.NewsScreen;

public class NewsSteps {

    public static void initiateTheCreationOfNews() {
        Allure.step("Начинаем создавать новость (переход к созданию новости)");
        NewsScreen.editNewsButton.perform(click());
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
        NewsScreen.addNewsButton.perform(click());
        NewsCreationAndEditingScreen.titleOfNewsCreatingWindow.check(matches(isDisplayed()));
    }

    public static void initiateNewsEditing(String title) throws InterruptedException {
        Allure.step("Переход к редактированию ранее созданной новости");
        NewsScreen.editNewsButton.perform(click());
        Thread.sleep(3000);
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
        onView(withId(R.id.sort_news_material_button)).perform(click());
        MainHelper.isDisplayedWithSwipe(onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title)))), 1, true);
        onView(allOf(withId(R.id.edit_news_item_image_view), hasSibling(withText(title)))).perform(click());
        NewsCreationAndEditingScreen.titleOfEditingNewsWindow.check(matches(isDisplayed()));
    }

    public static void checkNewsData(String title, String description) {
        Allure.step("Убеждаемся, что соданная новость содержит ранее введенные данные");
        if (MainHelper.isDisplayedWithSwipe(onView(withText(title)), 1, true)) {
            onView(withText(title)).check(matches(isDisplayed())).perform(click());
            onView(withText(description)).check(matches(isDisplayed()));
        }
    }

    public static void checkFirstNewsDataAfterEdit(String category, String description, String currentDate) {
        Allure.step("Провека первой новости после редактирования");
        NewsScreen.firstCardNews.perform(click());
        NewsScreen.firstNewsItemTitle.check(matches(withText(category)));
        NewsScreen.firstNewsItemDescription.check(matches(withText(description)));
        NewsScreen.firstNewsItemDate.check(matches(withText(currentDate)));
    }

    public static void checkThatNewsDoesNotExist(String title, String description) throws InterruptedException {
        Allure.step("Проверка того, что новость не существует в блоке новостей");
        NewsScreen.firstCardNews2.perform(actionOnItemAtPosition(0, click()));
        NewsScreen.firstNewsItemTitle2.check(matches(not(withText(title))));
        NewsScreen.firstNewsItemDescription2.check(matches(not(withText(description))));
    }

    public static void deleteNews(String title) {
        Allure.step("Удалить ранее созданную новость новость");
        // пересортировать новости (для стабильности свайпа)
        onView(withId(R.id.sort_news_material_button)).perform(click());
        // свайп к ранее созданной новости
        MainHelper.isDisplayedWithSwipe(onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title)))), 1, true);
        // непосредственное удаление новости
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(title)))).perform(click());
        NewsScreen.okButton.check(matches(isDisplayed()));
        // подтверждаем удаление новости
        NewsScreen.okButton.perform(click());
        // проверка возвращения к новостям (видна кнопка добавления новости)
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
    }

    public static void checkNewsStatus(String chosenCategory, String currentDate, String finalStatus) {
        Allure.step("Проверить статус новости");
        onView(withId(R.id.sort_news_material_button)).perform(click());
        MainHelper.isDisplayedWithSwipe(onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(chosenCategory)))), 1, true);
        // проверяем категорию
        onView(allOf(withId(R.id.news_item_published_text_view), hasSibling(withText(chosenCategory)))).check(matches(withText(finalStatus)));
        // на всякий случай проверяем дату создания и публикации (текущая)
        onView(allOf(withId(R.id.news_item_publication_date_text_view), hasSibling(withText(chosenCategory)))).check(matches(withText(currentDate)));
    }

    public static void goToEditingModeForNews() {
        NewsScreen.editNewsButton.perform(click());
        NewsCreationAndEditingScreen.titleOfEditingNewsWindow.check(matches(isDisplayed()));
    }

}
