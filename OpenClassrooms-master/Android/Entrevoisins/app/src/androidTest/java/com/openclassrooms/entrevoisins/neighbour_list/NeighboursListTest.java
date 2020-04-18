
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We ensure there are 12 elements in the recyclerview
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * We ensure that a click on a itemView run DisplayNeighbourActivity and check if the name is the good one
     */
    @Test
    public void runDisplayNeighbourActivity() {
        // We perform a click on a neighbour at position 0
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // We check that the user is on a new activity and the name is the good one
        onView(withId(R.id.tv_name)).check(matches(withText("Caroline")));
    }

    /**
     * We add a neighbor to favorites and check that the list contains a favorite
     */
    @Test
    public void onlyFavorites() {
        // We perform a click on a neighbour at position 0
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // We perform a click on the fab favorite
        ViewInteraction floatingActionButton = onView(withId(R.id.fab_favorite));
        floatingActionButton.perform(click());
        // We perform a click on the return arrow
        ViewInteraction appCompatImageButton = onView(withId(R.id.ib_return));
        appCompatImageButton.perform(click());
        // We open the favorites List
        ViewInteraction tabView = onView(withContentDescription("Favorites"));
        tabView.perform(click());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));


    }
}