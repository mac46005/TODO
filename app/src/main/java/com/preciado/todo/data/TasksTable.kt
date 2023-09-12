package com.preciado.todo.data

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.models.app_models.models.TaskSet
import com.preciado.todo.data.interfaces.ICRUD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

class TasksTable @Inject constructor(
    private val dbHelper: DatabaseHelper
) : ICRUD<Task>{
    
    companion object{
        private const val TAG = "TasksTable"
    }
    override suspend fun create(obj: Task) {
        try {
            val db = dbHelper.writableDatabase
            var contentValues = ContentValues().apply {
                put(DatabaseHelper.COLUMN_TASKS_TASK_NAME,obj.name)
                put(DatabaseHelper.COLUMN_TASKS_DETAILS, obj.details)
                put(DatabaseHelper.COLUMN_TASKS_CREATED_ON, obj.createdOn.toString())
                if(obj.dueOn != null){
                    put(DatabaseHelper.COLUMN_TASKS_DUE_ON, obj.dueOn.toString())
                }
                put(DatabaseHelper.COLUMN_TASKS_FREQUENCY, obj.taskFrequency.name)
                put(DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK, obj.taskSet.id)
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

    override suspend fun delete(obj: Task) {
        //TODO Implement delete function for TODOListTasksTable.delete()
    }

    override suspend fun read(id: Int, foreignKeys: Array<out String>): Task? {
        try {
            val db = dbHelper.readableDatabase
            var cursor = db.query(
                DatabaseHelper.TABLE_NAME_TASKS,
                arrayOf(
                    DatabaseHelper.COLUMN_TASKS_ID,
                    DatabaseHelper.COLUMN_TASKS_TASK_NAME,
                    DatabaseHelper.COLUMN_TASKS_DETAILS,
                    DatabaseHelper.COLUMN_TASKS_CREATED_ON,
                    DatabaseHelper.COLUMN_TASKS_IS_COMPLETED,
                    DatabaseHelper.COLUMN_TASKS_COMPLETED_ON,
                    DatabaseHelper.COLUMN_TASKS_DUE_ON,
                    DatabaseHelper.COLUMN_TASKS_FREQUENCY,
                    DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK
                ),
                "${DatabaseHelper.COLUMN_TASKS_ID} = ? AND ${DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK} = ?",
                arrayOf(
                    id.toString(),
                    foreignKeys[0]
                ),
                null,
                null,
                null
            )

            var task: Task = Task()

            while(cursor.moveToNext()){
                task.id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_ID))
                task.taskSet = TaskSet(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK)))
                task.name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_TASK_NAME))
                task.details = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_DETAILS))
                task.createdOn = LocalDateTime.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_CREATED_ON)))
                task.isCompleted = if(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_IS_COMPLETED)) == 1) true else false

                val completedOn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_COMPLETED_ON))
                if(completedOn.isNotEmpty() || completedOn.isNotBlank()){
                    task.completedOn = LocalDateTime.parse(completedOn)
                }

                val dueOn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_DUE_ON))
                if(dueOn != null){
                    task.dueOn = LocalDateTime.parse(dueOn)
                }



            }

            return task
        }catch (e: Exception){
            throw e
        }
    }

    override fun readAll(foreignKeys: Array<out String>): Flow<List<Task>?> {
        try {
            val db = dbHelper.readableDatabase
            var cursor = db.query(
                DatabaseHelper.TABLE_NAME_TASKS,
                arrayOf(
                    DatabaseHelper.COLUMN_TASKS_ID,
                    DatabaseHelper.COLUMN_TASKS_TASK_NAME,
                    DatabaseHelper.COLUMN_TASKS_DETAILS,
                    DatabaseHelper.COLUMN_TASKS_CREATED_ON,
                    DatabaseHelper.COLUMN_TASKS_IS_COMPLETED,
                    DatabaseHelper.COLUMN_TASKS_COMPLETED_ON,
                    DatabaseHelper.COLUMN_TASKS_DUE_ON,
                    DatabaseHelper.COLUMN_TASKS_FREQUENCY,
                    DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK
                ),
                "${DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK} = ?",
                foreignKeys,
                null,
                null,
                "${DatabaseHelper.COLUMN_TASKS_IS_COMPLETED} ASC"
            )
            var tasks = mutableListOf<Task>()

            while(cursor.moveToNext()){

                var task: Task = Task()

                task.id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_ID))
                task.taskSet = TaskSet(id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK)))
                task.name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_TASK_NAME))
                task.details = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_DETAILS))
                task.createdOn = LocalDateTime.parse(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_CREATED_ON)))
                task.isCompleted = if(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_IS_COMPLETED)) == 1) true else false

                val completedOn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_COMPLETED_ON))
                if(completedOn != null){
                    task.completedOn = LocalDateTime.parse(completedOn)
                }

                val dueOn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKS_DUE_ON))
                if(dueOn != null){
                    task.dueOn = LocalDateTime.parse(dueOn)
                }




                tasks.add(
                    task
                )
            }
            return flow {
                emit(tasks)
            }

        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun update(obj: Task) {
        try {
            Log.i(TAG, "update: task{id ${obj.id}, listId ${obj.taskSet}, taskName \"${obj.name}\", taskDetails \"${obj.details}\", isCompleted ${obj.isCompleted}}")
            val db = dbHelper.writableDatabase
            var contentValues = ContentValues().apply {
                put(DatabaseHelper.COLUMN_TASKS_TASK_NAME,obj.name)
                put(DatabaseHelper.COLUMN_TASKS_DETAILS, obj.details)
                put(DatabaseHelper.COLUMN_TASKS_IS_COMPLETED, obj.isCompleted)
                if(obj.isCompleted){
                    put(DatabaseHelper.COLUMN_TASKS_COMPLETED_ON, obj.completedOn.toString())
                }
                if(obj.dueOn != null){
                    put(DatabaseHelper.COLUMN_TASKS_DUE_ON, obj.dueOn.toString())
                }
                put(DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK, obj.taskSet.id)
            }
            db.update(
                DatabaseHelper.TABLE_NAME_TASKS,
                contentValues,
                "${DatabaseHelper.COLUMN_TASKS_ID} = ? AND ${DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK} = ?",
                arrayOf(
                    obj.id.toString(),
                    obj.taskSet.toString()
                )
            )
        }catch (e: SQLiteConstraintException){
            throw e
        }
    }









    fun getIncompleteAmount(selectionArgs: Array<out String>): Int{
        var db = dbHelper.readableDatabase
        var cursor = db.rawQuery(
            "SELECT * FROM tasks WHERE ${DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK} = ? AND ${DatabaseHelper.COLUMN_TASKS_IS_COMPLETED} = 0",
            selectionArgs
        )
        return cursor.columnCount
    }

    fun getCompleteAmount(selectionArgs: Array<out String>): Int{
        var db = dbHelper.readableDatabase
        var cursor = db.rawQuery(
            "SELECT * FROM tasks WHERE ${DatabaseHelper.COLUMN_TASKS_TASKSET_ID_FK} = ? AND ${DatabaseHelper.COLUMN_TASKS_IS_COMPLETED} = 1",
            selectionArgs
        )
        return cursor.columnCount
    }

}