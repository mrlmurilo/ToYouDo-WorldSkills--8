package com.murilo.toyoudo.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.murilo.toyoudo.R
import com.murilo.toyoudo.dao.TarefasDatabaseHelper
import com.murilo.toyoudo.modelo.Tarefa

class TarefaActivity : AppCompatActivity() {

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


