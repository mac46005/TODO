package com.preciado.todo.features.taskset_list.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.TaskSet
import com.preciado.todo.core.models.vm_models.models.ListVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.TaskSetsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TaskSetListVM @Inject constructor (
    private val taskSetsTable: TaskSetsTable
) : ListVM<TaskSet>() {

    private var _model: MutableLiveData<TaskSet> = MutableLiveData(TaskSet())
    override var model: LiveData<TaskSet>? = _model

    override fun getModel(): TaskSet {
        return _model!!.value!!
    }

    override fun setModel(model: TaskSet) {
        _model.value = model
    }

    init {
        title = "Your Lists"
    }

    override fun loadList(vararg args: Any): Flow<List<TaskSet>?> {
        return taskSetsTable.readAll()
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }


    override fun onItemSelected(item: TaskSet) {
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