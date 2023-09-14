package com.preciado.todo.features.add_edit_task.core

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.models.TaskSet
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.models.vm_models.models.FormVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.data.TaskSetsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddEditTaskFormVM"

@HiltViewModel
class AddEditTaskFormVM @Inject constructor(
    private val tasksTable: TasksTable,
    private val taskSetsTable: TaskSetsTable
): FormVM<Task>() {






    private var _model: MutableLiveData<Task> = MutableLiveData(Task(taskSet = TaskSet(id = 0)))
    override var model: LiveData<Task>? = _model





    override fun onBackButtonClicked() {
        val task = _model.value!!
        _navController!!.navigate(Screen.TaskList.withArgs(task.taskSet.id.toString()))
    }

    override fun getModel(): Task {
        return _model.value!!
    }
    override fun setModel(model: Task) {
        _model.value = model
    }



    private var _name: MutableLiveData<String> = MutableLiveData(_model.value!!.name)
    val name: LiveData<String> = _name

    private var _details: MutableLiveData<String> = MutableLiveData(_model.value!!.details)
    val details: LiveData<String> = _details


    fun onNameChange(name: String){
        _name.value = name
    }
    fun onDetailsChange(details: String){
        _details.value = details
    }
    override fun onLoad(vararg args: Any) {
        _navController = args[0] as NavController
        crudOperation = args[1] as CRUD_Operation


        val task = args[2] as Task
        var taskSet: TaskSet = TaskSet()

        viewModelScope.launch {
             taskSet = taskSetsTable.read(task.taskSet.id)!!
        }


        when(crudOperation){
            CRUD_Operation.CREATE -> {
                title = "Add new Task"
                _model.value = task
            }
            CRUD_Operation.UPDATE -> {
                viewModelScope.launch {
                    _model.value = tasksTable.read(task.id, arrayOf(taskSet.id.toString()))
                    title = "Edit Task"
                }
            }
            else -> {

            }
        }.also {
            _model.value!!.taskSet = taskSet
            title += "\nFor ${taskSet.name}"
        }
    }











    override fun submitForm() {
        viewModelScope.launch {
            val task = _model.value!!
            task.name = _name.value!!
            task.details = _details.value!!

            Log.i(TAG, "submitForm: $task")

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
                _navController!!.popBackStack(
                    Screen.TaskList.withArgs(task.taskSet.id.toString()),
                    false,
                    false
                )
            }
        }
    }

}