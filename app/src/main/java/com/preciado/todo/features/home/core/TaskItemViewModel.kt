package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskItemViewModel @Inject constructor(
    private val tasksTable: TasksTable
) : ViewModel() {
    private val _task: MutableLiveData<Task> = MutableLiveData()
    val task: LiveData<Task> = _task

    fun setTask(task: Task){
        _task.value = task;
    }

    fun changeCompletion(clicked: Boolean): Unit{

    }
}