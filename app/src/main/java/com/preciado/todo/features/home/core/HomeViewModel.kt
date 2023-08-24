package com.preciado.todo.features.home.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
    private val tasksTable: TasksTable
) : ViewModel() {


    companion object {
        private const val TAG = "HomeViewModel"
    }

    //listID
    private var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId


    //Buttons to enable/disable tasks lists
    //enable/disable uncomplete tasks button
    private var _incompTButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val incompTButtonEnabled: LiveData<Boolean> = _incompTButtonEnabled
    //enable/disable complete tasks button
    private var _compTButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val compTButtonEnabled: LiveData<Boolean> = _compTButtonEnabled


    //enable/disable visibility of tasks lists
    //enable/disable visibility of incomplete tasks
    private var _incompleteTasksEnabled: MutableLiveData<Boolean> = MutableLiveData(true)
    val incompleteTasksEnabled: LiveData<Boolean> = _incompleteTasksEnabled
    //enable/disable visibility of complete tasks
    private var _completedTasksEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val completedTasksEnabled: LiveData<Boolean> = _completedTasksEnabled


    //tasks count
    private var _incompleteTasksCount: MutableLiveData<Int> = MutableLiveData(0)
    val incompleteTasksCount: LiveData<Int> = _incompleteTasksCount
    private var _completedTasksCount: MutableLiveData<Int> = MutableLiveData(0)
    val completedTasksCount: LiveData<Int> = _completedTasksCount




    fun loadTodoLists(): Flow<List<TODOList>?> = todoListsTable.readAll()

    fun onListSelected(todoListId: Int) {
        _selectedTODOListId.value = todoListId
    }

    fun setIncompletedTasksCount(count: Int){
        _incompleteTasksCount.value = count
    }

    fun setCompletedTasksCount(count: Int){
        _completedTasksCount.value = count
    }

    fun setIncompleteTasksEnabled(isEnabled: Boolean){
        _incompleteTasksEnabled.value = isEnabled
    }
    fun setCompleteTasksEnabled(isEnabled: Boolean){
        _completedTasksEnabled.value = isEnabled
    }

    fun setIncompleteTasksButtonEnabled(bool: Boolean){
        _incompTButtonEnabled.value = bool
    }
    fun setCompletedTasksButtonEnabled(bool: Boolean){
       _compTButtonEnabled.value = bool
    }

    fun updateIncompleteTasksCount(count: Int){
        _incompleteTasksCount.value = count
    }
    fun updateCompleteTasksCount(count: Int){
        _completedTasksCount.value = count
    }

    fun onTaskItemChecked(task: Task){
        viewModelScope.launch {
            tasksTable.update(task)
        }
    }

    fun uncompletedTasks(listId: Int) = tasksTable.getUnCompletedTasks(arrayOf(listId.toString()))
    fun completedTasks(listId: Int) = tasksTable.getCompletedTasks(arrayOf(listId.toString()))
}