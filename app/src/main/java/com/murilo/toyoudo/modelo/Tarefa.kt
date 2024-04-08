package com.murilo.toyoudo.modelo

import java.io.Serializable

data class Tarefa(
    val id: Int,
    val nome: String,
    val descricao: String
) : Serializable