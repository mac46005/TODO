package com.preciado.todo.features.todo_tasks.core

import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.core.models.vm_models.models.ListVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoTasksVM @Inject constructor(
    private var todoListsTable: TODOListsTable,
    private var tasksTable: TasksTable
) : ListVM<Task>() {

    init {
        title = ""
    }

    override fun loadList(vararg args: Any): Flow<List<Task>?> {
        return tasksTable.readAll(arrayOf(args[0].toString()))
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }

    override fun setModel(model: Task) {
        _model.value = model
    }

    override fun onLoad(vararg args: Any) {
        TODO("Not yet implemented")
    }

    override fun onBackButtonClicked() {
        navController!!.navigate(Screen.TODOLists.fullRoute())
    }

    override fun navigateTo(route: String) {
        TODO("Not yet implemented")
    }

    override fun setNavigator(navController: NavController) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(item: Task) {
        TODO("Not yet implemented")
    }
}