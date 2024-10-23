package com.example.consultacep.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.consultacep.R
import com.exemplo.consultacep.model.Endereco

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val endereco = intent.getSerializableExtra("endereco") as? Endereco
        val textViewResultado: TextView = findViewById(R.id.textViewResultado)

        endereco?.let {
            textViewResultado.text = "Logradouro: ${it.logradouro}\nBairro: ${it.bairro}\nCidade: ${it.localidade}\nUF: ${it.uf}"
        }
    }
}
