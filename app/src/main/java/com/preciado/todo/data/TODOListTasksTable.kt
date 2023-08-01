package com.preciado.todo.data

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import com.preciado.todo.core.models.TODOListTask
import com.preciado.todo.data.interfaces.ICRUD
import javax.inject.Inject

class TODOListTasksTable @Inject constructor(
    private val dbHelper: DatabaseHelper
) : ICRUD<TODOListTask>{
    override suspend fun create(obj: TODOListTask) {
        try {
            val db = dbHelper.writableDatabase
            var contentValues = ContentValues().apply {
                put(DatabaseHelper.COLUMN_TASKS_TASK_NAME,obj.taskName)
                put(DatabaseHelper.COLUMN_LIST_FOREIGN_KEY, obj.todoList_id)
            }
            db.insert(
                DatabaseHelper.TABLE_NAME_TASKS,
                null,
                contentValues
            )
        }catch (e: SQLiteConstraintException){
            throw e
        }
    }

    override suspend fun delete(obj: TODOListTask) {

    }

    override suspend fun read(id: Int, foreignKeys: Array<Int>): TODOListTask? {
        try {
            val db = dbHelper.readableDatabase
            var cursor = db.query(
                DatabaseHelper.TABLE_NAME_TASKS,
                arrayOf(
                    DatabaseHelper.COLUMN_TASKS_ID,
                    DatabaseHelper.COLUMN_TASKS_TASK_NAME,
                    DatabaseHelper.COLUMN_TASKS_FOREIGN_KEY
                ),
                "${DatabaseHelper.COLUMN_TASKS_ID} = ? AND ${DatabaseHelper.COLUMN_TASKS_FOREIGN_KEY} = ?",
                arrayOf(
                    id.toString(),
                    foreignKeys[0].toString()
                ),
                null,
                null,
                null
            )

            var todolistTask: TODOListTask = TODOListTask()

            while(cursor.moveToNext()){
                todolistTask.todoList_id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_ID))
                todolistTask.taskName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_TASK_NAME))
                todolistTask.todoList_id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_FOREIGN_KEY))
            }

            return todolistTask
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun readAll(): List<TODOListTask> {
        try {
            val db = dbHelper.readableDatabase
            var cursor = db.query(
                DatabaseHelper.TABLE_NAME_TASKS,
                null,
                null,
                null,
                null,
                null,
                null
            )
        }catch (e: Exception){
            throw e
        }
        //TODO return value
    }

    override suspend fun update(obj: TODOListTask) {
        try {
            val db = dbHelper.writableDatabase
            var contentValues = ContentValues().apply {
                put(DatabaseHelper.COLUMN_TASKS_TASK_NAME,obj.taskName)
                put(DatabaseHelper.COLUMN_TASKS_FOREIGN_KEY, obj.todoList_id)
            }
            db.update(
                DatabaseHelper.TABLE_NAME_TASKS,
                contentValues,
                "${DatabaseHelper.COLUMN_TASKS_ID} = ? AND ${DatabaseHelper.COLUMN_LIST_FOREIGN_KEY} = ?",
                arrayOf(
                    obj.id.toString(),
                    obj.todoList_id.toString()
                )
            )
        }catch (e: SQLiteConstraintException){
            throw e
        }
    }
}