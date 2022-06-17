package com.moringaschool.myrestaurants;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

import android.view.View;

import com.moringaschool.myrestaurants.ui.RestaurantListActivity;

public class RestaurantListActivityInstrumentationTest {
    @Rule
    public ActivityScenarioRule<RestaurantListActivity> activityTestRule =
            new ActivityScenarioRule<>(RestaurantListActivity.class);

    private View activityDecorView;//what is this?

    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<RestaurantListActivity>() {
            @Override
            public void perform(RestaurantListActivity activity) {
                activityDecorView = activity.getWindow().getDecorView();
            }
        });
    }

//    @Test
//    public void listItemClickDisplaysToastWithCorrectRestaurant() {
//        String restaurantName = "Mi Mero Mole"; // Espresso to check that clicking on the first item (.atPosition(0)) in our ListView results in a Toast that displays "Mi Mero Mole"
//        onData(anything())//To interact with the data in an adapter we must use the onData() method rather than onView()
//                .inAdapterView(withId(R.id.listView))
//                .atPosition(0)
//                .perform(click());
//        onView(withText(restaurantName)).inRoot(withDecorView(not(activityDecorView)))
//                .check(matches(withText(restaurantName)));
//    }


}
