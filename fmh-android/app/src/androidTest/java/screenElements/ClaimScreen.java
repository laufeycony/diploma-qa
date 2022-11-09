package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimScreen {

    // Элементы раскрытой заявки (предварительно перейти в заявку)
    public static ViewInteraction titleTextOfClaim = onView(withId(R.id.title_text_view));
    public static ViewInteraction executorTextOfClaim = onView(withId(R.id.executor_name_text_view));
    public static ViewInteraction planeDateOfClaim = onView(withId(R.id.plane_date_text_view));
     // Другое
    public static ViewInteraction buttonToAddComment = onView(withId(R.id.add_comment_image_button));
}
