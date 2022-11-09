package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimCreationAndEditingScreen {


    public static ViewInteraction titleOfClaimsCreatingPage = onView(withText("Creating"));
    public static ViewInteraction titleTextInputOfClaim = onView(withId(R.id.title_edit_text));
    public static ViewInteraction executorTextInput = onView(withId(R.id.executor_drop_menu_text_input_layout));
    public static ViewInteraction buttonForShowingDropdownMenu = onView(withContentDescription("Show dropdown menu"));
    public static ViewInteraction dateInPlanOfClaim = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction timeInPlanOfClaim = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction buttonForSwitchToTextInput = onView(withContentDescription("Switch to text input mode for the time input."));
    public static ViewInteraction descriptionTextInputOfClaim = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButtonOfClaim = onView(withId(R.id.save_button));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction cancelButton = onView(withText("CANCEL"));

}
