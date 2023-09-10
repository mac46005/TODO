package com.preciado.todo.di.viewmodel_modules

import com.preciado.todo.data.TaskSetsTable
import com.preciado.todo.features.add_edit_taskset.core.AddEditTaskSetFormVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class VMModule {
    @Provides
    @ViewModelScoped
    fun providesAddEditListFormVM(
        taskSetsTable: TaskSetsTable
    ): AddEditTaskSetFormVM{
        return AddEditTaskSetFormVM(taskSetsTable)
    }
}