package com.preciado.todo.features.add_edit_task.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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
        _navController!!.navigate(Screen.TODOTasks.withArgs(task!!.todoList_id.toString()))
    }

    override fun getModel(): Task {
        return _model.value!!
    }
    override fun setModel(model: Task) {
        _model.value = model
    }



    var _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name

    var _details: MutableLiveData<String> = MutableLiveData("")
    val details: LiveData<String> = _details

    var _isCompleted: MutableLiveData<Boolean> = MutableLiveData(false)
    val isCompleted: LiveData<Boolean> = _isCompleted



    override fun onLoad(vararg args: Any) {
        _navController = args[0] as NavController
        crudOperation = args[1] as CRUD_Operation
        val task = args[2] as Task
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
                _navController!!.navigate(Screen.TODOTasks.withArgs(_model.value!!.todoList_id.toString()))
            }
        }
    }

}