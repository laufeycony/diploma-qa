package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutAppScreen {

    public static ViewInteraction versionValue = onView(withText("1.0.0"));
    public static ViewInteraction privacyPolicyValue = onView(withText("https://vhospice.org/#/privacy-policy/"));
    public static ViewInteraction termsOfUseValue = onView(withText("https://vhospice.org/#/terms-of-use"));
    public static ViewInteraction goBackButton = onView(withId(R.id.about_back_image_button));
    public static ViewInteraction versionValueElement = onView(withId(R.id.about_version_value_text_view));
}
