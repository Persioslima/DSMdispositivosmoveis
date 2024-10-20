package com.example.cadastroaluno

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ActivityExibicao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibicao)

        // Referências aos TextViews e botão
        val textViewStudentName = findViewById<TextView>(R.id.textViewStudentName)
        val textViewStudentId = findViewById<TextView>(R.id.textViewStudentId)
        val buttonGenerateId = findViewById<Button>(R.id.buttonGenerateId)

        // Recebendo o nome do aluno da MainActivity
        val studentName = intent.getStringExtra("student_name")
        textViewStudentName.text = "Nome do aluno: $studentName"

        // Ação ao clicar no botão para gerar o número de matrícula
        buttonGenerateId.setOnClickListener {
            // Gerar um número de matrícula aleatório
            val studentId = Random.nextInt(10000, 99999)
            textViewStudentId.text = "Matrícula: $studentId"
        }
    }
}
