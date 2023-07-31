package com.preciado.todo.core.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.TransparentButton
import com.preciado.todo.core.common_visuals.views.TBarView
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionView(
    title: String,
    navController: NavController,
    submit: (() -> Unit),
    canceled: (() -> Unit),
    doneButtonEnabled: Boolean = false,
    content: @Composable (ColumnScope.() -> Unit)
) {
    TODOTheme() {
        Scaffold() { padding ->
            val paddingValues = padding
            TBarView(
                topBarTitle = {
                    Text(text = title)
                },
                topBarImage = {
                    TransparentButton(
                        onClick = {
                            navController.popBackStack()
                            canceled()
                        }
                    ) {
                        Text(text = "X")
                    }
                },
                topBarActions = {
                    TransparentButton(
                        onClick = submit,
                        enabled = doneButtonEnabled
                    ) {
                        Text(text = "Done")
                    }
                },
                topBarInnerHorizontalArrangement = Arrangement.Start,
            ) {
                content(this)
            }
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
        canceled = {

        }
    ){

    }
}