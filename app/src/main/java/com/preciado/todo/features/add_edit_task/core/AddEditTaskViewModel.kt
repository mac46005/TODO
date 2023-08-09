package com.preciado.todo.features.add_edit_task.core

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.data.TODOListTasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val todoListTasksTable: TODOListTasksTable
): ViewModel(){

    private var crudOperation: CRUDEnum = CRUDEnum.CREATE

    private val _listId: MutableLiveData<Int> = MutableLiveData(0)
    val listId: LiveData<Int> = _listId

    private val _listTaskId: MutableLiveData<Int> = MutableLiveData(0)
    val listTaskId: LiveData<Int> = _listTaskId

    private val _taskName: MutableLiveData<String> = MutableLiveData("")
    val taskname: LiveData<String> = _taskName

    private val _taskDetails: MutableLiveData<String> = MutableLiveData("")
    val taskDetails: LiveData<String> = _taskDetails

    private val _isEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEnabled: LiveData<Boolean> = _isEnabled


    fun initialize(crudOperation: CRUDEnum, listId: Int,taskId: Int){

        this.crudOperation = crudOperation

        _listId.value = listId

        if(this.crudOperation.equals(CRUDEnum.UPDATE)){
            viewModelScope.launch {
                var task = todoListTasksTable.read(taskId)
                if(task != null){
                    _listId.value = task.id
                    _taskName.value = task.taskName
                }
            }
        }
    }

    fun onNameChange(newName: String){
        _taskName.value = newName
        isNameNotEmpty()
    }

    fun onDetailsChange(newDetails: String){
        _taskDetails.value = newDetails
    }

    private fun isNameNotEmpty(){
        _isEnabled.value = _taskName.value!!.isNotEmpty()
    }
    fun submit(){
        try {
            viewModelScope.launch {
                if(crudOperation.equals(CRUDEnum.CREATE)){
                    todoListTasksTable.create(
                        Task(
                            todoList_id = _listId.value!!,
                            taskName = _taskName.value!!,
                            details = _taskDetails.value!!
                        )
                    )
                }else{
                    todoListTasksTable.update(
                        Task(
                            todoList_id = _listId.value!!,
                            taskName = _taskName.value!!,
                            details = _taskDetails.value?: ""
                        )
                    )
                }
            }
        }catch (e: SQLiteConstraintException){
            throw e
        }
    }

    fun onCanceled(){
        onCleared()
    }
}