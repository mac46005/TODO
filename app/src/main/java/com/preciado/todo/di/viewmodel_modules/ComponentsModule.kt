package com.preciado.todo.di.viewmodel_modules

import com.preciado.todo.data.TasksTable
import com.preciado.todo.features.todo_tasks.core.TaskItemC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ComponentsModule {

    @Provides
    @ViewModelScoped
    fun providesTaskItemC(tasksTable: TasksTable): TaskItemC{
        return TaskItemC(tasksTable)
    }
}