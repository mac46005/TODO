package com.preciado.todo.features.todo_tasks.core

import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.core.models.vm_models.models.VM
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskItemVM @Inject constructor(
    private val tasksTable: TasksTable
) : VM<Task>() {

}