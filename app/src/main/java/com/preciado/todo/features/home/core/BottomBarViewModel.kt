package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor (

): ViewModel() {
    private var _listId: MutableLiveData<Int> = MutableLiveData(0)
    var listId: LiveData<Int> = _listId

    private var _isIncompleteTaskButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    var isIncompleteTaskButtonEnabled: LiveData<Boolean> = _isIncompleteTaskButtonEnabled

    private var _isCompleteTaskButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    var isCompleteTaskButtonEnabled: LiveData<Boolean> = _isCompleteTaskButtonEnabled



    fun setListId(listId: Int){
        _listId.value = listId
    }

    fun setIncompleteButtonEnabled(enabled: Boolean){
        _isIncompleteTaskButtonEnabled.value = enabled
    }

    fun setCompleteButtonEnabled(enabled: Boolean){
        _isCompleteTaskButtonEnabled.value = enabled
    }

    fun toggleButtons(){
        _isIncompleteTaskButtonEnabled.value = _isIncompleteTaskButtonEnabled.value != true
        _isCompleteTaskButtonEnabled.value = _isCompleteTaskButtonEnabled.value == false
    }

    fun isListIdNew(listId: Int) = _listId.value == listId
}