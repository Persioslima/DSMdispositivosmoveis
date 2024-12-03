package br.edu.fatecpg.cadastrousuario.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.cadastrousuario.R
import br.edu.fatecpg.cadastrousuario.adapter.UsuariosAdapter

class UsuariosCadastradosActivity : AppCompatActivity() {

    private lateinit var recyclerViewUsuarios: RecyclerView
    private lateinit var buttonSair: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios_cadastrados)

        recyclerViewUsuarios = findViewById(R.id.recyclerViewUsuarios)
        buttonSair = findViewById(R.id.buttonSair)

        // Configura o RecyclerView
        recyclerViewUsuarios.layoutManager = LinearLayoutManager(this)

        // Exemplo de dados de usuários (para testes)
        val usuarios = listOf(
            "Usuario 1 - usuario1@exemplo.com",
            "Usuario 2 - usuario2@exemplo.com",
            "Usuario 3 - usuario3@exemplo.com"
        )

        // Adicionando os usuários ao RecyclerView
        val adapter = UsuariosAdapter(usuarios)
        recyclerViewUsuarios.adapter = adapter

        // Ação do botão "Sair"
        buttonSair.setOnClickListener {
            // Ação de logout ou voltar para a tela de login
            Toast.makeText(this, "Você saiu.", Toast.LENGTH_SHORT).show()
            finish()  // Finaliza a activity atual e retorna para a anterior
        }
    }
}
