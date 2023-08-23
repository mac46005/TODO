package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
    private val tasksTable: TasksTable
) : ViewModel() {


    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId


    private var _unselectedTasksEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    var unselectedTasksEnabled: LiveData<Boolean> = _unselectedTasksEnabled

    private var _completedTasksEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    var completedTasksEnabled: LiveData<Boolean> = _completedTasksEnabled


    fun loadTodoLists(): Flow<List<TODOList>?> = todoListsTable.readAll()

    fun onListSelected(todoListId: Int) {
        _selectedTODOListId.value = todoListId
    }

    fun uncompletedTasks(listId: Int) = tasksTable.getUnCompletedTasks(arrayOf(listId.toString()))
    fun completedTasks(listId: Int) = tasksTable.getCompletedTasks(arrayOf(listId.toString()))

}