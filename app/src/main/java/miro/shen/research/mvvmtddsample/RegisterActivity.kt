package miro.shen.research.mvvmtddsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import miro.shen.research.mvvmtddsample.databinding.ActivityRegisterBinding

class RegisterActivity :AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}
