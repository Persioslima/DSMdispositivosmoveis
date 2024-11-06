package br.edu.cadastroproduto.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.cadastroproduto.databinding.ActivityCadastroProdutoBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CadastroProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa o binding
        binding = ActivityCadastroProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração do título da tela
        supportActionBar?.title = "Cadastro de Produto"

        // Configura o listener do botão de salvar
        binding.salva.setOnClickListener {
            // Obtém os dados inseridos no formulário
            val nome = binding.editNome.text.toString()
            val categoria = binding.editCategoria.text.toString()
            val preco = binding.editPreco.text.toString().toDoubleOrNull()

            // Verifica se o preço é válido
            if (nome.isNotBlank() && categoria.isNotBlank() && preco != null) {
                // Cria um campo de data e hora atual para a gravação
                val tempo = FieldValue.serverTimestamp()

                // Cria um mapa representando o produto
                val produto = hashMapOf(
                    "nome" to nome,
                    "categoria" to categoria,
                    "preco" to preco,
                    "gravacao" to tempo
                )

                // Salva o produto na coleção "Produtos"
                val db = Firebase.firestore
                db.collection("Produtos")
                    .add(produto)
                    .addOnSuccessListener {
                        // Mostra um Toast de sucesso
                        Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                        // Limpa os campos após o cadastro
                        binding.editNome.text.clear()
                        binding.editCategoria.text.clear()
                        binding.editPreco.text.clear()
                    }
                    .addOnFailureListener { e ->
                        // Exibe um Toast de erro
                        Toast.makeText(this, "Erro ao salvar: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Exibe um Toast se algum dado obrigatório não for preenchido ou se o preço for inválido
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuração do botão para exibir produtos cadastrados
        binding.btnExibirProdutos.setOnClickListener {
            val intent = Intent(this, ExibirProdutosActivity::class.java)
            startActivity(intent)
        }
    }
}
