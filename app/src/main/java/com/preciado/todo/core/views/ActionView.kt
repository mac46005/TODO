package com.preciado.todo.core.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
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
    navController: NavController
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
                        }
                    ) {
                        Text(text = "X")
                    }
                },
                topBarActions = {
                    TransparentButton(
                        onClick = {
                            //TODO save new list to db
                        }
                    ) {
                        Text(text = "Done")
                    }
                },
                topBarInnerHorizontalArrangement = Arrangement.Start,
            ) {
                Divider()
                TextField(
                    value = "",
                    onValueChange = {

                    },
                    shape = RectangleShape,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    )
                )
                Divider()
            }
        }

    }
}

@Preview
@Composable
fun PreviewActionView() {
    ActionView(
        title = "SOME ACTION",
        navController = rememberNavController()
    )
}