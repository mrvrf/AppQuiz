package com.example.appquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var tvPerguntaQuiz: TextView
    private lateinit var etRespostaQuiz: EditText
    private lateinit var btnEnviarResposta: Button
    private lateinit var btnVoltar: Button

    private lateinit var perguntas: List<Pergunta>
    private var atualPergunta = 0
    private var pontos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quizNumber = intent.getIntExtra("QUIZ_NUMBER", 0)
        perguntas = when (quizNumber){
            1 -> QuizPerguntas.perguntasAnimais
            2 -> QuizPerguntas.perguntasTecnologia
            3 -> QuizPerguntas.perguntasComida
            4 -> QuizPerguntas.perguntasMatematica
            5 -> QuizPerguntas.perguntasSocial
            else -> listOf()
        }

        tvPerguntaQuiz = findViewById(R.id.tvPerguntaQuiz)
        etRespostaQuiz = findViewById(R.id.etRespostaQuiz)
        btnEnviarResposta = findViewById(R.id.btnEnviarResposta)
        btnVoltar = findViewById(R.id.btnVoltar)

        proximaPergunta()

        btnEnviarResposta.setOnClickListener {
            if (atualPergunta < perguntas.size) {
                verificarResposta()
            }
        }

        btnVoltar.setOnClickListener {
            val intent = Intent(this, ListaQuiz::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun proximaPergunta(){
        if (atualPergunta < perguntas.size) {
            val pergunta = perguntas[atualPergunta]
            tvPerguntaQuiz.text = pergunta.text
            etRespostaQuiz.text.clear()
        } else {
            tvPerguntaQuiz.text = "Você terminou o quiz! Acertou: $pontos / 10"
            etRespostaQuiz.isEnabled = false
            btnEnviarResposta.isEnabled = false
            btnVoltar.visibility = Button.VISIBLE
        }
    }

    private fun verificarResposta(){
        val pergunta = perguntas[atualPergunta]
        val respostaUsuario = etRespostaQuiz.text.toString().trim()
        if (respostaUsuario.equals(pergunta.corretaR, ignoreCase = true)){
            pontos++
        }
        atualPergunta++
        proximaPergunta()
    }
}

