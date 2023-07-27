package com.preciado.todo.data.interfaces

interface IDelete<T> {
    suspend fun delete(obj: T)
}