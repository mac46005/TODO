package com.preciado.todo.features.task_list.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.models.app_models.models.TaskSet
import com.preciado.todo.core.models.vm_models.models.TaskItemComponent
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

private const val TAG = "TaskItemC"
@HiltViewModel
class TaskItemC @Inject constructor(
    private val tasksTable: TasksTable
) : TaskItemComponent<Task>() {



    override var data: Task = Task(taskSet = TaskSet(id = 0))


    var _checked:MutableLiveData<Boolean> = MutableLiveData(data.isCompleted)
    override var checked: LiveData<Boolean> = _checked

    override fun onItemChecked(checked: Boolean) {
        Log.i(TAG, "onItemChecked: data: $data")
        if(_checked.value == false){
            _checked.value = true
        }else{
            _checked.value = false
        }

        data.isCompleted = _checked.value!!
        data.completedOn = LocalDateTime.now()

        viewModelScope.launch {
            tasksTable.update(data)
        }
    }

    var _isSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    override var isSelected: LiveData<Boolean> = _isSelected

    override fun itemClicked(onClicked: () -> Unit) {
        onClicked()
        if(_isSelected.value == false){
            _isSelected.value = true
        }else{
            _isSelected.value = false
        }
    }
}