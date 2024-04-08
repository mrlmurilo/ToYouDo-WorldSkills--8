package com.murilo.toyoudo.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.murilo.toyoudo.R
import com.murilo.toyoudo.modelo.Tarefa

class TarefaActivity : AppCompatActivity() {

    private lateinit var tarefa: Tarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa)

        tarefa = intent.getSerializableExtra("TAREFA_EXTRA") as Tarefa

        val nomeTarefa: TextView = findViewById(R.id.activity_tarefa_nomeTarefa)
        nomeTarefa.text = tarefa.nome
        val descricaoTarefa: TextView = findViewById(R.id.activity_tarefa_descricao)
        descricaoTarefa.text = tarefa.descricao
    }



}
