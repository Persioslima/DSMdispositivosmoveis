package com.example.filmes.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.filmes.model.Filme
import com.example.fimes.R

class AdicionarFilmeActivity : AppCompatActivity() {

    private lateinit var editTextTitulo: EditText
    private lateinit var editTextDiretor: EditText
    private lateinit var buttonAdicionar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_filme)

        editTextTitulo = findViewById(R.id.editTextTitulo)
        editTextDiretor = findViewById(R.id.editTextDiretor)
        buttonAdicionar = findViewById(R.id.buttonAdicionar)

        // Inicialmente, desabilitar o botão "Adicionar"
        buttonAdicionar.isEnabled = false

        // Adiciona um TextWatcher para os campos de texto
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Habilita o botão apenas se ambos os campos estiverem preenchidos
                buttonAdicionar.isEnabled = editTextTitulo.text.isNotEmpty() && editTextDiretor.text.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        // Aplica o TextWatcher aos EditTexts
        editTextTitulo.addTextChangedListener(textWatcher)
        editTextDiretor.addTextChangedListener(textWatcher)

        buttonAdicionar.setOnClickListener {
            val titulo = editTextTitulo.text.toString()
            val diretor = editTextDiretor.text.toString()

            // Cria um objeto Filme
            val filme = Filme(titulo, diretor)

            // Retorna o filme para a MainActivity
            val intent = Intent().apply {
                putExtra("filme", filme)
            }
            setResult(Activity.RESULT_OK, intent)
            finish() // Fecha a AdicionarFilmeActivity
        }
    }
}
