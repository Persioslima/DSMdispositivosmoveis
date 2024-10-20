package com.example.cadastroaluno

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências aos campos de entrada e botão
        val editTextStudentName = findViewById<EditText>(R.id.editTextStudentName)
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        // Ação ao clicar no botão
        buttonNext.setOnClickListener {
            // Obtendo o nome do aluno
            val studentName = editTextStudentName.text.toString()

            // Criando um Intent para iniciar a StudentDetailsActivity e passar o nome do aluno
            val intent = Intent(this@MainActivity, ActivityExibicao::class.java)
            intent.putExtra("student_name", studentName)

            // Iniciando a segunda atividade
            startActivity(intent)
        }
    }
}
