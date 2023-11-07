package miro.shen.research.mvvmtddsample

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
        
    }
}