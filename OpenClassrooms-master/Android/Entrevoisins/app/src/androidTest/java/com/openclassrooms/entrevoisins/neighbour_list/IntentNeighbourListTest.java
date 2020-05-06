package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DisplayNeighbourActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.Matchers.allOf;


/**
     * Test class for list of neighbours
     */
    @RunWith(AndroidJUnit4.class)
    public class IntentNeighbourListTest {

        @Rule
        public IntentsTestRule<DisplayNeighbourActivity> intentsTestRule =
            new IntentsTestRule<>(DisplayNeighbourActivity.class);


        @Test
        public void runDisplayNeighbourActivity(){
        // we perform a click on a neighbour at position 0
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //  check is activity was started
        intended(hasComponent(DisplayNeighbourActivity.class.getName()));

    }
}
