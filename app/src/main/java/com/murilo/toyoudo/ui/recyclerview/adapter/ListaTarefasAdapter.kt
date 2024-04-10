package com.murilo.toyoudo.ui.recyclerview.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.modelo.Tarefa
import com.murilo.toyoudo.ui.activity.TarefaActivity

class ListaTarefasAdapter(
    private val context: Context, tarefas: List<Tarefa>
) : RecyclerView.Adapter<ListaTarefasAdapter.ViewHolder>() {

    private val tarefas = tarefas.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nome: TextView = view.findViewById(R.id.tarefa_item_layout_nome)
        private val btnDelete: ImageButton = view.findViewById(R.id.tarefa_item_layout_btn_deletar)
        fun vincula(tarefa: Tarefa, context: Context, adapter: ListaTarefasAdapter) {
            nome.text = tarefa.nome

            itemView.setOnClickListener {
                val intent = criarIntentParaTarefa(context, tarefa)
                context.startActivity(intent)
            }

            btnDelete.setOnClickListener {
                deletarTarefa(adapterPosition, adapter)
            }
        }

        private fun criarIntentParaTarefa(context: Context, tarefa: Tarefa): Intent {
            val intent = Intent(context, TarefaActivity::class.java)
            intent.putExtra("TAREFA_EXTRA", tarefa)
            return intent
        }

        private fun deletarTarefa(position: Int, adapter: ListaTarefasAdapter) {
            val tarefa = adapter.tarefas[position]
            val animation = TranslateAnimation(0f, 130f, 0f, 0f) // move para a direita por 130 dp
            animation.duration = 300 // duração da animação em milissegundos
            animation.fillAfter = true // mantém a posição após a animação
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    exibirPopupConfirmacao(adapter, position, itemView, tarefa.id)

                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })

            itemView.startAnimation(animation)
        }

        private fun exibirPopupConfirmacao(
            adapter: ListaTarefasAdapter, position: Int, itemView: View, tarefaId: Int
        ) {
            val dialogView = LayoutInflater.from(itemView.context)
                .inflate(R.layout.dialog_confirmacao_exclusao, null)

            val btnConfirmar =
                dialogView.findViewById<Button>(R.id.dialog_confirmacao_exclusao_btDeletar)
            val btnCancelar =
                dialogView.findViewById<Button>(R.id.dialog_confirmacao_exclusao_btCancelar)

            val alertDialogBuilder =
                AlertDialog.Builder(itemView.context).setView(dialogView).setCancelable(false)

            val alertDialog = alertDialogBuilder.create()

            btnConfirmar.setOnClickListener {
                val dbHelper = TarefasDatabaseHelper(itemView.context)
                dbHelper.deleteTarefa(tarefaId)
                adapter.tarefas.removeAt(position)
                adapter.notifyItemRemoved(position)
                alertDialog.dismiss()
            }

            btnCancelar.setOnClickListener {
                val animation =
                    TranslateAnimation(130f, 0f, 0f, 0f) // move para a direita por 130 dp
                animation.duration = 300 // duração da animação em milissegundos
                animation.fillAfter = true // mantém a posição após a animação
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        alertDialog.dismiss()
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })

                itemView.startAnimation(animation)
            }

            alertDialog.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.tarefa_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.vincula(tarefa, context, this)
    }

    override fun getItemCount(): Int = tarefas.size

    fun atualiza(tarefas: List<Tarefa>) {
        this.tarefas.clear()
        this.tarefas.addAll(tarefas)
        notifyDataSetChanged()
    }
}
