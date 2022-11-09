package tests;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AuthorizationSteps;
import steps.ClaimCreationSteps;
import steps.ClaimsSteps;
import steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimCreationTest {

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
    @DisplayName("Пустой ввод в поле \"Тема\" при создании заявки")
    public void shouldTryCreateClaimWithEmptyTitle() {
        String emptyTitle = "yes";
        String title = "no";
        String emptyExecutor = "no";
        String withExecutorChoice = "yes";
        String chosenExecutor = "Netology Diplom QAMID";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "New description";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, withExecutorChoice, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimCreationSteps.checkMessageThatFieldsShouldBeFilled(activityTestRule);
    }

    @Test
    @DisplayName("Пустой ввод в поле \"Описание\" при создании заявки")
    public void shouldTryCreateClaimWithEmptyDescription() {
        String emptyTitle = "no";
        String title = "QAMIDK78";
        String emptyExecutor = "no";
        String withExecutorChoice = "yes";
        String chosenExecutor = "Netology Diplom QAMID";
        String executor = "yes";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "yes";
        String description = "-";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.fillInTheClaimFields(emptyTitle, title, emptyExecutor, withExecutorChoice, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimCreationSteps.saveClaim();
        ClaimCreationSteps.checkMessageThatFieldsShouldBeFilled(activityTestRule);
    }

    @Test
    @DisplayName("Ввод > 24 часов в поле часы при создании заявки")
    public void shouldInputMoreThan24HoursWhenClaimIsBeingCreated() {
        String invalidHours = "76";
        String validMinutes = "23";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.timeInput(invalidHours, validMinutes);
        ClaimCreationSteps.checkMessageOfTimeInputError();
    }

    @Test
    @DisplayName("Ввод >60 в поле минуты при создании заявки")
    public void shouldInputMoreThan60MinutesWhenClaimIsBeingCreated() {
        String validHours = "22";
        String invalidMinutes = "68";
        ControlPanelSteps.goToClaimsBlock();
        ClaimsSteps.initiateTheCreationOfClaim();
        ClaimCreationSteps.timeInput(validHours, invalidMinutes);
        ClaimCreationSteps.checkMessageOfTimeInputError();
    }
}
