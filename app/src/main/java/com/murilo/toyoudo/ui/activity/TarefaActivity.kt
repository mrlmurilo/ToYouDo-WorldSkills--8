package com.murilo.toyoudo.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.modelo.Tarefa

class TarefaActivity : AppCompatActivity() {

    private lateinit var navigationMenu: MaterialCardView
    private lateinit var btFechar: ImageButton
    private lateinit var btHome: Button
    private lateinit var btNovaTarefa: Button
    private lateinit var btListaTarefas: Button
    private lateinit var btNavegar: ImageButton
    private lateinit var foreground: View
    private lateinit var tarefa: Tarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa)
        val btDeletar = findViewById<ImageButton>(R.id.activity_tarefa_bt_excluir)

        tarefa = intent.getSerializableExtra("TAREFA_EXTRA") as Tarefa

        val nomeTarefa: TextView = findViewById(R.id.activity_tarefa_nomeTarefa)
        nomeTarefa.text = tarefa.nome
        val descricaoTarefa: TextView = findViewById(R.id.activity_tarefa_descricao)
        descricaoTarefa.text = tarefa.descricao

        btDeletar.setOnClickListener {
            exibirPopupConfirmacao()
        }

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

    private fun exibirPopupConfirmacao() {
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_confirmacao_exclusao, null)

        val btnConfirmar =
            dialogView.findViewById<Button>(R.id.dialog_confirmacao_exclusao_btDeletar)
        val btnCancelar =
            dialogView.findViewById<Button>(R.id.dialog_confirmacao_exclusao_btCancelar)

        val alertDialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)

        val alertDialog = alertDialogBuilder.create()

        btnConfirmar.setOnClickListener {
            deletarTarefa()
            alertDialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun deletarTarefa() {
        val dbHelper = TarefasDatabaseHelper(this)
        dbHelper.deleteTarefa(tarefa.id)
        setResult(RESULT_OK) // Define o resultado como OK
        finish() // Finaliza a atividade após a exclusão
    }
}


