package com.preciado.todo.features.todo_tasks.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.core.models.vm_models.models.TaskItemComponent
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskItemC @Inject constructor(
    private val tasksTable: TasksTable
) : TaskItemComponent<Task>() {


    override var data: Task = Task()

    private var _isSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    override var isSelected: LiveData<Boolean> = _isSelected

    override fun itemClicked(onClicked: () -> Unit) {

    }
}