package com.murilo.toyoudo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.murilo.toyoudo.R
import com.murilo.toyoudo.modelo.Tarefa

class NovaTarefaActivity : AppCompatActivity(R.layout.activity_nova_tarefa) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btAdicionar = findViewById<Button>(R.id.btAdicionaTarefa)

        btAdicionar.setOnClickListener {
            val campoTitulo = findViewById<EditText>(R.id.etTitulo)
            val nome = campoTitulo.text.toString()
            val campoDescricao = findViewById<EditText>(R.id.etDescricao)
            val descricao = campoDescricao.text.toString()
            val tarefa = Tarefa(
                nome, descricao
            )
            Log.i("FormularioTarefa", "onClick: ${tarefa.toString()}")
        }
    }
}