package com.murilo.toyoudo.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDao
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.ui.recyclerview.adapter.ListaTarefasAdapter

class ListaTarefasActivity : AppCompatActivity(R.layout.activity_lista_tarefas) {

    private val REQUEST_CODE_DELETAR_TAREFA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        val dao = TarefasDatabaseHelper(this)
        val adapter = ListaTarefasAdapter(context = this, tarefas = dao.getAllTarefas())
        adapter.atualiza(dao.getAllTarefas())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }

    private fun configuraRecyclerView() {
        val dao = TarefasDatabaseHelper(this)
        val adapter = ListaTarefasAdapter(context = this, tarefas = dao.getAllTarefas())
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DELETAR_TAREFA && resultCode == RESULT_OK) {
            atualizarListaTarefas()
        }
    }

    private fun atualizarListaTarefas() {
        val dao = TarefasDatabaseHelper(this)
        val adapter = ListaTarefasAdapter(context = this, tarefas = dao.getAllTarefas())
        adapter.atualiza(dao.getAllTarefas())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }
}
