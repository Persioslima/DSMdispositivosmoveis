package br.edu.fatecpg.cadastrousuario.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.edu.fatecpg.cadastrousuario.R

class CadastroActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var editTextConfirmarSenha: EditText
    private lateinit var buttonCadastrar: Button

    // SharedPreferences para salvar os dados
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Referências aos componentes da tela
        editTextEmail = findViewById(R.id.editTextCadastroEmail)
        editTextSenha = findViewById(R.id.editTextCadastroSenha)
        editTextConfirmarSenha = findViewById(R.id.editTextCadastroConfirmarSenha)
        buttonCadastrar = findViewById(R.id.buttonCadastrar)

        // Inicializa o SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Habilitar/Desabilitar o botão de cadastro
        val enableCadastroButton = {
            buttonCadastrar.isEnabled =
                editTextEmail.text.isNotEmpty() && editTextSenha.text.isNotEmpty() && editTextConfirmarSenha.text.isNotEmpty()
        }

        editTextEmail.addTextChangedListener { enableCadastroButton() }
        editTextSenha.addTextChangedListener { enableCadastroButton() }
        editTextConfirmarSenha.addTextChangedListener { enableCadastroButton() }

        // Ação do botão de cadastro
        buttonCadastrar.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()
            val confirmarSenha = editTextConfirmarSenha.text.toString()

            when {
                email.isEmpty() -> Toast.makeText(this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show()
                senha.isEmpty() -> Toast.makeText(this, "Por favor, insira sua senha.", Toast.LENGTH_SHORT).show()
                confirmarSenha.isEmpty() -> Toast.makeText(this, "Por favor, confirme sua senha.", Toast.LENGTH_SHORT).show()
                senha != confirmarSenha -> Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
                else -> {
                    // Salva os dados do usuário no SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("user_email", email)
                    editor.putString("user_password", senha)  // Só se quiser salvar a senha, se não pode omitir
                    editor.putString("user_name", "Nome do Usuário")  // Aqui você pode adicionar o campo de nome se for necessário
                    editor.apply()

                    // Simular o cadastro (aqui seria uma lógica real de cadastro)
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()

                    // Finaliza a tela de cadastro e vai para a tela de usuários cadastrados
                    finish()
                }
            }
        }
    }
}
