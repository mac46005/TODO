package com.preciado.todo.features.add_edit_task.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.core.models.vm_models.models.FormVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.data.TasksTable
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditTaskFormVM @Inject constructor(
    private val tasksTable: TasksTable
): FormVM<Task>() {






    private var _model: MutableLiveData<Task> = MutableLiveData(Task())
    override var model: LiveData<Task>? = _model





    override fun onBackButtonClicked() {
        val task = _model.value
        navController!!.navigate(Screen.TODOTasks.withArgs(task!!.todoList_id.toString()))
    }

    override fun getModel(): Task {
        return _model.value!!
    }
    override fun setModel(model: Task) {
        _model.value = model
    }

    override fun onLoad(vararg args: Any) {
        crudOperation = args[0] as CRUD_Operation
        val task = args[1] as Task
        when(crudOperation){
            CRUD_Operation.CREATE -> {
                title = "Add new Task"
                _model.value = task
            }
            CRUD_Operation.UPDATE -> {
                viewModelScope.launch {
                    _model.value = tasksTable.read(task.id, arrayOf(task.todoList_id.toString()))
                    title = "Edit Task"
                }
            }
            else -> {

            }
        }
    }








    override fun submitForm() {
        viewModelScope.launch {
            var task = _model.value!!


            when(crudOperation){
                CRUD_Operation.CREATE -> {
                    tasksTable.create(task)
                }
                CRUD_Operation.UPDATE -> {
                    tasksTable.update(task)
                }
                else -> {

                }
            }.also{
                navController!!.navigate(Screen.TODOTasks.withArgs(_model.value!!.todoList_id.toString()))
            }
        }
    }

}