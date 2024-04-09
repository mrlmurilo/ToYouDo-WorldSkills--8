package com.murilo.toyoudo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDao
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.modelo.Tarefa
import java.util.UUID

class NovaTarefaActivity : AppCompatActivity(R.layout.activity_formulario_nova_tarefa) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btAdicionar = findViewById<Button>(R.id.btConfirmar)
        val intent = Intent(this, MainActivity::class.java)

        btAdicionar.setOnClickListener {
            val id = 0
            val campoTitulo = findViewById<EditText>(R.id.etTitulo)
            val nome = campoTitulo.text.toString()
            val campoDescricao = findViewById<EditText>(R.id.etDesc)
            val descricao = campoDescricao.text.toString()
            val tarefa = Tarefa(
                id, nome, descricao
            )
            val dao = TarefasDatabaseHelper(this)
            dao.insertTarefa(tarefa)
            Toast.makeText(this, "Feita com sucesso", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}