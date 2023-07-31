package com.example.android_coroutine.ui.success

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_coroutine.R

class LoginSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)
        val textWelcome = findViewById<TextView>(R.id.text_welcome)
        val text = getString(R.string.text_hello) + " " +
                intent?.getStringExtra(EXTRA_MESSAGE)
        textWelcome.setText(text)
    }

    companion object {
        const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    }
}