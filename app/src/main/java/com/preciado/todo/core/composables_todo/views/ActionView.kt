package com.preciado.todo.core.composables_todo.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.composable_templates.components.TransparentButton
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionView(
    title: String,
    navController: NavController,
    submit: (() -> Unit),
    backButtonClick: (() -> Unit),
    doneButtonEnabled: Boolean = false,
    content: @Composable (ColumnScope.() -> Unit)
) {
    TODOTheme() {
        TODOMainView() {
            
        }
    }
}

@Preview
@Composable
fun PreviewActionView() {
    ActionView(
        title = "SOME ACTION",
        navController = rememberNavController(),
        submit = {

        },
        backButtonClick = {

        }
    ){

    }
}