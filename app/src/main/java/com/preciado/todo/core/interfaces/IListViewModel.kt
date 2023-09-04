package com.preciado.todo.core.interfaces

import kotlinx.coroutines.flow.Flow

interface IListViewModel<T> : IViewModel<T> {
    fun loadList(vararg args: Any): Flow<List<T>>

}