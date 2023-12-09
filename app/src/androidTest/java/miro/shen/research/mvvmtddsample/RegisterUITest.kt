package miro.shen.research.mvvmtddsample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
    fun wrongLoginIdFormat_should_alert(){

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
}