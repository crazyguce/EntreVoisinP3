package com.openclassrooms.entrevoisins.test_neighbour;

import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * craeate by Bailleul Tanguy on 06/01/2020.
 */
public class test_view_neighbour {
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mListNeighbourActivityActivityTestRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);


/**item clikable on list neighbour**/
    @Test
    public void isItemClickOpenProfil() {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.bigUsernameText)).check(matches(isDisplayed()));
    }

/**test profil view**/
    @Test
    public void profilIsPopulated()  {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.bigUsernameText)).check(matches(withText("Caroline")));
    }
/** test make favoris profil **/
    @Test
    public void favorisListIsPopulated() {
        //click on profil
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        // click fav button on profil
        onView(withId(R.id.favButton)).perform(click());
        // com back
        pressBack();
        // back favorit button
        swipeLeft();
        // click profil on favorit onglet
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        // verification name profil fav its same on neighbourg list
        onView(withId(R.id.bigUsernameText)).check(matches(withText("Caroline")));
    }

}
