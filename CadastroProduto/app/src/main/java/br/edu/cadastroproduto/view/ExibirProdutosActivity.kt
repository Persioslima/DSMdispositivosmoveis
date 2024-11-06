package br.edu.cadastroproduto.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.cadastroproduto.R
import br.edu.cadastroproduto.adapter.ProdutoAdapter
import br.edu.cadastroproduto.model.Produto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExibirProdutosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produtoAdapter: ProdutoAdapter
    private lateinit var produtoList: MutableList<Produto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir_produtos)

        // Inicializa a lista de produtos
        produtoList = mutableListOf()
        produtoAdapter = ProdutoAdapter(produtoList)

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = produtoAdapter

        // Carrega os produtos do Firestore
        val db = Firebase.firestore
        db.collection("Produtos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Obtém os dados de cada produto do Firestore
                    val nome = document.getString("nome") ?: ""
                    val categoria = document.getString("categoria") ?: ""
                    val preco = document.getDouble("preco") ?: 0.0

                    // Cria um objeto Produto com os dados
                    val produto = Produto(nome, categoria, preco)
                    produtoList.add(produto)
                }
                // Atualiza o RecyclerView com a lista de produtos
                produtoAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                // Caso ocorra um erro, mostra um Toast com a mensagem de erro
                Toast.makeText(this, "Erro ao carregar os produtos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
