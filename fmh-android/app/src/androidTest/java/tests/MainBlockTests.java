package tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import steps.AuthorizationSteps;
import steps.ClaimSteps;
import steps.MainSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class MainBlockTests {

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
    @DisplayName("Наличие разделов \"Новости\" и \"Завки\" в главном блоке")
    public void newsAndClaimsPartsShouldBeInMainBLock() {
        MainSteps.checkIfNewsPartitionExists();
        MainSteps.checkIfClaimsPartitionExists();
    }

    @Test
    @DisplayName("Раскрытие новости в главном блоке")
    public void newsCanBeExpandedInMainBlock() {
        MainSteps.expandFirstNewsInMainBlock();
        MainSteps.checkDescriptionOfFirstNews();
    }


    @Test // нестабильный тест в эмуляторе (отдельно проходит)
    @DisplayName("Переход к заявке в главном блоке")
    public void shouldGoToFirstClaimInMainBlock() throws InterruptedException {
        MainSteps.expandOrHideNewsPart();
        MainSteps.goToFirstClaimFromMainBlock();
        Thread.sleep(3000);
        ClaimSteps.checkBasicElementsOfClaim();
    }
}
