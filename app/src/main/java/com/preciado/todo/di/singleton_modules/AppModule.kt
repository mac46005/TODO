package com.preciado.todo.di.singleton_modules

import android.content.Context
import com.preciado.todo.data.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesTodoDb(@ApplicationContext context: Context): DatabaseHelper{
        return DatabaseHelper(context)
    }
}