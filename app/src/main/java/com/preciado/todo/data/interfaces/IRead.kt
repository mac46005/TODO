package com.preciado.todo.data.interfaces

import kotlinx.coroutines.flow.Flow


interface IRead<T> {
    suspend fun read(id: Int, foreignKeys: Array<Int> = emptyArray()): T?
    suspend fun readAll(): List<T>
}