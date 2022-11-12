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
import screenElements.ClaimCreationAndEditingScreen;
import screenElements.ClaimsScreen;

public class ClaimCreationSteps {

    public static void timeInput(String hours, String minutes) {
        Allure.step("Ввод времени вручную");
        ClaimCreationAndEditingScreen.timeInPlanOfClaim.perform(click());
        ClaimCreationAndEditingScreen.buttonForSwitchToTextInput.perform(click());
        onView(MainHelper.withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 0)).perform(replaceText(hours));
        onView(MainHelper.withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 1)).perform(replaceText(minutes));
        ClaimCreationAndEditingScreen.okButton.perform(click());
    }

    public static void checkMessageOfTimeInputError() {
        Allure.step("Проверка, что появляется предупреждение о необходимости заполнить поля времени валидными данными");
        onView(withText("Enter a valid time")).check(matches(isDisplayed()));
    }
}
