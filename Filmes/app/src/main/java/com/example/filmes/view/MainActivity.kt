package com.example.filmes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmes.adapter.FilmeAdapter
import com.example.filmes.model.Filme
import com.example.fimes.R


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewFilmes: RecyclerView
    private lateinit var filmeAdapter: FilmeAdapter
    private lateinit var filmesList: MutableList<Filme>
    private lateinit var buttonAdicionarFilme: Button
    private lateinit var editTextTitulo: EditText
    private lateinit var editTextDiretor: EditText
    private lateinit var buttonExibirFilmes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando a lista de filmes
        filmesList = mutableListOf()

        // Configurando o RecyclerView
        recyclerViewFilmes = findViewById(R.id.recyclerViewFilmes)
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
        filmeAdapter = FilmeAdapter(filmesList)
        recyclerViewFilmes.adapter = filmeAdapter

        // Inicializando os campos de entrada
        editTextTitulo = findViewById(R.id.editTextTitulo)
        editTextDiretor = findViewById(R.id.editTextDiretor)

        // Inicializando o botão para adicionar filmes
        buttonAdicionarFilme = findViewById(R.id.buttonAdicionarFilme)
        buttonAdicionarFilme.setOnClickListener {
            val titulo = editTextTitulo.text.toString().trim()
            val diretor = editTextDiretor.text.toString().trim()

            if (titulo.isNotEmpty() && diretor.isNotEmpty()) {
                // Cria um objeto Filme e adiciona à lista
                val filme = Filme(titulo, diretor)
                filmesList.add(filme)

                // Exibe uma mensagem de sucesso
                Toast.makeText(this, "Filme adicionado com sucesso!", Toast.LENGTH_SHORT).show()

                // Limpa os campos de entrada
                editTextTitulo.text.clear()
                editTextDiretor.text.clear()
            } else {
                // Exibir um alerta ou mensagem para preencher os campos
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurando o botão "Exibir Filmes"
        buttonExibirFilmes = findViewById(R.id.buttonExibirFilmes)
        buttonExibirFilmes.setOnClickListener {
            // Verifica se há filmes na lista
            if (filmesList.isNotEmpty()) {
                val intent = Intent(this, ListaFilmesActivity::class.java)
                intent.putParcelableArrayListExtra("listaFilmes", ArrayList(filmesList))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Nenhum filme cadastrado para exibir", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Limpa a lista de filmes quando retorna para a MainActivity
        //filmeAdapter.clearFilmes()
    }
}
