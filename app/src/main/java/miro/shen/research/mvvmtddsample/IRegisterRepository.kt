package miro.shen.research.mvvmtddsample

interface IRegisterRepository {
    fun register(loginId: String, password: String, listener: RegisterCallback)

    interface RegisterCallback {
        abstract fun onRegisterResult(registerResponse: RegisterResponse)

    }

}
