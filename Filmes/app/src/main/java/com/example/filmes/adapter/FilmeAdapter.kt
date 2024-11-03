package com.example.filmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmes.model.Filme
import com.example.fimes.R

class FilmeAdapter(private val filmes: MutableList<Filme>) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    // ViewHolder para os itens de filme
    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTituloFilme)
        val textViewDiretor: TextView = itemView.findViewById(R.id.textViewDiretorFilme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        // Infla o layout item_filme.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        // Obtém o filme na posição atual
        val filme = filmes[position]
        // Configura o título e o diretor no ViewHolder
        holder.textViewTitulo.text = " ${filme.titulo.uppercase()}" // Adiciona o rótulo
        holder.textViewDiretor.text = " ${filme.diretor.uppercase()}" // Adiciona o rótulo
    }

    override fun getItemCount(): Int {
        // Retorna a quantidade de filmes na lista
        return filmes.size
    }

    // Função para adicionar um filme à lista e notificar o adapter
    fun addFilme(filme: Filme) {
        filmes.add(filme)
        notifyItemInserted(filmes.size - 1) // Notifica que um novo item foi inserido
    }
}
