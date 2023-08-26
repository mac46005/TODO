package com.preciado.todo.features.home.core

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
    var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId


    //enable/disable visibility of tasks lists
    //enable/disable visibility of incomplete tasks
    private var _incompleteTasksEnabled: MutableLiveData<Boolean> = MutableLiveData(true)
    val incompleteTasksEnabled: LiveData<Boolean> = _incompleteTasksEnabled
    //enable/disable visibility of complete tasks
    private var _completedTasksEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val completedTasksEnabled: LiveData<Boolean> = _completedTasksEnabled

    fun toggleTasks(){
        _incompleteTasksEnabled.value = _incompleteTasksEnabled.value != true
        _completedTasksEnabled.value = _completedTasksEnabled.value == false
    }


    fun resetListView(){
        _incompleteTasksEnabled.value = true
        _completedTasksEnabled.value = false
    }
}