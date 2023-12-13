package miro.shen.research.mvvmtddsample

import androidx.lifecycle.MutableLiveData

class RegisterViewModel(val repository: IRegisterRepository) : BaseViewModel() {
    val registerFail: MutableLiveData<Event<Unit>> = MutableLiveData()
    val registerSucces: MutableLiveData<Event<String>> = MutableLiveData()
    val alertText: MutableLiveData<Event<String>> = MutableLiveData()

    fun register(loginId: String, passWord: String) {

        if (!loginIdVerify(loginId)) {
            alertText.value = Event("帳號至少要6碼，第1碼為英文")
        } else if (!passwordVerify(passWord)) {
            alertText.value = Event("密碼至少要8碼，第1碼為英文，並包含1碼數字")
        } else {
            repository.register(loginId, passWord, object : IRegisterRepository.RegisterCallback {
                override fun onRegisterResult(registerResponse: RegisterResponse) {
                    if (registerResponse.registerResult) {
                        registerSucces.value = Event(registerResponse.userId!!)
                    } else {
                        registerFail.value = Event(Unit)
                    }
                }

            })
        }

    }

    private fun passwordVerify(passWord: String): Boolean {
        var isPwdOK = false
        if (passWord.length >= 8) {
            if (passWord.uppercase().first() in 'A'..'Z') {
                if (passWord.findAnyOf((0..9).map { it.toString() }) != null) {
                    isPwdOK = true
                }
            }
        }
        return isPwdOK
    }

    private fun loginIdVerify(loginId: String): Boolean {
        var isLoginOK = false

        if (loginId.length >= 6) {
            if (loginId.uppercase().first() in 'A'..'Z') {
                isLoginOK = true
            }
        }
        return isLoginOK
    }

}
