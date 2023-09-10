package com.preciado.todo.features.add_edit_taskset.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.helpers.CapitalizeString
import com.preciado.todo.core.models.app_models.TaskSet
import com.preciado.todo.core.models.vm_models.models.FormVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.data.TaskSetsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskSetFormVM @Inject constructor(
    private var taskSetsTable: TaskSetsTable
) : FormVM<TaskSet>() {

    private var _model: MutableLiveData<TaskSet> = MutableLiveData(TaskSet())
    override var model: LiveData<TaskSet>? = _model




    override fun onBackButtonClicked() {
        _navController!!.popBackStack(
            Screen.TODOLists.fullRoute(),
            false,
            false
        )
    }


    override fun getModel(): TaskSet {
        return model!!.value!!
    }

    override fun setModel(model: TaskSet) {
        _model.value = model
    }







    override fun onLoad(vararg args: Any) {
        _navController = args[0] as NavController
        crudOperation = args[1] as CRUD_Operation
        val taskSet = args[2] as TaskSet
        when(crudOperation){
            CRUD_Operation.CREATE -> {
                title = "Add new List"
                _model.value = taskSet
            }
            CRUD_Operation.UPDATE -> {
                viewModelScope.launch {
                    _model.value = taskSetsTable.read(taskSet.id)
                    title = "Edit " + _model.value!!.name
                }
            }
            else -> {

            }
        }
    }






    private val _name: MutableLiveData<String> = MutableLiveData(_model.value!!.name)
    val name: LiveData<String> = _name

    fun nameChanged(name: String){
        //_model.value!!.name = name
        _name.value = CapitalizeString.getCaptilizedSentence(name)
    }

    override fun submitForm() {
        //TODO Add user error views

        var list = _model.value!!
        list.name = _name.value?: ""

        viewModelScope.launch {
            when(crudOperation){
                CRUD_Operation.CREATE -> {
                    taskSetsTable.create(list)
                }
                CRUD_Operation.UPDATE -> {
                    taskSetsTable.update(list)
                }
                else -> {

                }
            }.also {
                onBackButtonClicked()
            }
        }

    }
}