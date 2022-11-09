package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static additional.MainHelper.isDisplayedWithSwipe;

import io.qameta.allure.kotlin.Allure;
import screenElements.ClaimScreen;
import screenElements.CommentScreen;

public class ClaimSteps {

    public static void checkBasicElementsOfClaim() {
        Allure.step("Проверка, основных элементов заявки: тема, исполнитель, плановая дата");
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
        ClaimScreen.executorTextOfClaim.check(matches(isDisplayed()));
        ClaimScreen.planeDateOfClaim.check(matches(isDisplayed()));
    }

    public static void scrollToLastComment() {
        Allure.step("Скрол к последнему комментарию (которого НЕТ)");
        isDisplayedWithSwipe(onView(withText("hjdwdwqfrgDEFEyveEUBU")), 4, true);
        ClaimScreen.buttonToAddComment.check(matches(isDisplayed()));
    }

    public static void initiateCommentCreation() {
        Allure.step("Нажать на кнопу добавления комментария");
        ClaimScreen.buttonToAddComment.perform(click());
        CommentScreen.saveButton.check(matches(isDisplayed()));
    }

    public static void isCommentDisplayed(String comment) {
        Allure.step("Поиск комментария");
        onView(withText(comment)).check(matches(isDisplayed()));
    }

    public static void commentDoesNotExist(String comment) {
        Allure.step("Поиск комментария");
        onView(withText(comment)).check(doesNotExist());
    }
}

