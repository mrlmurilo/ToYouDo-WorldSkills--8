package com.murilo.toyoudo.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.modelo.Tarefa

class ListaTarefasAdapter(
    private val context: Context,
    tarefas: List<Tarefa>
) : RecyclerView.Adapter<ListaTarefasAdapter.ViewHolder>() {

    private val tarefas = tarefas.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nome: TextView = view.findViewById(R.id.tarefa_item_layout_nome)
        private val btnDelete: ImageButton = view.findViewById(R.id.tarefa_item_layout_btn_deletar)

        fun vincula(tarefa: Tarefa) {
            nome.text = tarefa.nome

            btnDelete.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tarefa_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.vincula(tarefa)
    }

    override fun getItemCount(): Int = tarefas.size

    fun atualiza(tarefas: List<Tarefa>) {
        this.tarefas.clear()
        this.tarefas.addAll(tarefas)
        notifyDataSetChanged()
    }
}
