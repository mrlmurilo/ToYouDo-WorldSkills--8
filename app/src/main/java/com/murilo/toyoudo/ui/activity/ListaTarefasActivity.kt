package com.murilo.toyoudo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.ui.recyclerview.adapter.ListaTarefasAdapter

class ListaTarefasActivity : AppCompatActivity(R.layout.activity_lista_tarefas) {

    private val REQUEST_CODE_DELETAR_TAREFA = 1
    private lateinit var navigationMenu: MaterialCardView
    private lateinit var btFechar: ImageButton
    private lateinit var btHome: Button
    private lateinit var btNovaTarefa: Button
    private lateinit var btListaTarefas: Button
    private lateinit var btNavegar: ImageButton
    private lateinit var foreground: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        btNavegar = findViewById(R.id.activity_lista_tarefas_bt_navegar)
        navigationMenu = findViewById(R.id.activity_lista_tarefas_menuNav)
        btFechar = navigationMenu.findViewById(R.id.navigation_menu_btFechar)
        btHome = navigationMenu.findViewById(R.id.navigation_menu_btHome)
        btNovaTarefa = navigationMenu.findViewById(R.id.navigation_menu_btNovaTarefa)
        btListaTarefas = navigationMenu.findViewById(R.id.navigation_menu_btListaTarefas)
        foreground = findViewById(R.id.activity_lista_tarefas_foreground)
        foreground.setOnTouchListener { _, _ -> true }

        // Definir visibilidade inicial como GONE
        navigationMenu.visibility = View.GONE
        foreground.visibility = View.GONE

        btFechar.setOnClickListener {
            val animation = TranslateAnimation(0f, -navigationMenu.width.toFloat(), 0f, 0f)
            animation.duration = 1000  // 1 segundo
            navigationMenu.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    // Método chamado quando a animação começa.
                }

                override fun onAnimationEnd(animation: Animation?) {
                    // Método chamado quando a animação termina.
                    navigationMenu.visibility = View.GONE
                    foreground.visibility = View.GONE

                }

                override fun onAnimationRepeat(animation: Animation?) {
                    // Método chamado quando a animação se repete.
                }
            })
        }

        // Configuração do clique para abrir a atividade da home
        btHome.setOnClickListener {
            val intent = Intent(
                this,
                this::class.java
            )  // Substitua HomeActivity pela sua atividade principal
            startActivity(intent)
            hideNavigationMenu()
        }

        // Configuração do clique para abrir a atividade de nova tarefa
        btNovaTarefa.setOnClickListener {
            val intent = Intent(this, NovaTarefaActivity::class.java)
            startActivity(intent)
            hideNavigationMenu()
        }

        // Configuração do clique para abrir a atividade de listar tarefas
        btListaTarefas.setOnClickListener {
            val intent = Intent(this, ListaTarefasActivity::class.java)
            startActivity(intent)
            hideNavigationMenu()
        }

        btNavegar.setOnClickListener {
            val animation = TranslateAnimation(-navigationMenu.width.toFloat(), 0f, 0f, 0f)
            animation.duration = 1000  // 3 segundos
            navigationMenu.startAnimation(animation)
            navigationMenu.visibility = View.VISIBLE
            foreground.visibility = View.VISIBLE
            navigationMenu.elevation = 10f
            foreground.elevation = 9f
        }
    }

    private fun hideNavigationMenu() {
        navigationMenu.visibility = View.GONE
        foreground.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        val dao = TarefasDatabaseHelper(this)
        val adapter = ListaTarefasAdapter(context = this, tarefas = dao.getAllTarefas())
        adapter.atualiza(dao.getAllTarefas())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }

    private fun configuraRecyclerView() {
        val dao = TarefasDatabaseHelper(this)
        val adapter = ListaTarefasAdapter(context = this, tarefas = dao.getAllTarefas())
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DELETAR_TAREFA && resultCode == RESULT_OK) {
            atualizarListaTarefas()
        }
    }

    private fun atualizarListaTarefas() {
        val dao = TarefasDatabaseHelper(this)
        val adapter = ListaTarefasAdapter(context = this, tarefas = dao.getAllTarefas())
        adapter.atualiza(dao.getAllTarefas())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
    }
}
