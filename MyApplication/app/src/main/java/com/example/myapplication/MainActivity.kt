package com.myapplication // Altere para o nome do seu pacote

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtendo referência ao botão
        val button = findViewById<Button>(R.id.button)

        // Configurando o listener para o clique do botão
        button.setOnClickListener {
            // Criando um Intent para iniciar a NovaActivity
            val intent = Intent(this@MainActivity, NovaActivity::class.java) // Use this@MainActivity
            intent.putExtra("nome", "João") // Adiciona o nome ao Intent

            // Iniciando a NovaActivity
            startActivity(intent)
        }
    }
}
