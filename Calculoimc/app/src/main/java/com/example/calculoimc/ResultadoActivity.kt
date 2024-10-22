package com.example.calculoimc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val resultadoTextView: TextView = findViewById(R.id.textViewResultado)

        // Recebe o IMC e a classificação passada pela Intent
        val imc = intent.getStringExtra("IMC") ?: "0.0"
        val classificacao = intent.getStringExtra("CLASSIFICACAO") ?: "Indeterminado"

        resultadoTextView.text = "Seu IMC é: $imc\nClassificação: $classificacao"
    }
}
