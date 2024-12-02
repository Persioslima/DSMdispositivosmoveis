package br.edu.fatecpg.tarefas

import Tarefa
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaTarefasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnVoltar: Button
    private lateinit var tarefas: List<Tarefa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tarefas)

        tarefas = intent.getParcelableArrayListExtra("tarefas") ?: emptyList()

        recyclerView = findViewById(R.id.recycler_view_tarefas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TarefaAdapter(tarefas)

        btnVoltar = findViewById(R.id.btn_voltar)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}