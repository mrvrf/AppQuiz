package com.example.appquiz

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class Ranking : AppCompatActivity() {

    private lateinit var tvRanking: TextView
    private lateinit var lvRanking: ListView
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var btnVoltarR: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvRanking = findViewById(R.id.tvRanking)
        lvRanking = findViewById(R.id.lvRanking)
        btnVoltarR = findViewById(R.id.btnVoltarRank)
        db = FirebaseFirestore.getInstance()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
        lvRanking.adapter = adapter

        btnVoltarR.setOnClickListener {
            val intent = Intent(this, ListaQuiz::class.java)
            startActivity(intent)
            finish()
        }

        loadRanking()
    }

    private fun loadRanking(){
        db.collection("users")
            .orderBy("pontos", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val rankingList = ArrayList<String>()
                for (document in result) {
                    val username = document.getString("username") ?: "Unknown"
                    val points = document.getLong("pontos") ?: 0
                    rankingList.add("$username: $points pontos")
                }
                adapter.clear()
                adapter.addAll(rankingList)
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {

            }
    }
}