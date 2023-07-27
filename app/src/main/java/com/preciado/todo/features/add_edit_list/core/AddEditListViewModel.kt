package com.preciado.todo.features.add_edit_list.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditListViewModel @Inject constructor(
) : ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name
    fun onNameChange(newName: String){
        _name.value = newName
    }
}