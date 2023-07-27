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

            return TODOList(id, name)
        }else{
            return null
        }
    }

    override suspend fun readAll(): Flow<List<TODOList>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(obj: TODOList) {
        TODO("Not yet implemented")
    }

}