package screenElements;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class CommentScreen {

    public static ViewInteraction commentTestInputEditText = onView(withId(R.id.comment_test_input_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
}
