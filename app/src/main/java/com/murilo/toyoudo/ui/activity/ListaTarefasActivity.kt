package com.murilo.toyoudo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDao
import com.murilo.toyoudo.ui.recyclerview.adapter.ListaTarefasAdapter

class ListaTarefasActivity : AppCompatActivity(R.layout.activity_lista_tarefas) {
    private val dao = TarefasDao()
    private val adapter = ListaTarefasAdapter(context = this, tarefas = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }

}