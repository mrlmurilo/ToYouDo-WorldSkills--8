package com.murilo.toyoudo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDao
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.modelo.Tarefa
import java.util.UUID

class NovaTarefaActivity : AppCompatActivity(R.layout.activity_formulario_nova_tarefa) {
    private lateinit var navigationMenu: MaterialCardView
    private lateinit var btFechar: ImageButton
    private lateinit var btHome: Button
    private lateinit var btNovaTarefa: Button
    private lateinit var btListaTarefas: Button
    private lateinit var btNavegar: ImageButton
    private lateinit var foreground: View

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

        btNavegar = findViewById(R.id.activity_formulario_nova_tarefa_bt_navegar)
        navigationMenu = findViewById(R.id.activity_formulario_nova_tarefa_menuNav)
        btFechar = navigationMenu.findViewById(R.id.navigation_menu_btFechar)
        btHome = navigationMenu.findViewById(R.id.navigation_menu_btHome)
        btNovaTarefa = navigationMenu.findViewById(R.id.navigation_menu_btNovaTarefa)
        btListaTarefas = navigationMenu.findViewById(R.id.navigation_menu_btListaTarefas)
        foreground = findViewById(R.id.activity_formulario_nova_tarefa_foreground)
        foreground.setOnTouchListener { _, _ -> true }

        // Definir visibilidade inicial como GONE
        navigationMenu.visibility = View.GONE
        foreground.visibility = View.GONE

        btFechar.setOnClickListener {
            closeNavigationMenu()
        }

        btHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            closeNavigationMenu()
        }

        btNovaTarefa.setOnClickListener {
            val intent = Intent(this, NovaTarefaActivity::class.java)
            startActivity(intent)
            closeNavigationMenu()
        }

        btListaTarefas.setOnClickListener {
            val intent = Intent(this, ListaTarefasActivity::class.java)
            startActivity(intent)
            closeNavigationMenu()
        }

        btNavegar.setOnClickListener {
            val animation = TranslateAnimation(-navigationMenu.width.toFloat(), 0f, 0f, 0f)
            animation.duration = 1000
            navigationMenu.startAnimation(animation)
            navigationMenu.visibility = View.VISIBLE
            foreground.visibility = View.VISIBLE
            navigationMenu.elevation = 10f
            foreground.elevation = 9f
        }
    }

    private fun closeNavigationMenu() {
        val animation = TranslateAnimation(0f, -navigationMenu.width.toFloat(), 0f, 0f)
        animation.duration = 1000
        navigationMenu.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                navigationMenu.visibility = View.GONE
                foreground.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

}