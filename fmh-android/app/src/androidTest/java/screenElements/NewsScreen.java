package screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import additional.MainHelper;
import ru.iteco.fmhandroid.R;


public class NewsScreen {

    // Заголовок блока "Новости"
    public static ViewInteraction titleOfNewsBlock = onView(withText("News"));

    // Элементы раскрытой новости
    public static ViewInteraction firstCardNews = onView(MainHelper.withIndex(withId(R.id.news_item_material_card_view), 0));
    public static ViewInteraction firstCardNews2 = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    MainHelper.childAtPosition(
                            withId(R.id.all_news_cards_block_constraint_layout),
                            0)));
    public static ViewInteraction firstNewsItemTitle = onView(MainHelper.withIndex(withId(R.id.news_item_title_text_view), 0));
    public static ViewInteraction firstNewsItemTitle2 = onView(allOf(withId(R.id.news_item_title_text_view), withParent(withParent(MainHelper.withIndex(withId(R.id.news_item_material_card_view), 0)))));
    public static ViewInteraction firstNewsItemDescription = onView(MainHelper.withIndex(withId(R.id.news_item_description_text_view), 0));
    public static ViewInteraction firstNewsItemDescription2 = onView(allOf(withId(R.id.news_item_description_text_view), withParent(withParent(MainHelper.withIndex(withId(R.id.news_item_material_card_view), 0)))));
    public static ViewInteraction firstNewsItemDate = onView(MainHelper.withIndex(withId(R.id.news_item_date_text_view), 0));

    // Кнопка редактирования новостей
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));

    // Создание новости
    public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));

    // Кнопка "ОК"
    public static ViewInteraction okButton = onView(withText("OK"));

    // Сортировка новостей
    public static ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
}
