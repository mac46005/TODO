package com.preciado.todo.data.interfaces

import kotlinx.coroutines.flow.Flow


interface IRead<T> {
    suspend fun read(id: Int): T?
    suspend fun readAll(): Flow<List<T>?>
}