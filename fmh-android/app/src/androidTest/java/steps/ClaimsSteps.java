package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import io.qameta.allure.kotlin.Allure;
import screenElements.ClaimCreationAndEditingScreen;
import screenElements.ClaimScreen;
import screenElements.ClaimsScreen;

public class ClaimsSteps {

    public static void initiateTheCreationOfClaim() {
        Allure.step("Начинаем создавать заявку (переход в раздел создания заявки)");
        ClaimsScreen.addNewClaimButton.perform(click());
        ClaimCreationAndEditingScreen.titleOfClaimsCreatingPage.check(matches(isDisplayed()));
    }

    public static void goToFirstClaimFromClaimsBlock() {
        Allure.step("Переход к первой заявке из блока заявок");
        ClaimsScreen.firstClaimCard.perform(click());
        ClaimScreen.titleTextOfClaim.check(matches(isDisplayed()));
    }
}
