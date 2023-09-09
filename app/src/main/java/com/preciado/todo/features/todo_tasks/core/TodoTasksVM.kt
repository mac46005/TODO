package com.preciado.todo.features.todo_tasks.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.TODOList
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

    private var _model: MutableLiveData<Task> = MutableLiveData(Task())
    override var model: LiveData<Task>? = _model
    override fun getModel(): Task {
        return _model!!.value!!
    }

    override fun setModel(model: Task) {
        _model.value = model
    }
    override fun setInfo(key: String, obj: Any) {

    }

    override fun loadList(vararg args: Any): Flow<List<Task>?> {
        return tasksTable.readAll(arrayOf(args[0].toString()))
    }

    override fun updateList() {
    }



    override fun onLoad(vararg args: Any) {
        viewModelScope.launch {
            val todoList = todoListsTable.read(args[0] as Int)
            title = todoList!!.name
        }
    }

    override fun onBackButtonClicked() {
        _navController!!.popBackStack(
            Screen.TODOLists.fullRoute(),
            false,
            false
        )
    }

    override fun navigateTo(route: String) {
        _navController!!.navigate(route)
    }



    override fun onItemSelected(item: Task) {
        TODO("Not yet implemented")
    }


    sealed class ListInfo(val name: String){
        object List : ListInfo("list")
    }
}