package com.murilo.toyoudo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDao
import com.murilo.toyoudo.ui.recyclerview.adapter.ListaTarefasAdapter

class TarefasActivity : AppCompatActivity(R.layout.tarefas_layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val dao = TarefasDao()
        recyclerView.adapter = ListaTarefasAdapter(context = this, tarefas= dao.buscaTodos())
    }

}