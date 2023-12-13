package miro.shen.research.mvvmtddsample

import miro.shen.research.mvvmtddsample.api.MemberApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepository(val api: MemberApi) : IRegisterRepository {
    override fun register(
        loginId: String,
        password: String,
        listener: IRegisterRepository.RegisterCallback
    ) {
        val request = RegisterRequest(loginId, password)

        api.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                println("response $response")
                if(response.isSuccessful){
                    //200
                    listener.onRegisterResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
