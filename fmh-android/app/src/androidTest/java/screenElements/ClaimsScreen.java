package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import additional.MainHelper;
import ru.iteco.fmhandroid.R;

public class ClaimsScreen {

    // Название блока
    public static ViewInteraction titleOfClaimsBlock = onView(withText("Claims"));

    // Создание заявки
    public static ViewInteraction addNewClaimButton = onView(withId(R.id.add_new_claim_material_button));

    // Переход к заявке
    public static ViewInteraction firstClaimCard = onView(MainHelper.withIndex(withId(R.id.claim_list_card), 0));

    // Разное
    public static ViewInteraction containerForClaims = onView(withId(R.id.claim_list_recycler_view));
    public static ViewInteraction filtersButton = onView(withId(R.id.filters_material_button));

}
