package com.fjmartins.webuy

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WebuyInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.fjmartins.webuy", appContext.packageName)
    }

    @Test
    fun homeIsDisplayedTest() {
        // Set up
        ActivityScenario.launch(MainActivity::class.java)

        // Verify
        onView(withId(R.id.fragment_home_parent)).check(matches(isDisplayed()))
    }

    // TODO: Note; ATM there is only one screen so there's no need to implement navigation tests but we could do it here.
}