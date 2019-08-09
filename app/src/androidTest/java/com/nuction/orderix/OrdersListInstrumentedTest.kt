package com.nuction.orderix

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nuction.orderix.activities.MainActivity
import com.nuction.orderix.adapters.OrderAdapter
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class OrdersListInstrumentedTest {


    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().context
        assertEquals("com.nuction.orderix.test", appContext.packageName)
    }


    @Test
    fun openOrderActivityToEdit() {

        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<OrderAdapter.OrderHolder>(0, click())
            )

        //onData(withId(R.id.text_view_client)).perform(click())
//        onView(withId(R.id.button_main)).perform(click())
//        onView(withId(R.id.text_header)).check(matches(isDisplayed()))
//        onView(withId(R.id.button_second)).perform(click())
//        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()))
    }
//
//    @Test
//    fun textInputOutput() {
//        onView(withId(R.id.editText_main)).perform(
//            typeText("This is a test.")
//        )
//        onView(withId(R.id.button_main)).perform(click())
//        onView(withId(R.id.text_message)).check(
//            matches(withText("This is a test."))
//        )
//    }
}
