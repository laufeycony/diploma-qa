package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationScreen {

    public static ViewInteraction authorization = onView(withText("Authorization"));
    public static ViewInteraction loginInput = onView(withId(R.id.login_test_input_edit_text));
    public static ViewInteraction passwordInput = onView(withId(R.id.pass_test_input_edit_text));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));

}
