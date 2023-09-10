package com.preciado.todo.di.viewmodel_modules

import com.preciado.todo.data.DatabaseHelper
import com.preciado.todo.data.TasksTable
import com.preciado.todo.data.TaskSetsTable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object DatabaseTableModule {

    @Provides
    @ViewModelScoped
    fun providesTODOlistTable(dbHelper: DatabaseHelper): TaskSetsTable{
        return TaskSetsTable(dbHelper)
    }

    @Provides
    @ViewModelScoped
    fun providesTODOListTasksTable(dbHelper: DatabaseHelper): TasksTable{
        return TasksTable(dbHelper)
    }
}