package miro.shen.research.mvvmtddsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import miro.shen.research.mvvmtddsample.api.NetworkService

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            val networkService = NetworkService()
            return RegisterViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
