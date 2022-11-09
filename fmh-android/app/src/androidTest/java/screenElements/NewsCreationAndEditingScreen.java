package screenElements;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class NewsCreationAndEditingScreen {

    // Элементы при создании/редактировании новости

    public static ViewInteraction titleOfEditingNewsWindow = onView(withText("Editing"));
    public static ViewInteraction titleOfNewsCreatingWindow = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public static ViewInteraction categoryTextInputOfNews = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction buttonForShowingDropdownMenu = onView(withContentDescription("Show dropdown menu"));
    public static ViewInteraction titleTextInputOfNews = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction publicationDateTextInputOfNews = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction buttonForSwitchToTextInput = onView(withContentDescription("Switch to text input mode for the time input."));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction cancelButton = onView(withText("CANCEL"));
    public static ViewInteraction timeTextInputOfNews = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction descriptionTextInputOfNews = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction statusOfNewsSwitcher = onView(withId(R.id.switcher));
    public static ViewInteraction saveButtonOfNews = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButtonOfNews = onView(withId(R.id.cancel_button));

}