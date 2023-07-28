package com.preciado.todo.di.viewmodel_modules

import com.preciado.todo.data.DatabaseHelper
import com.preciado.todo.data.TODOListTable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseTableModule {

    @Provides
    fun providesTODOlistTable(dbHelper: DatabaseHelper): TODOListTable{
        return TODOListTable(dbHelper)
    }
}