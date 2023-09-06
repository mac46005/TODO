package com.preciado.todo.data

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.data.interfaces.ICRUD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class TODOListsTable @Inject constructor(
    private val dbHelper: DatabaseHelper
) : ICRUD<TODOList> {
    companion object {
        const val TABLE_NAME = "todo_list"
    }

    override suspend fun create(obj: TODOList) {
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues().apply {
            put(DatabaseHelper.COLUMN_LISTS_NAME, obj.name)
        }
        try {
            db.insert(
                TABLE_NAME,
                null,
                contentValues
            )
        } catch (e: SQLiteConstraintException) {
            throw e
        }

        db.close()
    }

    override suspend fun delete(obj: TODOList) {
        var db = dbHelper.writableDatabase
        db.delete(TABLE_NAME, "${DatabaseHelper.COLUMN_LISTS_ID} = ?", arrayOf(obj.id.toString()))
        db.close()
    }

    override suspend fun read(id: Int, foreignKeys: Array<out String>): TODOList? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(DatabaseHelper.COLUMN_LISTS_ID, DatabaseHelper.COLUMN_LISTS_NAME),
            "${DatabaseHelper.COLUMN_LISTS_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LISTS_ID))
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LISTS_NAME))

            db.close()
            return TODOList(id, name)
        } else {
            db.close()
            return null
        }
    }

    override fun readAll(foreignKeys: Array<out String>): Flow<List<TODOList>?> {
        var db = dbHelper.readableDatabase
        var cursor = db.query(
            TABLE_NAME,
            arrayOf(DatabaseHelper.COLUMN_LISTS_ID, DatabaseHelper.COLUMN_LISTS_NAME),
            null,
            null,
            null,
            null,
            null
        )

        var list = mutableListOf<TODOList>()

        while (cursor.moveToNext()) {
            var id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LISTS_ID))
            var name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LISTS_NAME))
            list.add(TODOList(id, name))
        }
        db.close()
        return flow {
            emit(list)
        }
    }

    override suspend fun update(obj: TODOList) {
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues().apply {
            put(DatabaseHelper.COLUMN_LISTS_NAME, obj.name)
        }
        try {
            db.update(
                TABLE_NAME,
                contentValues,
                "${DatabaseHelper.COLUMN_LISTS_ID} = ?",
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
            TABLE_NAME,
            arrayOf(DatabaseHelper.COLUMN_LISTS_NAME),
            "${DatabaseHelper.COLUMN_LISTS_NAME} = ?",
            arrayOf(value),
            null,
            null,
            null
        )

        return cursor.count
    }
}