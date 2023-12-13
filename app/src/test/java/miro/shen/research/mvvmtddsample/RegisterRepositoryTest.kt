package miro.shen.research.mvvmtddsample

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import miro.shen.research.mvvmtddsample.api.MemberApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepositoryTest {

    @MockK(relaxed = true)
    private lateinit var api: MemberApi

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun registerTest() {

        val loginId = "A11111111"
        val password = "A22222222"
        val request = RegisterRequest(loginId, password)
        val registerResponse = RegisterResponse(true, "AnyId")

        val response: Response<RegisterResponse> = Response.success(registerResponse)
        val mockCall = mockk<Call<RegisterResponse>>(relaxed = true)

        val slot = slot<Callback<RegisterResponse>>()

        every { api.register(any()).enqueue(capture(slot)) }
            .answers {
                slot.captured.onResponse(
                    mockCall, response
                )
            }

        val callback = mockk<IRegisterRepository.RegisterCallback>(relaxed = true)

        val registerRespository = RegisterRepository(api)
        registerRespository.register(loginId, password, callback)

        verify { api.register(eq(request)) }
        verify { callback.onRegisterResult(registerResponse) }
    }
}