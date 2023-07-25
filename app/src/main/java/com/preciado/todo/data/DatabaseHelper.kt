package com.preciado.todo.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Inject

class DatabaseHelper @Inject constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "todo.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val TABLE_NAME_LISTS = "list"
        val COLUMN_LISTS_ID = "id"
        val COLUMN_LISTS_NAME = "list_name"

        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME_LISTS(" +
                    "$COLUMN_LISTS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_LISTS_NAME TEXT NOT NULL" +
                    ")"
        )

        val TABLE_NAME_TASKS = "tasks"
        val COLUMN_TASKS_ID = "id"
        val COLUMN_TASKS_TASK_NAME = "task_name"
        val COLUMN_LIST_FOREIGN_KEY = "list_id_fk"

        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME_TASKS(" +
                    "$COLUMN_TASKS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TASKS_TASK_NAME TEXT NOT NULL," +
                    "$COLUMN_LIST_FOREIGN_KEY INTEGER," +
                    "FOREIGN KEY($COLUMN_LIST_FOREIGN_KEY) REFERENCES $TABLE_NAME_LISTS($COLUMN_LISTS_ID)" +
                    ")"
        )

        val TABLE_NAME_SUBTASKS = "subtasks"
        val COLUMN_SUBTASKS_ID = "id"
        val COLUMN_SUBTASKS_TASK_NAME = "task_name"
        val COLUMN_TASK_FOREIGN_KEY = "task_id_fk"

        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME_SUBTASKS(" +
                    "$COLUMN_SUBTASKS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_SUBTASKS_TASK_NAME TEXT NOT NULL," +
                    "$COLUMN_TASK_FOREIGN_KEY INTEGER," +
                    "FOREIGN KEY($COLUMN_TASK_FOREIGN_KEY) REFERENCES $TABLE_NAME_TASKS($COLUMN_TASKS_ID)" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}