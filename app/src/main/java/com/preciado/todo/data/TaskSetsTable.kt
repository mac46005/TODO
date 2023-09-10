package com.preciado.todo.data

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import com.preciado.todo.core.models.app_models.TaskSet
import com.preciado.todo.data.interfaces.ICRUD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class TaskSetsTable @Inject constructor(
    private val dbHelper: DatabaseHelper
) : ICRUD<TaskSet> {
    companion object {
    }

    override suspend fun create(obj: TaskSet) {
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TASKSETS_NAME, obj.name)
        }
        try {
            db.insert(
                DatabaseHelper.TABLE_NAME_TASKSETS,
                null,
                contentValues
            )
        } catch (e: SQLiteConstraintException) {
            throw e
        }

        db.close()
    }

    override suspend fun delete(obj: TaskSet) {
        var db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.TABLE_NAME_TASKSETS, "${DatabaseHelper.COLUMN_TASKSETS_ID} = ?", arrayOf(obj.id.toString()))
        db.close()
    }

    override suspend fun read(id: Int, foreignKeys: Array<out String>): TaskSet? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME_TASKSETS,
            arrayOf(DatabaseHelper.COLUMN_TASKSETS_ID, DatabaseHelper.COLUMN_TASKSETS_NAME),
            "${DatabaseHelper.COLUMN_TASKSETS_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKSETS_ID))
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKSETS_NAME))

            db.close()
            return TaskSet(id, name)
        } else {
            db.close()
            return null
        }
    }

    override fun readAll(foreignKeys: Array<out String>): Flow<List<TaskSet>?> {
        var db = dbHelper.readableDatabase
        var cursor = db.query(
            DatabaseHelper.TABLE_NAME_TASKSETS,
            arrayOf(DatabaseHelper.COLUMN_TASKSETS_ID, DatabaseHelper.COLUMN_TASKSETS_NAME),
            null,
            null,
            null,
            null,
            null
        )

        var list = mutableListOf<TaskSet>()

        while (cursor.moveToNext()) {
            var id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKSETS_ID))
            var name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TASKSETS_NAME))
            list.add(TaskSet(id, name))
        }
        db.close()
        return flow {
            emit(list)
        }
    }

    override suspend fun update(obj: TaskSet) {
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TASKSETS_NAME, obj.name)
        }
        try {
            db.update(
                DatabaseHelper.TABLE_NAME_TASKSETS,
                contentValues,
                "${DatabaseHelper.COLUMN_TASKSETS_ID} = ?",
                arrayOf(obj.id.toString())
            )
        }catch (e: SQLiteConstraintException){
            throw e
        }


        db.close()
    }

    suspend fun findName(value: String): Int{
        var db = dbHelper.readableDatabase
        var cursor = db.query(
            DatabaseHelper.TABLE_NAME_TASKSETS,
            arrayOf(DatabaseHelper.COLUMN_TASKSETS_NAME),
            "${DatabaseHelper.COLUMN_TASKSETS_NAME} = ?",
            arrayOf(value),
            null,
            null,
            null
        )

        return cursor.count
    }
}