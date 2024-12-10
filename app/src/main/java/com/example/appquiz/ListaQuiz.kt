package com.example.appquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaQuiz : AppCompatActivity() {

    private lateinit var btnAnimais: Button
    private lateinit var btnTecnologia: Button
    private lateinit var btnComida: Button
    private lateinit var btnMatematica: Button
    private lateinit var btnSocial: Button
    private lateinit var btnRanking: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnAnimais = findViewById(R.id.btnAnimais)
        btnTecnologia = findViewById(R.id.btnTecnologia)
        btnComida = findViewById(R.id.btnComida)
        btnMatematica = findViewById(R.id.btnMatematica)
        btnSocial = findViewById(R.id.btnSocial)
        btnRanking = findViewById(R.id.btnRanking)

        btnAnimais.setOnClickListener {
            startQuizActivity(1)
        }
        btnTecnologia.setOnClickListener {
            startQuizActivity(2)
        }
        btnComida.setOnClickListener {
            startQuizActivity(3)
        }
        btnMatematica.setOnClickListener {
            startQuizActivity(4)
        }
        btnSocial.setOnClickListener {
            startQuizActivity(5)
        }

        btnRanking.setOnClickListener {
            val intent = Intent(this, Ranking::class.java)
            startActivity(intent)
        }

    }

    private fun startQuizActivity(quizNumber: Int){
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("QUIZ_NUMBER", quizNumber)
        startActivity(intent)
    }
}