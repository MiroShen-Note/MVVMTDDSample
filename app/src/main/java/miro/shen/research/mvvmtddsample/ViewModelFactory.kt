package miro.shen.research.mvvmtddsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
