package com.example.appquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.checkerframework.common.returnsreceiver.qual.This
import kotlin.math.log

class Login : AppCompatActivity() {

    private lateinit var btnConfirmarLog: Button
    private lateinit var btnCancelarLog: Button
    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        etUsuario = findViewById(R.id.etUsuarioL)
        etSenha = findViewById(R.id.etPassL)

        btnConfirmarLog = findViewById(R.id.btnConfirmarLog)
        btnCancelarLog = findViewById(R.id.btnCancelarLog)

        btnConfirmarLog.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val senha = etSenha.text.toString()
            if (usuario.isNotEmpty() && senha.isNotEmpty()){
                loginUser(usuario, senha)
            } else {
                Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelarLog.setOnClickListener {
            finish()
        }
    }

    private fun loginUser(usuario: String, senha: String) {
        auth.signInWithEmailAndPassword(usuario, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ListaQuiz::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Login falhou: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}