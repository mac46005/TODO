package com.preciado.todo.features.todo_lists.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.models.vm_models.models.ListVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TodoListVM @Inject constructor (
    private val todoListsTable: TODOListsTable
) : ListVM<TODOList>() {

    private var _model: MutableLiveData<TODOList> = MutableLiveData(TODOList())
    override var model: LiveData<TODOList>? = _model

    override fun getModel(): TODOList {
        return _model!!.value!!
    }

    override fun setModel(model: TODOList) {
        _model.value = model
    }

    init {
        title = "Your Lists"
    }

    override fun loadList(vararg args: Any): Flow<List<TODOList>?> {
        return todoListsTable.readAll()
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }


    override fun onItemSelected(item: TODOList) {
        _navController!!.navigate(Screen.TODOTasks.withArgs(item.id.toString()))
    }

    override fun onLoad(vararg args: Any) {
        _navController = args[0] as NavController
    }

    override fun onBackButtonClicked() {
        _navController!!.navigate(Screen.TODOLists.fullRoute())
    }

    override fun navigateTo(route: String) {
        _navController!!.navigate(route)
    }

    override fun setInfo(key: String, obj: Any) {

    }


}