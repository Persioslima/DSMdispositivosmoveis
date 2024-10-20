package com.myapplication // Altere para o nome do seu pacote
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.example.myapplication.R

class NovaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova)

        // Recuperando o dado passado na Intent
        val nome = intent.getStringExtra("nome")

        // Exibindo o nome em um TextView
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Olá, $nome!"
    }
}
