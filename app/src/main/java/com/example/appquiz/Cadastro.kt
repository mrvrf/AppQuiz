package com.example.appquiz

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cadastro : AppCompatActivity() {

    private lateinit var btnConfirmarCad: Button
    private lateinit var btnCancelarCad: Button
    private lateinit var et

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnConfirmarCad = findViewById(R.id.btnConfirmarLog)
        btnCancelarCad = findViewById(R.id.btnCancelarLog)

        btnConfirmarCad.setOnClickListener {

        }

        btnCancelarCad.setOnClickListener {
            finish()
        }

    }
}