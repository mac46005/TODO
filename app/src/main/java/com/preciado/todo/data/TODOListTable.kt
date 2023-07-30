package com.preciado.todo.data

import android.content.ContentValues
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.interfaces.ICRUD
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TODOListTable @Inject constructor(
    private val dbHelper: DatabaseHelper
) : ICRUD<TODOList> {
    companion object{
        const val TABLE_NAME = "todo_list"
    }
    override suspend fun create(obj: TODOList) {
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues().apply {
            put("name", obj.name)
        }
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    override suspend fun delete(obj: TODOList) {
        var db = dbHelper.writableDatabase
        db.delete(TABLE_NAME, "id = ?", arrayOf(obj.id.toString()))
        db.close()
    }

    override suspend fun read(id: Int): TODOList? {
        val db = dbHelper.readableDatabase
        var cursor = db.query(
            TABLE_NAME,
            arrayOf("id", "name"),
            "id = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if(cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))

            db.close()
            return TODOList(id, name)
        }else{
            db.close()
            return null
        }
    }

    override suspend fun readAll(): List<TODOList> {
        var db = dbHelper.readableDatabase
        var cursor = db.query(
            TABLE_NAME,
            arrayOf("id", "name"),
            null,
            null,
            null,
            null,
            null
        )

        var list = mutableListOf<TODOList>()

        while (cursor.moveToNext()){
            var id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            var name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            list.add(TODOList(id, name))
        }
        db.close()
        return list
    }

    override suspend fun update(obj: TODOList) {
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues().apply {
            put("name", obj.name)
        }
        db.update(TABLE_NAME, contentValues, "id = ?", arrayOf(obj.id.toString()))

        db.close()
    }

}