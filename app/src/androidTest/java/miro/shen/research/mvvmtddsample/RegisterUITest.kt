package miro.shen.research.mvvmtddsample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RegisterUITest {
    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun wrongLoginIdFormat_should_alert() {

        //輸入錯誤格式的帳號
        onView(withId(R.id.loginId))
            .perform(typeText("1111"), closeSoftKeyboard())

        //輸入正確格式的密碼
        onView(withId(R.id.password))
            .perform(typeText("A112342626"), closeSoftKeyboard())

        //點選註冊按鈕
        onView(withId(R.id.send))
            .perform(click())

        //註冊失敗，Alert
        onView(withText("錯誤"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))

    }

    @Test
    fun register_success_should_starActivity() {
//        IdlingRegistry.getInstance().register(Idling.idlingResource)

        onView(withId(R.id.loginId))
            .perform(typeText("a123456789"), closeSoftKeyboard())

        onView(withId(R.id.password))
            .perform(typeText("a111111111"), closeSoftKeyboard())

        onView(withId(R.id.send))
            .perform(click())

        Thread.sleep(3000)

        onView(withText("註冊成功"))
            .check(matches(isDisplayed()))

//        IdlingRegistry.getInstance().unregister(Idling.idlingResource)
    }
}