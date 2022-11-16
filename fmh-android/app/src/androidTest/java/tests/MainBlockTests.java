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
import steps.ClaimSteps;
import steps.MainSteps;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.Wait;

@RunWith(AllureAndroidJUnit4.class)
public class MainBlockTests {

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
        ClaimSteps.checkBasicElementsOfClaim();
    }
}
