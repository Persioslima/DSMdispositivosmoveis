package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultadoTextView: TextView
    private var entradaAtual = ""
    private var operador: String? = null
    private var primeiroNumero: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoTextView = findViewById(R.id.resultTextView)

        configurarBotoes()
    }

    private fun configurarBotoes() {
        val botoes = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEqual, R.id.buttonClear
        )

        for (id in botoes) {
            findViewById<Button>(id).setOnClickListener { onBotaoClicado(it) }
        }
    }

    private fun onBotaoClicado(view: View) {
        when (view.id) {
            R.id.buttonClear -> limpar()
            R.id.buttonEqual -> calcular()
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide -> {
                operador = (view as Button).text.toString()
                primeiroNumero = entradaAtual.toDoubleOrNull()
                entradaAtual = ""
            }
            else -> {
                val numero = (view as Button).text.toString()
                entradaAtual += numero
            }
        }
        atualizarResultado()
    }

    private fun limpar() {
        entradaAtual = ""
        operador = null
        primeiroNumero = null
        atualizarResultado()
    }

    private fun calcular() {
        val segundoNumero = entradaAtual.toDoubleOrNull()
        if (primeiroNumero != null && segundoNumero != null && operador != null) {
            val resultado = when (operador) {
                "+" -> primeiroNumero!! + segundoNumero
                "-" -> primeiroNumero!! - segundoNumero
                "*" -> primeiroNumero!! * segundoNumero
                "/" -> if (segundoNumero != 0.0) primeiroNumero!! / segundoNumero else "Erro: Divisão por zero"
                else -> 0.0
            }
            entradaAtual = resultado.toString()
            operador = null
            primeiroNumero = null
        }
    }

    private fun atualizarResultado() {
        resultadoTextView.text = entradaAtual
    }
}
