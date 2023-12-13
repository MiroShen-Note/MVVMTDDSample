package miro.shen.research.mvvmtddsample.api

import miro.shen.research.mvvmtddsample.RegisterRequest
import miro.shen.research.mvvmtddsample.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberApi {
    @POST(ApiConfig.registerUrl)
    abstract fun register(@Body request: RegisterRequest): Call<RegisterResponse>

}
