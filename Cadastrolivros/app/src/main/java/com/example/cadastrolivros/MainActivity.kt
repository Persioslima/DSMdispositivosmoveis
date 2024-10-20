package com.example.cadastrolivros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.ActivityExibicao

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências aos campos de entrada e ao botão
        val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        val editTextAuthor = findViewById<EditText>(R.id.editTextAuthor)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        // Ação ao clicar no botão
        buttonRegister.setOnClickListener {
            // Obtendo os valores inseridos
            val title = editTextTitle.text.toString()
            val author = editTextAuthor.text.toString()

            // Criando um Intent para iniciar a BookDetailsActivity e passar os dados
            val intent = Intent(this@MainActivity, ActivityExibicao::class.java)
            intent.putExtra("book_title", title)
            intent.putExtra("book_author", author)

            // Iniciando a segunda atividade
            startActivity(intent)
        }
    }
}