package br.edu.fatecpg.clinica

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.clinica.databinding.ItemConsultaBinding


class ConsultaAdapter(
    private val consultas: List<Consulta>
) : RecyclerView.Adapter<ConsultaAdapter.ConsultaViewHolder>() {

    // ViewHolder que usa o ViewBinding
    class ConsultaViewHolder(private val binding: ItemConsultaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(consulta: Consulta) {
            binding.nomePaciente.text = consulta.nomePaciente
            binding.tipoConsulta.text = consulta.tipoConsulta
            binding.dataConsulta.text = consulta.dataConsulta
            binding.horaConsulta.text = consulta.horaConsulta
        }
    }

    // Cria o ViewHolder e infla o layout de cada item usando ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val binding = ItemConsultaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConsultaViewHolder(binding)
    }

    // Atualiza a visualização do ViewHolder
    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
        val consulta = consultas[position]
        holder.bind(consulta)
    }

    // Retorna o número de itens (consultas) no RecyclerView
    override fun getItemCount(): Int = consultas.size
}
