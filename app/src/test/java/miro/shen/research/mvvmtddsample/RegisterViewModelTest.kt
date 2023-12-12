package miro.shen.research.mvvmtddsample

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
@ExtendWith(InstantExecutorExtension::class)
class RegisterViewModelTest {

    @ParameterizedTest
    @CsvSource("1111, A11111111, 帳號至少要6碼，第1碼為英文", "A11111111, 111, 密碼至少要8碼，第1碼為英文，並包含1碼數字")
    fun registerFormatTest(
        loginId: String,
        password: String,
        expectedMessage: String
    ) {
        val viewModel = RegisterViewModel()
        viewModel.register(loginId, password)

        Assertions.assertEquals(
            expectedMessage,
            viewModel.alertText.value?.getContentIfNotHandled()
        )
    }
}