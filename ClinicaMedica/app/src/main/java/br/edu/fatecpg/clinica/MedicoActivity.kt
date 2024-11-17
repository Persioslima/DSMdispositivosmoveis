package br.edu.fatecpg.clinica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MedicoActivity : AppCompatActivity() {

    private lateinit var consultasList: MutableList<Consulta> // Lista para armazenar as consultas
    private lateinit var consultaAdapter: ConsultaAdapter // Adapter para o RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medico)

        // Inicializando a lista
        consultasList = mutableListOf()

        // Inicializando o RecyclerView e o Adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewConsultas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Recuperando os dados do Firestore
        val db = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // Ref para a subcoleção "consultas" dos usuários com tipo "Paciente"
        val consultasRef = db.collection("usuarios")
            .whereEqualTo("tipoUsuario", "Paciente") // Buscando usuários do tipo "Paciente"
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Iterando sobre os usuários do tipo "Paciente"
                    for (userDocument in querySnapshot.documents) {
                        // Ref para a subcoleção "consultas" do usuário
                        val consultasSubRef = userDocument.reference.collection("consultas")
                        consultasSubRef.get()
                            .addOnSuccessListener { consultas ->
                                // Iterando sobre as consultas de cada paciente
                                for (consulta in consultas) {
                                    // Exibir todos os campos da consulta para verificar como estão estruturados
                                    Log.d("MedicoActivity", "Consulta data: ${consulta.data}")

                                    // Extraindo os dados, agora com os nomes de campo corretos
                                    val nomePaciente = consulta.getString("nome do paciente") ?: "Desconhecido"
                                    val tipoConsulta = consulta.getString("tipo_consulta") ?: "Tipo não informado"
                                    val dataConsulta = consulta.getString("data_tipo_consulta") ?: "Data não informada"
                                    val horaConsulta = consulta.getString("hora_tipo_consulta") ?: "Hora não informada"

                                    // Verificando se os dados foram extraídos corretamente
                                    Log.d("MedicoActivity", "Consulta encontrada: $nomePaciente, $tipoConsulta, $dataConsulta, $horaConsulta")

                                    // Criando a consulta com os dados recuperados
                                    val consultaObj = Consulta(
                                        id = consulta.id, // ID do documento
                                        nomePaciente = nomePaciente,
                                        tipoConsulta = tipoConsulta,
                                        dataConsulta = dataConsulta,
                                        horaConsulta = horaConsulta
                                    )

                                    consultasList.add(consultaObj) // Adiciona a consulta na lista
                                }

                                // Atualizando o RecyclerView com as consultas
                                consultaAdapter = ConsultaAdapter(consultasList)
                                recyclerView.adapter = consultaAdapter
                            }
                            .addOnFailureListener { exception ->
                                Log.e("MedicoActivity", "Erro ao buscar consultas do paciente", exception)
                            }
                    }
                } else {
                    Log.d("MedicoActivity", "Nenhum usuário do tipo 'Paciente' encontrado")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("MedicoActivity", "Erro ao buscar usuários do tipo 'Paciente'", exception)
            }

        // Configuração do botão "Sair" para fazer o logout
        val btnSair: Button = findViewById(R.id.buttonSair)
        btnSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
