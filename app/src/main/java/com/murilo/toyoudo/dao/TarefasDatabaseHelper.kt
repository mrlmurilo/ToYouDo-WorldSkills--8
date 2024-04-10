package com.murilo.toyoudo.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.murilo.toyoudo.modelo.Tarefa

class TarefasDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "toYouDo.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "tarefa"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_DESCRICAO = "descricao"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY," +
                " $COLUMN_NOME TEXT, $COLUMN_DESCRICAO TEXT)"

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertTarefa(tarefa: Tarefa) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, tarefa.nome)
            put(COLUMN_DESCRICAO, tarefa.descricao)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllTarefas(): List<Tarefa> {
        val tarefasList = mutableListOf<Tarefa>()
        var db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
            val descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRICAO))

            val tarefa = Tarefa(id, nome, descricao)
            tarefasList.add(tarefa)
        }
        cursor.close()
        db.close()
        return tarefasList
    }

    fun deleteTarefa(id: Int) {
        val db = writableDatabase
        val whereClaue = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(id.toString())
        db.delete(TABLE_NAME, whereClaue, whereArgs)
    }

}