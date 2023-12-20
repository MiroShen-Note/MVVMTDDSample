package miro.shen.research.mvvmtddsample

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource
import miro.shen.research.mvvmtddsample.api.MemberApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//object Idling {
//    val idlingResource = CountingIdlingResource("API")
//}

class RegisterRepository(val api: MemberApi) : IRegisterRepository {
    override fun register(
        loginId: String,
        password: String,
        listener: IRegisterRepository.RegisterCallback
    ) {
//        Idling.idlingResource.increment()
        val request = RegisterRequest(loginId, password)

        api.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                //success
                if (response.isSuccessful) {
                    //200
                    listener.onRegisterResult(response.body()!!)
                }

//                Idling.idlingResource.decrement()
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                //TODO
            }

        })
    }

}
