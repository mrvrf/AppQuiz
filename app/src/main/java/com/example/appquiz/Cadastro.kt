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

class Cadastro : AppCompatActivity() {

    private lateinit var btnConfirmarCad: Button
    private lateinit var btnCancelarCad: Button
    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        btnConfirmarCad = findViewById(R.id.btnConfirmarCad)
        btnCancelarCad = findViewById(R.id.btnCancelarCad)
        etUsuario = findViewById(R.id.etUsuarioC)
        etSenha = findViewById(R.id.etPassC)

        btnConfirmarCad.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val senha = etSenha.text.toString()
            if (usuario.isNotEmpty() && senha.isNotEmpty()) {
                registroUser(usuario, senha)
            } else {
                Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnCancelarCad.setOnClickListener {
            finish()
        }
    }

    private fun registroUser(usuario: String, senha: String) {
        auth.createUserWithEmailAndPassword(usuario, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userMap = hashMapOf("username" to usuario, "password" to senha)
                    db.collection("users").document(user!!.uid)
                        .set(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "Usuário registrado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Erro ao registrar o usuário", Toast.LENGTH_SHORT)
                                .show()
                        }
                } else {
                    Toast.makeText(
                        this,
                        "Registro falhou: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}