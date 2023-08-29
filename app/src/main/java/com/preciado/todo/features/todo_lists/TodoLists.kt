package com.preciado.todo.features.todo_lists

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoLists(

){
    TODOTheme {
        Scaffold {
            var pv = it
            BaseView {

            }
        }
    }
}