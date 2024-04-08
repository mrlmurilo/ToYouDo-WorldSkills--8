package com.murilo.toyoudo.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.modelo.Tarefa
import com.murilo.toyoudo.ui.activity.TarefaActivity

class ListaTarefasAdapter(
    private val context: Context,
    tarefas: List<Tarefa>
) : RecyclerView.Adapter<ListaTarefasAdapter.ViewHolder>() {

    private val tarefas = tarefas.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nome: TextView = view.findViewById(R.id.tarefa_item_layout_nome)
        private val btnDelete: ImageButton = view.findViewById(R.id.tarefa_item_layout_btn_deletar)

        fun vincula(tarefa: Tarefa, context: Context) {
            nome.text = tarefa.nome

            itemView.setOnClickListener {
                val intent = criarIntentParaTarefa(context, tarefa)
                context.startActivity(intent)
            }

            btnDelete.setOnClickListener {
                // Implemente a ação de exclusão aqui, se necessário
            }
        }

        private fun criarIntentParaTarefa(context: Context, tarefa: Tarefa): Intent {
            val intent = Intent(context, TarefaActivity::class.java)
            intent.putExtra("TAREFA_EXTRA", tarefa)
            return intent
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tarefa_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.vincula(tarefa, context)
    }

    override fun getItemCount(): Int = tarefas.size

    fun atualiza(tarefas: List<Tarefa>) {
        this.tarefas.clear()
        this.tarefas.addAll(tarefas)
        notifyDataSetChanged()
    }
}
