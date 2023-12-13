package miro.shen.research.mvvmtddsample

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(InstantExecutorExtension::class, MockKExtension::class)
class RegisterViewModelTest {

    @MockK
    lateinit var repository: IRegisterRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @ParameterizedTest
    @CsvSource(
        "1111, A11111111, 帳號至少要6碼，第1碼為英文",
        "A11111111, 111, 密碼至少要8碼，第1碼為英文，並包含1碼數字"
    )
    fun registerFormatTest(
        loginId: String,
        password: String,
        expectedMessage: String
    ) {
        val viewModel = RegisterViewModel(repository)
        viewModel.register(loginId, password)

        Assertions.assertEquals(
            expectedMessage,
            viewModel.alertText.value?.getContentIfNotHandled()
        )
    }

    @Test
    fun register_success() {
        val loginId = "A11111111"
        val password = "A2222222"
        val userId = "AnyId"

        val slot = slot<IRegisterRepository.RegisterCallback>()

        every { repository.register(eq(loginId), eq(password), capture(slot)) }
            .answers {
                slot.captured.onRegisterResult(
                    RegisterResponse(true, userId)
                )
            }

        val viewModel = RegisterViewModel(repository)
        viewModel.register(loginId, password)

        Assertions.assertEquals(userId, viewModel.registerSucces.value?.getContentIfNotHandled())

    }

    @Test
    fun registerFail() {
        val loginId = "A11111111"
        val password = "A2222222"

        val slot = slot<IRegisterRepository.RegisterCallback>()

        every { repository.register(eq(loginId), eq(password), capture(slot)) }
            .answers {
                slot.captured.onRegisterResult(
                    RegisterResponse(false, null)
                )
            }

        val viewModel = RegisterViewModel(repository)
        viewModel.register(loginId, password)

        Assertions.assertEquals(Unit, viewModel.registerFail.value?.getContentIfNotHandled())

    }
}