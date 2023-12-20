package miro.shen.research.mvvmtddsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import miro.shen.research.mvvmtddsample.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            send.setOnClickListener {
                val loginId = loginId.text.toString()
                val password = password.text.toString()

                viewModel.register(loginId, password)
            }
        }

        viewModel.alertText.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(it)
                    .setTitle("錯誤")

                builder.show()
            }
        })

        viewModel.registerFail.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                event.getContentIfNotHandled()?.let {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("註冊失敗")
                        .setTitle("錯誤")

                    builder.show()
                }
            }
        })

        viewModel.registerSucces.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val loginId = it
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ID", loginId)

                startActivity(intent)
            }
        })
    }

}
