package com.preciado.todo.data

import com.preciado.todo.core.models.app_models.models.Log
import com.preciado.todo.data.interfaces.ICRUD
import kotlinx.coroutines.flow.Flow

class TaskLogsTable(
    private val dbHelper: DatabaseHelper
): ICRUD<Log> {
    override suspend fun create(obj: Log) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(obj: Log) {
        TODO("Not yet implemented")
    }

    override suspend fun read(id: Int, foreignKeys: Array<out String>): Log? {
        TODO("Not yet implemented")
    }

    override fun readAll(foreignKeys: Array<out String>): Flow<List<Log>?> {
        TODO("Not yet implemented")
    }

    override suspend fun update(obj: Log) {
        TODO("Not yet implemented")
    }

}