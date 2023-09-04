package com.preciado.todo.core.common_visuals.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.preciado.todo.R
import com.preciado.todo.core.common_visuals.components.TODOTitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TODOMainView(
    backButtonVisible: Boolean = true,
    onBackButtonClicked: (() -> Unit) = {},
    floatingActionButtonEnabled: Boolean = false,
    onFloatingActionButtonClicked: (() -> Unit) = {},
    content:  @Composable (PaddingValues) -> Unit
){
    Scaffold(
        topBar = {
                 TODOTitleBar(
                     backButtonVisible = backButtonVisible,
                     onBackButtonClicked = onBackButtonClicked
                 )
        },
        floatingActionButton = {
            Button(
                onClick = onFloatingActionButtonClicked,
                enabled = floatingActionButtonEnabled
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Floating action button")
            }
        },
        content = content
    )
}

@Preview
@Composable
fun PreviewMainViewV1(){
    TODOMainView() {

    }
}