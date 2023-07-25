package com.preciado.todo.core.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
    navController: NavController
){
    TODOTheme() {
        Surface() {
            TBarView(
                topBarTitle = {
                    Text(text = "ACTION")
                },
                topBarImage = {
                    TransparentButton {
                        Text(text = "X")
                    }
                },
                topBarActions = {
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Done")
                                }
                },
                topBarInnerHorizontalArrangement = Arrangement.Start,
            ) {
                Divider()
                TextField(value = "", onValueChange = {

                })
                Divider()
            }
        }
    }
}

@Preview
@Composable
fun PreviewActionView(){
    ActionView(
        title = "SOME ACTION",
        navController = rememberNavController()
    )
}