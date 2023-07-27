package com.preciado.todo.data.interfaces

interface IUpdate<T> {
    suspend fun update(obj: T)
}