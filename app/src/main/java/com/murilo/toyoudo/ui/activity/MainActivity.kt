package com.murilo.toyoudo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.murilo.toyoudo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Infla o layout aqui

        val btAddTarefa = findViewById<Button>(R.id.btAdicionaTarefa)
        val btListarTarefas = findViewById<Button>(R.id.btListarTarefas)

        btAddTarefa.setOnClickListener {
            val intent = Intent(this, NovaTarefaActivity::class.java)
            startActivity(intent)
        }

        btListarTarefas.setOnClickListener {
            val intent = Intent(this, TarefasActivity::class.java)
            startActivity(intent)
        }
    }
}