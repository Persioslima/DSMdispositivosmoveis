package br.edu.fatecpg

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonCreateUser: Button
    private lateinit var buttonResetPassword: Button
    private lateinit var buttonDeleteAccount: Button
    private lateinit var textViewStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Inicializando os EditTexts e Buttons
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonCreateUser = findViewById(R.id.buttonCreateUser)
        buttonResetPassword = findViewById(R.id.buttonResetPassword)
        buttonDeleteAccount = findViewById(R.id.buttonDeleteAccount)
        textViewStatus = findViewById(R.id.textViewStatus) // Inicializando o TextView

        // Login
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        textViewStatus.text = "Login bem-sucedido" // Atualiza a mensagem
                        Log.d("TAG", "Login bem-sucedido")
                    } else {
                        textViewStatus.text = "Erro ao fazer login: " // Atualiza a mensagem de erro
                        Log.w("TAG", "Erro ao fazer login", task.exception)
                    }
                }
        }

        // Criar Usuário
        buttonCreateUser.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        textViewStatus.text = "Usuário criado com sucesso" // Atualiza a mensagem
                        Log.d("TAG", "Usuário criado com sucesso")
                    } else {
                        textViewStatus.text = "Erro ao criar usuário: " // Atualiza a mensagem de erro
                        Log.w("TAG", "Erro ao criar usuário", task.exception)
                    }
                }
        }

        // Redefinir Senha
        buttonResetPassword.setOnClickListener {
            val email = editTextEmail.text.toString()
            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            textViewStatus.text = "E-mail de redefinição enviado" // Atualiza a mensagem
                            Log.d("TAG", "E-mail de redefinição enviado")
                        } else {
                            textViewStatus.text = "Erro ao enviar e-mail de redefinição: ${task.exception?.message}" // Atualiza a mensagem de erro
                            Log.w("TAG", "Erro ao enviar e-mail de redefinição", task.exception)
                        }
                    }
            }
        }

        // Excluir Conta
        buttonDeleteAccount.setOnClickListener {
            val user = auth.currentUser
            user?.let {
                it.delete()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            textViewStatus.text = "Conta excluída com sucesso" // Atualiza a mensagem
                            Log.d("TAG", "Conta excluída com sucesso")
                        } else {
                            textViewStatus.text = "Erro ao excluir conta: ${task.exception?.message}" // Atualiza a mensagem de erro
                            Log.w("TAG", "Erro ao excluir conta", task.exception)
                        }
                    }
            }
        }
    }
}
