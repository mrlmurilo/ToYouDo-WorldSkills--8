package com.murilo.toyoudo.dao

import com.murilo.toyoudo.modelo.Tarefa

class TarefasDao {

    companion object {
        private val tarefas = mutableListOf<Tarefa>()
    }


    fun adiciona(tarefa: Tarefa) {
        tarefas.add(tarefa)
    }

    fun buscaTodos(): List<Tarefa> {
        return tarefas.toList()
    }

}