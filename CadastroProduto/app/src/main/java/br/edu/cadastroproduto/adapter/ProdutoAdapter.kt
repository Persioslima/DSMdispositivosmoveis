package br.edu.cadastroproduto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.cadastroproduto.databinding.ItemProdutoBinding
import br.edu.cadastroproduto.model.Produto

class ProdutoAdapter(private val produtoList: List<Produto>) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        // Infla o layout do item de produto
        val binding = ItemProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        // Preenche o item com os dados do produto
        val produto = produtoList[position]
        holder.bind(produto)
    }

    override fun getItemCount(): Int {
        // Retorna a quantidade de itens no RecyclerView
        return produtoList.size
    }

    inner class ProdutoViewHolder(private val binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(produto: Produto) {
            // Vincula os dados do produto aos elementos do layout
            binding.textNome.text = produto.nome
            binding.textCategoria.text = produto.categoria
            binding.textPreco.text = "R$ ${produto.preco}"
        }
    }
}
