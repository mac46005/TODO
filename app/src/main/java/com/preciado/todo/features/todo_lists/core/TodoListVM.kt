package com.preciado.todo.features.todo_lists.core

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
    init {
        title = "Your Lists"
    }

    override fun loadList(vararg args: Any): Flow<List<TODOList>?> {
        return todoListsTable.readAll()
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }

    override fun setModel(model: TODOList) {

    }

    override fun onItemSelected(item: TODOList) {
        navController!!.navigate(Screen.TODOTasks.withArgs(item.id.toString()))
    }

    override fun onLoad(vararg args: Any) {
        navController = args[0] as NavController
    }

    override fun onBackButtonClicked() {
        navController!!.navigate(Screen.TODOLists.fullRoute())
    }

    override fun navigateTo(route: String) {
        navController!!.navigate(route)
    }

    override fun setInfo(key: String, obj: Any) {
        TODO("Not yet implemented")
    }


}