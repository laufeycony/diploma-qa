package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.AppActivity;
import screenElements.ClaimScreen;
import screenElements.CommentScreen;

public class CommentSteps {

    public static void fillInTheCommentField(String comment) {
        Allure.step("Заполнение поля комментария");
        CommentScreen.commentTestInputEditText.perform(replaceText(comment));
        CommentScreen.commentTestInputEditText.check(matches(withText(comment)));
    }

    public static void saveComment() {
        Allure.step("Сохранить комментарий");
        CommentScreen.saveButton.perform(click());
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
    }

    public static void cancelCommentCreation() {
        CommentScreen.cancelButton.perform(click());
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
    }

    public static void checkMessageThatFieldShouldBeFilled(ActivityTestRule<AppActivity> activityTestRule) {
        onView(withText("The field cannot be empty."))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("The field cannot be empty.")));
    }
}
