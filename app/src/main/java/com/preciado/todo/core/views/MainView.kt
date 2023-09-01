package com.preciado.todo.core.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.R
import com.preciado.todo.core.common_visuals.components.MainHeaderBar
import com.preciado.todo.core.common_visuals.components.SubHeaderBar
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    navController: NavController,
    subHeader: String = "SubHeader",
    backButtonEnabled: Boolean = true,
    popBackStackDestination: String = "",
    fabEnabled: Boolean = true,
    onFabClickedDestination: String = "",
    listContent: @Composable (ColumnScope.() -> Unit)
){
    TODOTheme() {
        Scaffold(
            floatingActionButton = {
                Button(
                    onClick = { navController.navigate(onFabClickedDestination) },
                    enabled = fabEnabled
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add new list")
                }
            }
        ) {
            var pv = it
            Column (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
                MainHeaderBar(navController, backButtonEnabled = backButtonEnabled, popBackDestination = popBackStackDestination)
                SubHeaderBar(
                    header = subHeader
                )
                listContent(this)
            }
        }
    }


}

@Preview
@Composable
fun PreviewMainView(){
    MainView(rememberNavController(), onFabClickedDestination = ""){

    }
}