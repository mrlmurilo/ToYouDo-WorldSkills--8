package com.murilo.toyoudo.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.modelo.Tarefa

class ListaTarefasAdapter(
    private val context: Context,
    private val tarefas: List<Tarefa>
) : RecyclerView.Adapter<ListaTarefasAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(tarefa: Tarefa) {
            val nome = itemView.findViewById<TextView>(R.id.nome)
            nome.text = tarefa.nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tarefas_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.vincula(tarefa)
    }

    override fun getItemCount(): Int = tarefas.size

}