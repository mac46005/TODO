package com.preciado.todo.data.interfaces

interface ICreate<T> {
    suspend fun create(obj: T)
}