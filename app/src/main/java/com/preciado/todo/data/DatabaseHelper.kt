package com.preciado.todo.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.preciado.todo.core.models.app_models.TimeFrequency
import javax.inject.Inject

class DatabaseHelper @Inject constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "todo.db"
        private const val DATABASE_VERSION = 1


        const val TABLE_NAME_LISTS = "todo_list"
        const val COLUMN_LISTS_ID = "id"
        const val COLUMN_LISTS_NAME = "list_name"


        const val TABLE_NAME_TASKS = "tasks"
        const val COLUMN_TASKS_ID = "id"
        const val COLUMN_TASKS_TASK_NAME = "task_name"
        const val COLUMN_TASKS_DETAILS = "details"
        const val COLUMN_TASKS_IS_COMPLETED = "is_completed"
        const val COLUMN_TASKS_CREATED_ON = "created_on"
        const val COLUMN_TASKS_COMPLETED_ON = "completed_on"
        const val COLUMN_TASKS_DUE_ON = "due_on"
        const val COLUMN_TASKS_FREQUENCY = "frequency"
        const val COLUMN_TASKS_LIST_ID_FOREIGN_KEY = "list_id_fk"

        const val TABLE_NAME_SUBTASKS = "subtasks"
        const val COLUMN_SUBTASKS_ID = "id"
        const val COLUMN_SUBTASKS_TASK_NAME = "task_name"
        const val COLUMN_SUBTASKS_TASK_ID_FOREIGN_KEY = "task_id_fk"
    }

    override fun onCreate(db: SQLiteDatabase?) {


        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME_LISTS(" +
                    "$COLUMN_LISTS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_LISTS_NAME TEXT NOT NULL UNIQUE" +
                    ")"
        )



        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME_TASKS(" +
                    "$COLUMN_TASKS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TASKS_TASK_NAME TEXT NOT NULL UNIQUE," +
                    "$COLUMN_TASKS_DETAILS TEXT," +
                    "$COLUMN_TASKS_CREATED_ON TEXT NULL," +
                    "$COLUMN_TASKS_IS_COMPLETED BOOLEAN DEFAULT 0," +
                    "$COLUMN_TASKS_COMPLETED_ON TEXT NULL," +
                    "$COLUMN_TASKS_DUE_ON TEXT NULL," +
                    "$COLUMN_TASKS_FREQUENCY TEXT DEFAULT ${TimeFrequency.None.name}," +
                    "$COLUMN_TASKS_LIST_ID_FOREIGN_KEY INTEGER," +
                    "FOREIGN KEY($COLUMN_TASKS_LIST_ID_FOREIGN_KEY) REFERENCES $TABLE_NAME_LISTS($COLUMN_LISTS_ID)" +
                    ")"
        )



        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME_SUBTASKS(" +
                    "$COLUMN_SUBTASKS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_SUBTASKS_TASK_NAME TEXT NOT NULL UNIQUE," +
                    "$COLUMN_SUBTASKS_TASK_ID_FOREIGN_KEY INTEGER," +
                    "FOREIGN KEY($COLUMN_SUBTASKS_TASK_ID_FOREIGN_KEY) REFERENCES $TABLE_NAME_TASKS($COLUMN_TASKS_ID)" +
                    ")"
        )

        db?.execSQL(
            "INSERT INTO $TABLE_NAME_LISTS($COLUMN_LISTS_NAME)" +
                    "VALUES(\"My List\")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}