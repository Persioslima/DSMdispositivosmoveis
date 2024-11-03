package com.example.filmes.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmes.adapter.FilmeAdapter
import com.example.filmes.model.Filme
import com.example.fimes.R

class ListaFilmesActivity : AppCompatActivity() {

    private lateinit var recyclerViewFilmes: RecyclerView
    private lateinit var filmeAdapter: FilmeAdapter
    private lateinit var filmesList: MutableList<Filme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        // Recupera a lista de filmes passada pelo MainActivity
        filmesList = intent.getParcelableArrayListExtra("listaFilmes") ?: mutableListOf()

        // Configura o RecyclerView para exibir a lista de filmes
        recyclerViewFilmes = findViewById(R.id.recyclerViewFilmes)
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
        filmeAdapter = FilmeAdapter(filmesList)
        recyclerViewFilmes.adapter = filmeAdapter

        // Adiciona o DividerItemDecoration para separar os itens do RecyclerView
        val dividerItemDecoration = DividerItemDecoration(
            recyclerViewFilmes.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerViewFilmes.addItemDecoration(dividerItemDecoration)

        // Configurando o botão "Voltar"
        val buttonVoltar: Button = findViewById(R.id.buttonVoltar)
        buttonVoltar.setOnClickListener {
            finish() // Finaliza a activity atual e volta para a anterior
        }
    }
}
