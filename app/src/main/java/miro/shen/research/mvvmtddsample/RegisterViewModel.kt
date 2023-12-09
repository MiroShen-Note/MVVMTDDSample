package miro.shen.research.mvvmtddsample

import androidx.lifecycle.MutableLiveData

class RegisterViewModel {
    val alertText: MutableLiveData<Event<String>> = MutableLiveData()

    fun register(loginId: String, passWord: String) {
        var isLoginOK = false

        if (loginId.length >= 6) {
            if (loginId.uppercase().first() in 'A'..'Z') {
                isLoginOK = true
            }
        }

        var isPwdOK = false
        if (passWord.length >= 8) {
            if (passWord.uppercase().first() in 'A'..'Z') {
                if (passWord.findAnyOf((0..9).map { it.toString() }) != null) {
                    isPwdOK = true
                }
            }
        }

        if (!isLoginOK) {
            alertText.value = Event("帳號至少要6碼，第1碼為英文")
        } else if (!isPwdOK) {
            alertText.value = Event("密碼至少要8碼，第1碼為英文，並包含1碼數字")
        }


    }

}
