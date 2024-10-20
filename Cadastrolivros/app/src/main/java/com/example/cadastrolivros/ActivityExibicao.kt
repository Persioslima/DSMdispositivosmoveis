package com.example.bookapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastrolivros.R

class ActivityExibicao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibicao)

        // Obtendo os dados passados pela Intent
        val title = intent.getStringExtra("book_title")
        val author = intent.getStringExtra("book_author")

        // Exibindo os dados nos TextViews
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        val textViewAuthor = findViewById<TextView>(R.id.textViewAuthor)

        textViewTitle.text = "Título: $title"
        textViewAuthor.text = "Autor: $author"
    }
}
