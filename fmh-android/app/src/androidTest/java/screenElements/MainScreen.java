package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import additional.MainHelper;
import ru.iteco.fmhandroid.R;

public class MainScreen {

    public static ViewInteraction tradeMark = onView(withId(R.id.trademark_image_view));

    // Выход из приложения
    public static ViewInteraction authorizationButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOutButton = onView(withText("Log out"));

    // Меню
    public static ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction mainOfMenu = onView(withText("Main"));
    public static ViewInteraction claimsOfMenu = onView(withText("Claims"));
    public static ViewInteraction newsOfMenu = onView(withText("News"));
    public static ViewInteraction aboutOfMenu = onView(withText("About"));

    // Переход к блоку цитат о хосписе
    public static ViewInteraction goToQuotesBlockButton = onView(withId(R.id.our_mission_image_button));

    //
    public static ViewInteraction buttonToExpandOrHideNewsPart = onView(MainHelper.withIndex(withId(R.id.expand_material_button), 0));
    public static ViewInteraction containerListForNews = onView(withId(R.id.container_list_news_include_on_fragment_main));
    public static ViewInteraction buttonToExpandFirstNews = onView(MainHelper.withIndex(withId(R.id.view_news_item_image_view), 0));
    public static ViewInteraction descriptionOfFirstNews = onView(MainHelper.withIndex(withId(R.id.news_item_description_text_view), 0));

    // Заявки
    public static ViewInteraction containerListForClaims = onView(withId(R.id.container_list_claim_include_on_fragment_main));
    public static ViewInteraction firstClaim = onView(MainHelper.withIndex(withId(R.id.claim_list_card), 0));
   }
