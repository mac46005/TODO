package com.preciado.todo.di.viewmodel_modules

import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.models.vm_models.interfaces.IFormVM
import com.preciado.todo.features.add_edit_list.core.AddEditListFormVM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindVMModule {
    @Binds
    @ViewModelScoped
    abstract fun bindAddEditListFormVM(
        addEditListFormVM: AddEditListFormVM
    ): IFormVM<TODOList>
}