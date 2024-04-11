package com.murilo.toyoudo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.murilo.toyoudo.R

class MainActivity : AppCompatActivity() {

    private lateinit var navigationMenu: MaterialCardView
    private lateinit var btFechar: ImageButton
    private lateinit var btHome: Button
    private lateinit var btNovaTarefa: Button
    private lateinit var btListaTarefas: Button
    private lateinit var btNavegar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Infla o layout aqui

        // Inicializa os componentes do menu de navegação
        btNavegar = findViewById(R.id.activity_main_bt_navegar)
        navigationMenu = findViewById(R.id.activity_main_menuNav)
        btFechar = navigationMenu.findViewById(R.id.navigation_menu_btFechar)
        btHome = navigationMenu.findViewById(R.id.navigation_menu_btHome)
        btNovaTarefa = navigationMenu.findViewById(R.id.navigation_menu_btNovaTarefa)
        btListaTarefas = navigationMenu.findViewById(R.id.navigation_menu_btListaTarefas)

        // Definir visibilidade inicial como GONE
        navigationMenu.visibility = android.view.View.GONE

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
                    navigationMenu.visibility = android.view.View.GONE
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

        val btAddTarefa = findViewById<Button>(R.id.activity_main_btAdicionaTarefa)
        val btListarTarefas = findViewById<Button>(R.id.activity_main_btListarTarefas)

        btAddTarefa.setOnClickListener {
            val intent = Intent(this, NovaTarefaActivity::class.java)
            startActivity(intent)
        }

        btListarTarefas.setOnClickListener {
            val intent = Intent(this, ListaTarefasActivity::class.java)
            startActivity(intent)
        }

        btNavegar.setOnClickListener {
            val animation = TranslateAnimation(-navigationMenu.width.toFloat(), 0f, 0f, 0f)
            animation.duration = 1000  // 3 segundos
            navigationMenu.startAnimation(animation)
            navigationMenu.visibility = android.view.View.VISIBLE
        }
    }

    private fun hideNavigationMenu() {
        navigationMenu.visibility = android.view.View.GONE
    }
}
