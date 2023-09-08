package com.preciado.todo.features.todo_tasks.core

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.core.models.vm_models.models.ListVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoTasksVM @Inject constructor(
    private var todoListsTable: TODOListsTable,
    private var tasksTable: TasksTable
) : ListVM<Task>() {


    override fun loadList(vararg args: Any): Flow<List<Task>?> {
        return tasksTable.readAll(arrayOf(args[0].toString()))
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }

    override fun setModel(model: Task) {
        setModel(model)
    }

    override fun onLoad(vararg args: Any) {
        viewModelScope.launch {
            val todoList = todoListsTable.read(args[0] as Int)
            title = todoList!!.name
        }
    }

    override fun onBackButtonClicked() {
        navController!!.popBackStack(
            Screen.TODOLists.fullRoute(),
            false,
            false
        )
    }

    override fun navigateTo(route: String) {
        navController!!.navigate(route)
    }

    override fun setInfo(key: String, obj: Any) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(item: Task) {
        TODO("Not yet implemented")
    }


    sealed class ListInfo(val name: String){
        object List : ListInfo("list")
    }
}