package com.example.loginscreen.view

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.loginscreen.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    private lateinit var scenario: ActivityScenario<LoginScreen>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(LoginScreen::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testAddingEmailAndPasscode() {

        val email = "gaurav@gmail.com"
        val passcode = "sam@123"

        onView(withId(R.id.edittext_username)).perform(ViewActions.typeText(email))
        onView(withId(R.id.edittext_password)).perform(
            ViewActions.typeText(passcode),
            closeSoftKeyboard()
        )

        //Espresso.closeSoftKeyboard()

        onView(withId(R.id.login)).perform(ViewActions.click())


    }

    @Test
    fun testCase_wrongEmailAddress() {

        val email = "gaurav"
        val passcode = "sam@123"

        onView(withId(R.id.edittext_username)).perform(ViewActions.typeText(email))
        onView(withId(R.id.edittext_password)).perform(
            ViewActions.typeText(passcode),
            closeSoftKeyboard()
        )

        onView(withId(R.id.login)).perform(ViewActions.click())


    }

    @Test
    fun testCase_wrongPassword() {

        val email = "gaurav@gmail.com"
        val passcode = "sam@12345"

        onView(withId(R.id.edittext_username)).perform(ViewActions.typeText(email))
        onView(withId(R.id.edittext_password)).perform(
            ViewActions.typeText(passcode),
            closeSoftKeyboard()
        )


        onView(withId(R.id.login)).perform(ViewActions.click())


    }

    @Test
    fun testCase_shortPassword() {

        val email = "gaurav@gmail.com"
        val passcode = "sam@1"

        onView(withId(R.id.edittext_username)).perform(ViewActions.typeText(email))
        onView(withId(R.id.edittext_password)).perform(
            ViewActions.typeText(passcode),
            closeSoftKeyboard()
        )


        onView(withId(R.id.login)).perform(ViewActions.click())


    }


    @Test
    fun testCase_emptydata() {

        val email = ""
        val passcode = ""

        onView(withId(R.id.edittext_username)).perform(ViewActions.typeText(email))
        onView(withId(R.id.edittext_password)).perform(
            ViewActions.typeText(passcode),
            closeSoftKeyboard()
        )


        onView(withId(R.id.login)).perform(ViewActions.click())


    }

    /* @After
     fun lastSeen(){

         scenario.moveToState(Lifecycle.State.INITIALIZED)
     }*/
}