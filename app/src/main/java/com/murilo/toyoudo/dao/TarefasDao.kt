package com.murilo.toyoudo.dao

import android.widget.Toast
import com.murilo.toyoudo.modelo.Tarefa

class TarefasDao {

    private lateinit var db: TarefasDatabaseHelper


    companion object {
        private val tarefas = mutableListOf<Tarefa>()
    }


    fun adiciona(tarefa: Tarefa) {
        db.insertTarefa(tarefa)
    }

    fun buscaTodos(): List<Tarefa> {
        return tarefas.toList()
    }

    fun deletarTarefa(id: String) {

    }

}