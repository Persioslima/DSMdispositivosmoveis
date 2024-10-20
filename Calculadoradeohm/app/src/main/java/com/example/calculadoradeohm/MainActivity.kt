package com.example.calculadoradeohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editTextTensao: EditText
    private lateinit var editTextCorrente: EditText
    private lateinit var editTextResistencia: EditText
    private lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTensao = findViewById(R.id.editTextV)
        editTextCorrente = findViewById(R.id.editTextI)
        editTextResistencia = findViewById(R.id.editTextR)
        textViewResultado = findViewById(R.id.textViewResult)

        val botaoCalcular: Button = findViewById(R.id.buttonCalculate)
        botaoCalcular.setOnClickListener { calcular() }

        val botaoLimpar: Button = findViewById(R.id.buttonClear)
        botaoLimpar.setOnClickListener { limparCampos() }
    }

    private fun calcular() {
        val tensao = editTextTensao.text.toString().toDoubleOrNull()
        val corrente = editTextCorrente.text.toString().toDoubleOrNull()
        val resistencia = editTextResistencia.text.toString().toDoubleOrNull()

        when {
            tensao != null && corrente != null -> {
                val resultadoR = tensao / corrente
                textViewResultado.text = "Resultado:  Resistência é = %.2f Ohm".format(resultadoR)
            }
            tensao != null && resistencia != null -> {
                val resultadoI = tensao / resistencia
                textViewResultado.text = "Resultado:  Amperagem é = %.2f A".format(resultadoI)
            }
            corrente != null && resistencia != null -> {
                val resultadoV = corrente * resistencia
                textViewResultado.text = "Resultado: Voltagem é = %.2f V".format(resultadoV)
            }
            else -> {
                Toast.makeText(this, "Por favor, insira dois valores válidos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limparCampos() {
        editTextTensao.text.clear()
        editTextCorrente.text.clear()
        editTextResistencia.text.clear()
        textViewResultado.text = "Resultado: "
    }
}
