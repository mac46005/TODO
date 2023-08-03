package com.preciado.todo.features.add_edit_task.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.data.CRUDEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(

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


    fun initialize(){

    }

    fun onNameChange(newName: String){
        _taskName.value = newName
    }

    fun onDetailsChange(newDetails: String){
        _taskDetails.value = newDetails
    }
}