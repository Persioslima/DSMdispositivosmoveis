package br.edu.fatecpg.tarefas

import Tarefa
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class TarefaAdapter(private val tarefas: List<Tarefa>) :
    RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefa, parent, false)
        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.nomeTarefa.text = tarefa.nome
        holder.descricaoTarefa.text = tarefa.descricao
        holder.checkBox.isChecked = tarefa.concluida

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            tarefa.concluida = isChecked
        }
    }

    override fun getItemCount(): Int = tarefas.size

    class TarefaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeTarefa: TextView = view.findViewById(R.id.tv_nome_tarefa)
        val descricaoTarefa: TextView = view.findViewById(R.id.tv_descricao_tarefa)
        val checkBox: CheckBox = view.findViewById(R.id.checkbox_concluida)
    }
}