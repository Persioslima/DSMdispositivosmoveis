package br.edu.fatecpg.cadastrousuario.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import br.edu.fatecpg.cadastrousuario.R

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewCadastro: TextView

    private var tentativaContador = 0
    private val maxTentativas = 3
    private var bloqueado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências aos componentes da tela
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextSenha = findViewById(R.id.editTextSenha)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewCadastro = findViewById(R.id.textViewCadastro)

        // Habilitar/Desabilitar o botão de login
        val enableLoginButton = {
            buttonLogin.isEnabled =
                editTextEmail.text.isNotEmpty() && editTextSenha.text.isNotEmpty()
        }

        editTextEmail.addTextChangedListener { enableLoginButton() }
        editTextSenha.addTextChangedListener { enableLoginButton() }

        // Ação do botão de login
        buttonLogin.setOnClickListener {
            if (bloqueado) {
                Toast.makeText(
                    this,
                    "Você está bloqueado. Tente novamente mais tarde.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()

            when {
                email.isEmpty() -> Toast.makeText(this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show()
                senha.isEmpty() -> Toast.makeText(this, "Por favor, insira sua senha.", Toast.LENGTH_SHORT).show()
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(this, "E-mail inválido.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    if (verificarLogin(email, senha)) {
                        // Login bem-sucedido
                        val intent = Intent(this, UsuariosCadastradosActivity::class.java)
                        startActivity(intent)
                        finish() // Finaliza a LoginActivity
                    } else {
                        // Incrementa o contador de tentativas
                        tentativaContador++
                        if (tentativaContador >= maxTentativas) {
                            bloquearLogin()
                        } else {
                            Toast.makeText(this, "E-mail ou senha incorretos.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        // Ação do link de cadastro
        textViewCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    // Simulação de verificação de login (normalmente seria uma consulta a um banco ou API)
    private fun verificarLogin(email: String, senha: String): Boolean {
        val usuarioValido = "usuario@exemplo.com"
        val senhaValida = "senha123"
        return email == usuarioValido && senha == senhaValida
    }

    // Bloquear login após 3 tentativas falhas
    private fun bloquearLogin() {
        Toast.makeText(this, "Você foi bloqueado por 1 minuto devido a tentativas excessivas.", Toast.LENGTH_LONG).show()
        buttonLogin.isEnabled = false
        bloqueado = true

        // Desbloqueia após 1 minuto
        Handler(Looper.getMainLooper()).postDelayed({
            buttonLogin.isEnabled = true
            bloqueado = false
            tentativaContador = 0
        }, 60000) // 1 minuto = 60000 milissegundos
    }
}