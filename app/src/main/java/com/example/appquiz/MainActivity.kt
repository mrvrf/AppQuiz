package com.example.appquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var btnRegistro: Button
    private lateinit var btnLogin: Button
    private lateinit var btnLoginN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRegistro = findViewById(R.id.btnRegistro)
        btnLogin = findViewById(R.id.btnLogin)
        btnLoginN = findViewById(R.id.btnLoginN)

        btnRegistro.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        btnLoginN.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmação")
            builder.setMessage("Jogar sem login não vai salvar pontos para o ranking. Deseja continuar?")
            builder.setPositiveButton("Confirmar"){ dialog, which ->
                val intent = Intent(this, ListaQuiz::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("Cancelar") { dialog , which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}