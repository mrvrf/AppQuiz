package com.example.appquiz

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    private lateinit var btnConfirmarLog: Button
    private lateinit var btnCancelarLog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnConfirmarLog = findViewById(R.id.btnConfirmarLog)
        btnCancelarLog = findViewById(R.id.btnCancelarLog)

        btnConfirmarLog.setOnClickListener {

        }

        btnCancelarLog.setOnClickListener {
            finish()
        }
    }
}