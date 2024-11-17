package br.edu.fatecpg.clinica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var cadastroLink: TextView
    private val db = FirebaseFirestore.getInstance() // Firebase Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa o Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Referências para os campos de entrada, botão de login e o link de cadastro
        emailEditText = findViewById(R.id.editTextEmail)
        senhaEditText = findViewById(R.id.editTextSenha)
        loginButton = findViewById(R.id.buttonLogin)
        cadastroLink = findViewById(R.id.textViewCadastrar)

        // Configuração do listener do botão de login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val senha = senhaEditText.text.toString()

            // Verifique se o email e a senha não estão vazios
            if (email.isNotEmpty() && senha.isNotEmpty()) {
                loginUsuario(email, senha)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar o clique no link para abrir a tela de cadastro
        cadastroLink.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    // Método de login com o Firebase Auth
    private fun loginUsuario(email: String, senha: String) {
        Log.d("LoginActivity", "Tentando login...")

        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("LoginActivity", "Login bem-sucedido!")

                    // Obter o UID do usuário
                    val userId = user?.uid
                    if (userId != null) {
                        // Exibe o UID no Logcat e Toast
                        Log.d("LoginActivity", "UID do usuário: $userId")
                        Toast.makeText(this, "Usuário logado: $userId", Toast.LENGTH_SHORT).show()

                        // Verifique o tipo do usuário
                        getTipoUsuario(userId) { tipoUsuario ->
                            // Redirecionamento com base no tipo do usuário
                            val intent = when (tipoUsuario.lowercase()) {
                                "paciente" -> Intent(this, PacienteActivity::class.java)
                                "médico" -> Intent(this, MedicoActivity::class.java)
                                else -> {
                                    Toast.makeText(this, "Tipo de usuário inválido: $tipoUsuario", Toast.LENGTH_SHORT).show()
                                    Intent(this, LoginActivity::class.java)
                                }
                            }
                            startActivity(intent)
                            finish() // Impede o usuário de voltar para a tela de login
                        }
                    } else {
                        Log.d("LoginActivity", "Erro: Usuário não encontrado")
                    }

                } else {
                    Log.d("LoginActivity", "Falha no login: ${task.exception?.message}")
                    Toast.makeText(this, "Erro ao fazer login: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Método para obter o tipo de usuário
    private fun getTipoUsuario(userId: String, callback: (String) -> Unit) {
        val usuarioRef = db.collection("usuarios").document(userId)

        usuarioRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val tipoUsuario = document.getString("tipoUsuario") ?: "Desconhecido" // Valor padrão atualizado
                    Log.d("LoginActivity", "Tipo de usuário recuperado: $tipoUsuario") // Log para depuração
                    callback(tipoUsuario) // Passa o tipo de usuário
                } else {
                    Log.d("LoginActivity", "Documento não encontrado. Assumindo tipo padrão: Paciente")
                    callback("Paciente") // Tipo padrão
                }
            }
            .addOnFailureListener { e ->
                Log.d("LoginActivity", "Erro ao buscar tipo de usuário: ${e.message}")
                callback("Paciente") // Tipo padrão em caso de erro
            }
    }
}
