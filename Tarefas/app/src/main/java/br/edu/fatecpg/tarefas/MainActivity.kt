package br.edu.fatecpg.tarefas

import Tarefa
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var edtNome: EditText
    private lateinit var edtDescricao: EditText
    private lateinit var btnSalvar: Button
    private var tarefas = mutableListOf<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNome = findViewById(R.id.edt_nome_tarefa)
        edtDescricao = findViewById(R.id.edt_descricao_tarefa)
        btnSalvar = findViewById(R.id.btn_salvar_tarefa)

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val descricao = edtDescricao.text.toString()

            if (nome.isNotEmpty() && descricao.isNotEmpty()) {
                val tarefa = Tarefa(nome, descricao)
                tarefas.add(tarefa)
                edtNome.text.clear()
                edtDescricao.text.clear()

                Toast.makeText(this, "Tarefa Salva", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ListaTarefasActivity::class.java)
                intent.putParcelableArrayListExtra("tarefas", ArrayList(tarefas))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}