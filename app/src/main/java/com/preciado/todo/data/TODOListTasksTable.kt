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
                put(DatabaseHelper.COLUMN_TASKS_DETAILS, obj.details)
                put(DatabaseHelper.COLUMN_TASKS_LIST_ID_FOREIGN_KEY, obj.todoList_id)
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
                    DatabaseHelper.COLUMN_TASKS_DETAILS,
                    DatabaseHelper.COLUMN_TASKS_LIST_ID_FOREIGN_KEY
                ),
                "${DatabaseHelper.COLUMN_TASKS_ID} = ? AND ${DatabaseHelper.COLUMN_TASKS_LIST_ID_FOREIGN_KEY} = ?",
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
                todolistTask.details = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_DETAILS))
                todolistTask.todoList_id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SUBTASKS_TASK_ID_FOREIGN_KEY))
            }

            return todolistTask
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun readAll(foreignKeys: Array<out String>): List<TODOListTask>? {
        try {
            val db = dbHelper.readableDatabase
            var cursor = db.query(
                DatabaseHelper.TABLE_NAME_TASKS,
                arrayOf(
                    DatabaseHelper.COLUMN_TASKS_ID,
                    DatabaseHelper.COLUMN_TASKS_TASK_NAME,
                    DatabaseHelper.COLUMN_TASKS_DETAILS,
                    DatabaseHelper.COLUMN_TASKS_LIST_ID_FOREIGN_KEY
                ),
                "${DatabaseHelper.COLUMN_TASKS_LIST_ID_FOREIGN_KEY} = ?",
                foreignKeys,
                null,
                null,
                null
            )
            var todoTaskList = mutableListOf<TODOListTask>()

            while(cursor.moveToNext()){
                var id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_ID))
                var taskName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_TASK_NAME))
                var details = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_DETAILS))
                var listId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SUBTASKS_TASK_ID_FOREIGN_KEY))
                todoTaskList.add(TODOListTask(id, listId, taskName))
            }
            return todoTaskList
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun update(obj: TODOListTask) {
        try {
            val db = dbHelper.writableDatabase
            var contentValues = ContentValues().apply {
                put(DatabaseHelper.COLUMN_TASKS_TASK_NAME,obj.taskName)
                put(DatabaseHelper.COLUMN_SUBTASKS_TASK_ID_FOREIGN_KEY, obj.todoList_id)
            }
            db.update(
                DatabaseHelper.TABLE_NAME_TASKS,
                contentValues,
                "${DatabaseHelper.COLUMN_TASKS_ID} = ? AND ${DatabaseHelper.COLUMN_TASKS_LIST_ID_FOREIGN_KEY} = ?",
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