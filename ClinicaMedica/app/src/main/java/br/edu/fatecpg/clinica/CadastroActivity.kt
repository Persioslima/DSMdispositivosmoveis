package br.edu.fatecpg.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var nomeEditText: EditText
    private lateinit var tipoUsuarioSpinner: Spinner
    private lateinit var cadastrarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializa o Firebase Auth e Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Referências para os campos de entrada e botão
        emailEditText = findViewById(R.id.editTextEmail)
        senhaEditText = findViewById(R.id.editTextSenha)
        nomeEditText = findViewById(R.id.editTextNome)
        tipoUsuarioSpinner =
            findViewById(R.id.spinnerTipoUsuario) // Spinner para escolher o tipo de usuário
        cadastrarButton = findViewById(R.id.buttonCadastrar)

        // Criar o ArrayAdapter para o Spinner
        val tipoUsuarioAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.tipo_usuario_array, // Array definido em strings.xml
            android.R.layout.simple_spinner_item // Layout padrão do Spinner
        )

        tipoUsuarioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipoUsuarioSpinner.adapter = tipoUsuarioAdapter

        // Configuração do listener do botão de cadastro
        cadastrarButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val senha = senhaEditText.text.toString()
            val nome = nomeEditText.text.toString()
            val tipoUsuario =
                tipoUsuarioSpinner.selectedItem.toString() // Obtém o tipo selecionado no Spinner

            // Verifique se todos os campos estão preenchidos
            if (email.isNotEmpty() && senha.isNotEmpty() && nome.isNotEmpty()) {
                cadastrarUsuario(email, senha, nome, tipoUsuario)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // Método para cadastrar o usuário com Firebase Auth e adicionar dados no Firestore
    private fun cadastrarUsuario(email: String, senha: String, nome: String, tipoUsuario: String) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    // Cria um mapa de dados do usuário
                    val userMap = hashMapOf(
                        "email" to email,
                        "nome" to nome,
                        "tipoUsuario" to tipoUsuario, // Adiciona o tipo do usuário
                        "dataCadastro" to System.currentTimeMillis()
                    )

                    // Adiciona os dados na coleção 'usuarios' no Firestore
                    user?.uid?.let { userId ->
                        db.collection("usuarios").document(userId)
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Cadastro realizado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                redirecionarParaTela(tipoUsuario)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Erro ao cadastrar, tente novamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                } else {
                    Toast.makeText(this, "Erro ao cadastrar, tente novamente", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    // Método para redirecionar para a tela de login após o cadastro
    private fun redirecionarParaTela(tipoUsuario: String) {
        // Redireciona sempre para a LoginActivity, independentemente do tipo de usuário
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Impede o usuário de voltar para a tela de cadastro
    }
}