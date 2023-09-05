package com.preciado.todo.features.todo_lists.core

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.ui_models.interfaces.IVM
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.ui_models.models.ListVM
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TodoListVM @Inject constructor (
    private val todoListsTable: TODOListsTable
) : ListVM<TODOList>() {

    init {
        title = "Your Lists"
    }

    override fun loadList(vararg args: Any): Flow<List<TODOList>?> {
        return todoListsTable.readAll()
    }

    override fun updateList() {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(item: TODOList) {
        navController!!.navigate("")
    }

    override fun onLoad(vararg args: Any) {
        navController = args[0] as NavController
    }

    override fun onBackButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun onNavigateTo(vararg args: Any) {
        if(args[0] is CRUDEnum){
            val crudOp = args[0] as CRUDEnum
            when(crudOp){
                CRUDEnum.CREATE -> navController!!.navigate("add_edit_list/${crudOp.ordinal}/0")
                CRUDEnum.READ -> TODO()
                CRUDEnum.UPDATE -> TODO()
                CRUDEnum.DELETE -> TODO()
            }
        }
    }

    override fun setNavigator(navController: NavController) {
        TODO("Not yet implemented")
    }

}