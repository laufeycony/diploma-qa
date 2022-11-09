package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;

import additional.MainHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.NewsCreationAndEditingScreen;
import screenElements.NewsScreen;

public class NewsCreationAndEditingSteps {



    public static void fillInTheNewsFields(String emptyCategory, String choiceOfCategory, String chosenCategory, String category, String title, String emptyDate, String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime, String emptyDescription, String description) {
        Allure.step("Заполнение полей при создании/редактировании новости");
        // определяется позиция дочернего элемента
        Integer categoryPosition = null;
        if (chosenCategory == "Объявление") {
            categoryPosition = 0;
        } else if (chosenCategory == "День рождения") {
            categoryPosition = 1;
        } else if (chosenCategory == "Зарплата") {
            categoryPosition = 2;
        } else if (chosenCategory == "Профсоюз") {
            categoryPosition = 3;
        } else if (chosenCategory == "Праздник") {
            categoryPosition = 4;
        } else if (chosenCategory == "Массаж") {
            categoryPosition = 5;
        } else if (chosenCategory == "Благодарность") {
            categoryPosition = 6;
        } else if (chosenCategory == "Нужна помощь") {
            categoryPosition = 7;
        }
        // заполнение поля "Категория"
        if (emptyCategory == "no") {
            if (choiceOfCategory == "yes") {
                NewsCreationAndEditingScreen.buttonForShowingDropdownMenu.perform(click());
                // выбор категории (источник: https://stackoverflow.com/questions/29438569/dropdown-value-selection-using-espresso-android-with-dynamic-element-ids)
                Espresso.onData(Matchers.anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(categoryPosition).perform(ViewActions.click());
            } else {
                NewsCreationAndEditingScreen.categoryTextInputOfNews.perform(replaceText(category));
                NewsCreationAndEditingScreen.categoryTextInputOfNews.check(matches(isDisplayed()));
            }
        } else {
            NewsCreationAndEditingScreen.titleTextInputOfNews.perform(replaceText(title));
            NewsCreationAndEditingScreen.titleTextInputOfNews.check(matches(withText(title)));
        }
        // заполнение поля "Дата"
        if (emptyDate == "no") {
            NewsCreationAndEditingScreen.publicationDateTextInputOfNews.perform(click());
            NewsCreationAndEditingScreen.okButton.perform(click());
        }
        // заполнение поля "Время"
        if (emptyTime == "no") {
            if (withDialPadOrTextInput == "dial") {
                NewsCreationAndEditingScreen.timeTextInputOfNews.perform(click());
                if (saveOrCancelTime == "save") {
                    NewsCreationAndEditingScreen.okButton.perform(click());
                } else {
                    NewsCreationAndEditingScreen.cancelButton.perform(click());
                }
            } else {
                NewsCreationAndEditingScreen.timeTextInputOfNews.perform(click());
                NewsCreationAndEditingScreen.buttonForSwitchToTextInput.perform(click());
                onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(2, 23));
                NewsCreationAndEditingScreen.okButton.perform(click());
                NewsCreationAndEditingScreen.timeTextInputOfNews.check(matches(withText("02:23")));
            }
        }
        // заполнение поля "Описание"
        if (emptyDescription == "no") {
            NewsCreationAndEditingScreen.descriptionTextInputOfNews.perform(replaceText(description));
            NewsCreationAndEditingScreen.descriptionTextInputOfNews.check(matches(withText(description)));
        }
    }

    public static void saveNews() {
        Allure.step("Сохранить новость");
        NewsCreationAndEditingScreen.saveButtonOfNews.perform(click());
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
    }

    public static void сancelSavingNews() {
        Allure.step("Отмена сохранения новости");
        NewsCreationAndEditingScreen.cancelButtonOfNews.perform(click());
        NewsCreationAndEditingScreen.okButton.perform(click());
        NewsScreen.addNewsButton.check(matches(isDisplayed()));
    }

    public static void timeInput(String hours, String minutes) {
        Allure.step("Ввод времени вручную");
        NewsCreationAndEditingScreen.timeTextInputOfNews.perform(click());
        NewsCreationAndEditingScreen.buttonForSwitchToTextInput.perform(click());
        onView(MainHelper.withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 0)).perform(replaceText(hours));
        onView(MainHelper.withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 1)).perform(replaceText(minutes));
        NewsCreationAndEditingScreen.okButton.perform(click());
    }

    public static void checkMessageOfTimeInputError() {
        Allure.step("Проверка, что появляется предупреждение о необходимости заполнить поля времени валидными данными");
        onView(withText("Enter a valid time")).check(matches(isDisplayed()));
    }

    public static void checkNewsInEditMode(String chosenCategory, String currentDate, String description) {
        Allure.step("Проверка содержимого новости в режиме редактирования");
        NewsCreationAndEditingScreen.categoryTextInputOfNews.check(matches(withText(chosenCategory)));
        NewsCreationAndEditingScreen.titleTextInputOfNews.check(matches(withText(chosenCategory)));
        NewsCreationAndEditingScreen.publicationDateTextInputOfNews.check(matches(withText(currentDate)));
        NewsCreationAndEditingScreen.descriptionTextInputOfNews.check(matches(withText(description)));
    }

    public static void changeNewsStatus() {
        Allure.step("Изменить статус новости");
        NewsCreationAndEditingScreen.statusOfNewsSwitcher.perform(click());
        // проверка не предусмотрена (результат зависит от предыдущего состояния)
    }

    public static void checkMessageThatFieldShouldBeFilled(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Fill empty fields")));
    }
}
