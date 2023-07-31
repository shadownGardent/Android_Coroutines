package com.example.android_coroutine.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.example.android_coroutine.R
import com.example.android_coroutine.repository.LoginRepository
import com.example.android_coroutine.repository.LoginResponseParser
import com.example.android_coroutine.ui.success.LoginSuccessActivity
import com.example.android_coroutine.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var contaierLayout: View
    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText
    private lateinit var viewModel: LoginViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        viewModel = LoginViewModel(LoginRepository(LoginResponseParser()))
        viewModel.user.observe(this) { user ->
            progressBar.visibility = View.GONE
            if (user != null) {
                val intent = Intent(this, LoginSuccessActivity::class.java)
                intent.putExtra(LoginSuccessActivity.EXTRA_MESSAGE, user.displayName)
                startActivity(intent)
            } else {
                Snackbar.make(
                    contaierLayout,
                    getString(R.string.text_login_falied),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initViews() {
        contaierLayout = findViewById(R.id.container_layout)
        editUsername = findViewById(R.id.edit_username)
        editPassword = findViewById(R.id.edit_pasword)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        progressBar = findViewById(R.id.progress_bar)
        btnLogin.setOnClickListener { doLogin() }
    }

    private fun doLogin() {
        progressBar.visibility = View.VISIBLE
        val username = editUsername.text.toString()
        val password = editPassword.text.toString()
        viewModel.login(username, password)
    }
}