package br.edu.fatecpg.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.clinica.databinding.ActivityPacienteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPacienteBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar botão para agendar consulta
        binding.btnAgendarConsulta.setOnClickListener {
            agendarConsulta()
        }

        // Botão para sair e retornar à tela de login
        binding.btnSair.setOnClickListener {
            auth.signOut()
            redirecionarParaLogin()
        }
    }

    private fun agendarConsulta() {
        val nomeConsulta = binding.etNome.text.toString()
        val tipoConsulta = binding.etTipoConsulta.text.toString()
        val dataConsulta = binding.etDataConsulta.text.toString()
        val horaConsulta = binding.etHoraConsulta.text.toString()

        if (tipoConsulta.isBlank() || dataConsulta.isBlank() || horaConsulta.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Pegar o ID do usuário atual
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val consultaData = hashMapOf(
                "nome do paciente" to nomeConsulta,
                "tipo_consulta" to tipoConsulta,
                "data_tipo_consulta" to dataConsulta,
                "hora_tipo_consulta" to horaConsulta
            )

            // Adicionar uma subcoleção "Consultas" no documento do usuário na coleção "usuarios"
            db.collection("usuarios").document(userId)
                .collection("consultas")
                .add(consultaData) // Usar 'add' para criar um novo documento na subcoleção
                .addOnSuccessListener {
                    Toast.makeText(this, "Consulta agendada com sucesso!", Toast.LENGTH_SHORT).show()

                    // Limpar os campos após o agendamento
                    binding.etNome.text.clear()
                    binding.etTipoConsulta.text.clear()
                    binding.etDataConsulta.text.clear()
                    binding.etHoraConsulta.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Erro ao agendar consulta: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para redirecionar para a tela de login
    private fun redirecionarParaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Fecha a Activity atual
    }
}
