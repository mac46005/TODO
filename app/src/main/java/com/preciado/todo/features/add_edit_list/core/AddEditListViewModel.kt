package com.preciado.todo.features.add_edit_list.core

import androidx.lifecycle.ViewModel
import com.preciado.todo.data.CRUDEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditListViewModel @Inject constructor(
    crudOperation: CRUDEnum = CRUDEnum.CREATE
) : ViewModel() {
    val title = if(crudOperation.equals(CRUDEnum.CREATE)) "Add New List" else "Update List Name"


}